package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.query.ConditionView;
import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;
import com.dc.dcrud.web.view.support.viewpojo.inputview.InputViewConfigurator;
import com.dc.frame2.ReflectionUtils;
import com.dc.frame2.view.Frame2View;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/12.
 */
public class InputPanelViewSupport {
    protected List<FieldViewGeneratorTracer> fieldViewConfigurations = null;
    protected Class<?> configBaseClass;
    
    protected void resolveConditionGroups(Class<?> baseClass, List<Field> fieldChain, Class<?> conditionsGroupClass) {
        //1. 获取所有字段基于PageSearcher
        //2. 检查字段所有注解类型，获取有@ViewConfigurator注解的字段判断字段
        //3. 根据注解字段类型生成FieldViewGeneratorTracer List
        ReflectionUtils.doWithFields(conditionsGroupClass, field -> {
            List<Field> nextFieldChain = new ArrayList<>(fieldChain);
            nextFieldChain.add(field);
            Class<?> fieldClass = field.getType();
            if (configBaseClass.isAssignableFrom(fieldClass)) {
                resolveConditionGroups(baseClass, nextFieldChain, fieldClass);
                return;
            }
            
            Annotation viewAnnotation = null;
            InputViewConfigurator inputViewConfigurator = null;
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                inputViewConfigurator = annotation.annotationType().getAnnotation(InputViewConfigurator.class);
                if (inputViewConfigurator != null) {
                    viewAnnotation = annotation;
                    break;
                }
            }
            if (inputViewConfigurator == null) {
                return;
            }
            
            String path = nextFieldChain.stream()
                                  .map(Field::getName)
                                  .reduce("", (f1, f2) -> StringUtils.isEmpty(f1) ? (f2) : (f1 + "." + f2));
            ViewGenerator viewGenerator = null;
            try {
                viewGenerator = inputViewConfigurator.value().newInstance();
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
            fieldViewConfigurations.add(fieldViewGeneratorTracer);
        });
    }
    
    protected void genInputViews(Object configPojo, Function<ConditionView, ?> addTo) {
        fieldViewConfigurations.forEach(fieldViewGeneratorTracer -> {
            Object fieldData = configPojo;
            List<Field> fieldChain = fieldViewGeneratorTracer.getFieldChain();
            for (Field field : fieldChain) {
                fieldData = ReflectionUtils.getValueByField(configPojo, field.getName());
                if (fieldData == null) {
                    break;
                }
            }
            Frame2View conditionView = fieldViewGeneratorTracer.getViewGenerator().generate(fieldData);
            addTo.apply((ConditionView) conditionView);
        });
    }
}
