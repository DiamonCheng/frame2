package com.dc.dcrud.web.view.support.viewpojo.inputview;

import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;
import com.dc.frame2.view.Frame2View;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public class ReadonlyTextInputGenerator implements ViewGenerator {
    private com.dc.dcrud.web.view.support.viewpojo.inputview.ReadonlyTextInput textInputAnnotation;
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
        this.textInputAnnotation = (com.dc.dcrud.web.view.support.viewpojo.inputview.ReadonlyTextInput) viewAnnotation;
    }
    
    @Override
    public void configure(String path) {
        this.path = path;
    }
    
    @Override
    public Frame2View generate(Object data) {
        com.dc.dcrud.web.view.query.ReadonlyTextInput textInput = new com.dc.dcrud.web.view.query.ReadonlyTextInput()
                                                                          .setId(textInputAnnotation.id());
        String labelConfigured = textInputAnnotation.label();
        if (StringUtils.isEmpty(labelConfigured)) {
            textInput.setLabel(root.getName() + "." + path);
        } else {
            textInput.setLabel(labelConfigured);
        }
        textInput.setValue(data == null ? "" : data.toString());
        return textInput;
    }
}
