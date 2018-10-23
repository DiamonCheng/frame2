package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.query.ConditionView;
import com.dc.dcrud.web.view.query.QueryPanelView;
import com.dc.dcrud.web.view.support.viewpojo.ViewConfigurator;
import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;
import com.dc.frame2.ReflectionUtils;
import com.dc.frame2.core.dao.conditions.ConditionsGroup;
import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.view.Frame2View;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p> Generate query condition view in page view by page searcher
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public class QueryConditionViewFactory implements QueryViewFactory {
    
    private List<FieldViewGeneratorTracer> generators = null;
    
    public QueryConditionViewFactory configure(PageSearcher searcher) {
        Assert.notNull(searcher, "Searcher cannot be null.");
        generators = new ArrayList<>(10);
        resolveConditionGroups(searcher.getClass(), Collections.emptyList(), searcher.getClass());
        return this;
    }
    
    private void resolveConditionGroups(Class<?> baseClass, List<Field> fieldChain, Class<? extends ConditionsGroup> conditionsGroupClass) {
        //1. 获取所有字段基于PageSearcher
        //2. 检查字段所有注解类型，获取有@ViewConfigurator注解的字段判断字段
        //3. 根据注解字段类型生成FieldViewGeneratorTracer List
        ReflectionUtils.doWithFields(conditionsGroupClass, field -> {
            List<Field> nextFieldChain = new ArrayList<>(fieldChain);
            nextFieldChain.add(field);
            Class<?> fieldClass = field.getType();
            if (ConditionsGroup.class.isAssignableFrom(fieldClass)) {
                resolveConditionGroups(baseClass, nextFieldChain, (Class<? extends ConditionsGroup>) fieldClass);
                return;
            }
            
            Annotation viewAnnotation = null;
            ViewConfigurator viewConfigurator = null;
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                viewConfigurator = annotation.annotationType().getAnnotation(ViewConfigurator.class);
                if (viewConfigurator != null) {
                    viewAnnotation = annotation;
                    break;
                }
            }
            if (viewConfigurator == null) {
                return;
            }
            
            String path = nextFieldChain.stream().map(Field::getName).reduce("", (f1, f2) -> StringUtils.isEmpty(f1) ? (f2) : (f1 + "." + f2));
            ViewGenerator viewGenerator = null;
            try {
                viewGenerator = viewConfigurator.value().newInstance();
            } catch (InstantiationException e) {
                throw new IllegalStateException("Class configure failed, please set default no args constructor", e);
            }
            viewGenerator.configure(path);
            viewGenerator.configure(baseClass);
            viewGenerator.configure(viewAnnotation);
            FieldViewGeneratorTracer fieldViewGeneratorTracer =
                    new FieldViewGeneratorTracer()
                            .setBaseClass(baseClass)
                            .setViewGenerator(viewGenerator)
                            .setFieldChain(nextFieldChain);
            generators.add(fieldViewGeneratorTracer);
        });
    }
    
    
    @Override
    public Frame2View createPageQueryView(PageSearcher searcher) {
        QueryPanelView queryPanelView = new QueryPanelView();
        //1. 如果没有configure configure 生成 generators
        //generators.get(0).getViewGenerator().generate()
        //2. 使用ganerators变量解析searcher字段进行视图生成conditionViews 并添加进queryPanelView中。
//        if (generators==null){
        configure(searcher);
//        }
        generators.forEach(fieldViewGeneratorTracer -> {
            Object fieldData = searcher;
            List<Field> fieldChain = fieldViewGeneratorTracer.getFieldChain();
            for (Field field : fieldChain) {
                fieldData = ReflectionUtils.getField(field, searcher);
                if (fieldData == null) {
                    break;
                }
            }
            Frame2View conditionView = fieldViewGeneratorTracer.getViewGenerator().generate(fieldData);
            queryPanelView.addCondition((ConditionView) conditionView);
        });
        return queryPanelView;
    }
    
}
