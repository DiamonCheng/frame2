package com.dc.dcrud.web.view.support.viewpojo.inputview;

import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;
import com.dc.frame2.dict.OptionProvider;
import com.dc.frame2.util.ReflectionUtils;
import com.dc.frame2.data.DataFieldExtractor;
import com.dc.frame2.util.SpringContextUtils;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.form.Option;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public class SelectInputGenerator implements ViewGenerator {
    private com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput selectInputAnnotation;
    private String path;
    private Class<?> root;
    
    @Override
    public void configure(Class<?> root) {
        if (HibernateProxy.class.isAssignableFrom(root) || ClassUtils.isCglibProxyClass(root)) {
            this.root = root.getSuperclass();
        } else {
            this.root = root;
        }
        
    }
    
    @Override
    public void configure(Annotation viewAnnotation) {
        this.selectInputAnnotation = (com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput) viewAnnotation;
    }
    
    @Override
    public void configure(String path) {
        this.path = path;
    }
    
    @Override
    public Frame2View generate(Object data1, List<Field> fieldChain, Object value) {
        com.dc.dcrud.web.view.query.SelectInput selectInput = new com.dc.dcrud.web.view.query.SelectInput();
        selectInput.setId(selectInputAnnotation.id());
        selectInput.setLabel(selectInputAnnotation.label());
        String nameConfigured = selectInputAnnotation.name();
        if (StringUtils.isEmpty(nameConfigured)) {
            selectInput.setName(path);
        } else {
            selectInput.setName(nameConfigured);
        }
        String labelConfigured = selectInputAnnotation.label();
        if (StringUtils.isEmpty(labelConfigured)) {
            selectInput.setLabel(root.getName() + "." + path);
        } else {
            selectInput.setLabel(labelConfigured);
        }
    
        Arrays.stream(selectInputAnnotation.validators()).forEach(selectInput::addValidator);
    
        //placeHolder -- options
        String className = selectInputAnnotation.optionProvider();
        OptionProvider optionProvider = null;
        Exception ex = null;
        try {
            Class<?> cls = Class.forName(className);
            if (OptionProvider.class.isAssignableFrom(cls)) {
                optionProvider = (OptionProvider) SpringContextUtils.tryGetInstanceByClass(cls);
            } else {
                throw new IllegalStateException("Class not an instance of optionProvider, class:" + cls);
            }
        } catch (Exception e) {
            ex = e;
        }
        try {
            if (optionProvider == null) {
                optionProvider = (OptionProvider) SpringContextUtils.getBean(className);
            }
        } catch (Exception e) {
            IllegalStateException ex2 = new IllegalStateException("Cannot find bean or initialize class for config class name :" + className, e);
            if (ex != null) {
                ex2.addSuppressed(ex);
            }
            throw ex2;
        }
    
        Locale locale = LocaleContextHolder.getLocale();
        List<Option> options = optionProvider.listOptions(selectInputAnnotation.optionProviderKey(), locale);
        if (!StringUtils.isEmpty(selectInputAnnotation.placeHolder())) {
            selectInput.addOption(new Option().setText(selectInputAnnotation.placeHolder()).setValue(""));
        }
        String valueStr = null;
        valueStr = extractValueStrByFieldChain(data1, fieldChain, value);
    
        Set<String> values = new HashSet<>();
        if (valueStr != null) {
            values.addAll(Arrays.asList(valueStr.split(",")));
        }
        options.forEach(option -> {
            if (values.contains(option.getValue())) {
                option.setSelected(true);
            }
            selectInput.addOption(option);
        });
        // multi select check
        Field f = fieldChain.get(fieldChain.size() - 1);
        if (Collection.class.isAssignableFrom(f.getType()) || f.getType().isArray()) {
            selectInput.multipleSelect(true);
        }
        return selectInput;
    }
    
    public static String extractValueStrByFieldChain(Object data1, List<Field> fieldChain, Object value) {
        String valueStr = null;
        if (value != null) {
            int index = 0;
            Object fieldData = data1;
            String fieldName = null;
            for (Field field : fieldChain) {
                fieldName = field.getName();
                if (++index == fieldChain.size()) {
                    break;
                }
                fieldData = ReflectionUtils.getValueByField(data1, field.getName());
                if (fieldData == null) {
                    break;
                }
            }
            if (fieldData != null && fieldName != null) {
                valueStr = DataFieldExtractor.extractValue(fieldData, fieldName);
            }
        }
        return valueStr;
    }
}
