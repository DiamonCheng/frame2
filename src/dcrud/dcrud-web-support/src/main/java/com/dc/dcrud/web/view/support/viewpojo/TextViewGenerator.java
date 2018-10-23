package com.dc.dcrud.web.view.support.viewpojo;

import com.dc.dcrud.web.view.query.InputCondition;
import com.dc.frame2.view.Frame2View;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public class TextViewGenerator implements ViewGenerator {
    private TextView textViewAnnotation;
    private String path;
    private Class<?> root;
    
    @Override
    public void configure(Class<?> root) {
        this.root = root;
    }
    
    @Override
    public void configure(Annotation viewAnnotation) {
        this.textViewAnnotation = (TextView) viewAnnotation;
    }
    
    @Override
    public void configure(String path) {
        this.path = path;
    }
    
    @Override
    public Frame2View generate(Object data) {
        InputCondition inputCondition = new InputCondition()
                                                .setId(textViewAnnotation.id())
                                                .setPlaceHolder(textViewAnnotation.placeHolder());
        String nameConfigured = textViewAnnotation.name();
        if (StringUtils.isEmpty(nameConfigured)) {
            inputCondition.setName(path);
        } else {
            inputCondition.setName(nameConfigured);
        }
        String labelConfigured = textViewAnnotation.label();
        if (StringUtils.isEmpty(labelConfigured)) {
            inputCondition.setLabel(root.getName() + "." + path);
        } else {
            inputCondition.setLabel(labelConfigured);
        }
        inputCondition.setValue(data == null ? "" : data.toString());
        return inputCondition;
    }
}
