package com.dc.dcrud.web.view.support.viewpojo;

import com.dc.frame2.view.Frame2View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>Descriptions...
 * Generator for a annotation configured view
 * MUST SET A DEFAULT NO ARGS CONSTRUCTOR
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public interface ViewGenerator {
    void configure(Class<?> root);
    
    void configure(Annotation viewAnnotation);
    
    void configure(String path);
    
    Frame2View generate(Object data, List<Field> fieldChain, Object fieldData);
    
}
