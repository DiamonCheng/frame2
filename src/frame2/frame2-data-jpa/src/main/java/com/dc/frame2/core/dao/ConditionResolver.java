package com.dc.frame2.core.dao;

import com.dc.frame2.core.dao.conditions.*;
import com.dc.frame2.core.exception.TranslatableException;
import com.dc.frame2.util.SpringContextUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.persistence.criteria.JoinType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/14.
 */
public class ConditionResolver {
    private static final char ESCAPE_CHAR ='#';
    private static final Pattern ESCAPE_PATTERN=Pattern.compile("["+ESCAPE_CHAR+"_%]]");
    
    public static ConditionResolver getInstance() {
        return instance;
    }
    
    private static ConditionResolver instance = new ConditionResolver();
    
    @SuppressWarnings("unchecked")
    public Predicate resolve(ConditionsGroup searcher, Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (searcher == null) {
            return null;
        }
        List<ConditionPojo> conditionList = resolveFieldsList(searcher.getClass());
        Predicate predicate = null;
        for (ConditionPojo conditionPojo : conditionList) {
            Condition condition = conditionPojo.getCondition();
            Field field = conditionPojo.getField();
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, searcher);
            Class<CustomSearcherFieldResolver> customSearcherFieldResolverClass = condition.customResolver();
            if (CustomSearcherFieldResolver.class.equals(customSearcherFieldResolverClass)) {
                //standard resolve condition
                if (value == null && !condition.nullResolve()) {
                    continue;
                }
                if (ConditionsGroup.class.isAssignableFrom(field.getType())) {
                    Predicate subPredicate = resolve(ConditionsGroup.class.cast(value), root, query, builder);
                    predicate = mergePredicate(predicate, subPredicate, condition, builder);
                } else {
                    Path<?> path = getPath(root, condition, field);
                    predicate = mergePredicate(
                            predicate,
                            resolveOperator(path, value, condition, builder),
                            condition,
                            builder
                    );
                }
            } else {
                //custom resolve condition
                CustomSearcherFieldResolver customSearcherFieldResolver;
                try {
                    customSearcherFieldResolver = SpringContextUtils.getBean(customSearcherFieldResolverClass);
                    if (customSearcherFieldResolver == null) {
                        customSearcherFieldResolver = customSearcherFieldResolverClass.newInstance();
                    }
                    Path<?> path = getPath(root, condition, field);
                    return customSearcherFieldResolver.resolve(root, path, query, builder, searcher, value);
                } catch (Exception e) {
                    throw new IllegalStateException("Custom condition field failed. extractor: " + customSearcherFieldResolverClass, e);
                }
            }
        }
        return predicate;
    }
    
    @SuppressWarnings("unchecked")
    private Predicate resolveOperator(Path path, Object value, Condition condition, CriteriaBuilder builder){
        try {
            switch (condition.operator()){
                case EQ:
                    return builder.equal(path, value);
                case GT:
                    return builder.gt(path, (Number) value);
                case LT:
                    return builder.lt(path, (Number) value);
                case GTEQ:
                    return builder.ge(path, (Number) value);
                case LTEQ:
                    return builder.le(path, (Number) value);
                case NOT_EQ:
                    return builder.notEqual(path, value);
                case LIKE:
                    return builder.like(path, (String) value);
                case DUP_LIKE:
                    return builder.like(path, "%"+escapeParamString((String) value)+"%", ESCAPE_CHAR);
                case PRE_LIKE:
                    return builder.like(path, escapeParamString((String) value)+"%", ESCAPE_CHAR);
                case POST_LIKE:
                    return builder.like(path, "%"+escapeParamString((String) value), ESCAPE_CHAR);
                case IN:
                case NOT_IN: {
                    CriteriaBuilder.In expression = builder.in(path);
                    if (value instanceof Object[]) {
                        Arrays.stream((Object[]) value).forEach(expression::value);
                    } else if (value instanceof Collection) {
                        ((Collection) value).forEach(expression::value);
                    } else {
                        expression.value(value);
                    }
                    return condition.operator() == CompareOperator.IN ? expression : builder.not(expression);
                }
                default:
                    return null;
            }
        } catch (ClassCastException e) {
            throw TranslatableException.builder()
                          .cause(e)
                          .message("Parameter transfer failed, check searcher condition type with domain entity!")
                          .functionName("SEARCH_DAO")
                          .sceneName("FILL_CONDITION_s")
                          .context("path", path)
                          .context("value", value)
                          .context("condition",condition)
                          .build();
        }
    }
    private String escapeParamString(String input){
        return ESCAPE_PATTERN.matcher(input).replaceAll(ESCAPE_CHAR +"$1");
    }
    
    private Path<?> getPath(Root<?> root, Condition condition, Field field) {
        String conditionFieldPath = condition.value();
        if (StringUtils.isEmpty(conditionFieldPath)) {
            conditionFieldPath = field.getName().replace('$', '.');
        }
        Path<?> path = null;
        switch (condition.joinType()) {
            case NONE:
                path = root.get(conditionFieldPath);
                break;
            case INNER:
                path = root.join(conditionFieldPath, JoinType.INNER);
                break;
            case LEFT:
                root.join(conditionFieldPath, JoinType.LEFT);
                break;
            case RIGHT:
                root.join(conditionFieldPath, JoinType.RIGHT);
                break;
            default:
        }
        return path;
    }
    
    private Predicate mergePredicate(Predicate exists, Predicate toMerge, Condition condition, CriteriaBuilder builder) {
        if (toMerge == null) {
            return exists;
        }
        if (exists == null) {
            return toMerge;
        } else {
            if (PreContact.AND.equals(condition.preContact())) {
                return builder.and(exists, toMerge);
            } else {
                return builder.or(exists, toMerge);
            }
        }
    }
    
    private List<ConditionPojo> resolveFieldsList(Class<? extends ConditionsGroup> clazz) {
        List<ConditionPojo> conditionList = new ArrayList<>();
        ReflectionUtils.doWithFields(
                clazz,
                field -> {
                    Condition condition = field.getAnnotation(Condition.class);
                    if (condition == null && null == field.getAnnotation(NotCondition.class)) {
                        condition = defaultCondition(field.getName());
                    }
                    conditionList.add(new ConditionPojo().setCondition(condition).setField(field));
                },
                field -> ConditionsGroup.class.isAssignableFrom(field.getDeclaringClass())
        );
        conditionList.sort(Comparator.comparingInt(conditionPojo -> conditionPojo.getCondition().order()));
        return conditionList;
    }
    
    
    private static class ConditionPojo {
        private Condition condition;
        private Field field;
        
        public Condition getCondition() {
            return condition;
        }
        
        public Field getField() {
            return field;
        }
        
        public ConditionPojo setCondition(Condition condition) {
            this.condition = condition;
            return this;
        }
        
        public ConditionPojo setField(Field field) {
            this.field = field;
            return this;
        }
        
        @Override
        public String toString() {
            return field.toString();
        }
    }
    
    
    private Condition defaultCondition(String field) {
        return new Condition() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Condition.class;
            }
            
            @Override
            public String value() {
                return field;
            }
            
            @Override
            public CompareOperator operator() {
                return CompareOperator.EQ;
            }
            
            @Override
            public int order() {
                return 0;
            }
            
            @Override
            public PreContact preContact() {
                return PreContact.AND;
            }
            
            @Override
            public com.dc.frame2.core.dao.conditions.JoinType joinType() {
                return com.dc.frame2.core.dao.conditions.JoinType.NONE;
            }
            
            @Override
            public boolean nullResolve() {
                return false;
            }
    
            @Override
            public Class<CustomSearcherFieldResolver> customResolver() {
                return null;
            }
        };
    }
    
}
