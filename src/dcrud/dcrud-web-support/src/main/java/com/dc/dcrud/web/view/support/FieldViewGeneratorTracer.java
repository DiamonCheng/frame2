package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/30.
 */
public class FieldViewGeneratorTracer {
    /**
     * field1.field2->xxxViewGenerator
     */
    private Class<?> baseClass;
    private List<Field> fieldChain;
    private ViewGenerator viewGenerator;
    
    public Class<?> getBaseClass() {
        return baseClass;
    }
    
    public FieldViewGeneratorTracer setBaseClass(Class<?> baseClass) {
        this.baseClass = baseClass;
        return this;
    }
    
    public List<Field> getFieldChain() {
        return fieldChain;
    }
    
    public FieldViewGeneratorTracer setFieldChain(List<Field> fieldChain) {
        this.fieldChain = fieldChain;
        return this;
    }
    
    public ViewGenerator getViewGenerator() {
        return viewGenerator;
    }
    
    public FieldViewGeneratorTracer setViewGenerator(ViewGenerator viewGenerator) {
        this.viewGenerator = viewGenerator;
        return this;
    }
    
    @Override
    public String toString() {
        if (fieldChain != null) {
            return fieldChain.stream().map(Field::getName).reduce((f1, f2) -> f1 + "." + f2) + viewGenerator.toString();
        } else {
            return "Empty";
        }
    }
}
