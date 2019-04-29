package com.dc.frame2.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/6/5.
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {
    public static class ReflectException extends RuntimeException {
        private static final long serialVersionUID = -233333333333333L;
        
        ReflectException(Throwable t) {
            super(t);
        }
    }
    
    public static <T> T setValueByField(T target, String field, Object value) {
        String setMethodName = "set" + upperCaseFirst(field);
        AtomicBoolean flag = new AtomicBoolean(false);
        try {
            org.springframework.util.ReflectionUtils.doWithMethods(target.getClass(), method -> {
                if (!flag.get()) {
                    org.springframework.util.ReflectionUtils.invokeMethod(method, target, value);
                    flag.set(true);
                }
            }, method -> method.getName().equals(setMethodName)
                                 && method.getParameterCount() == 1
                                 && (value == null || value.getClass().isAssignableFrom(method.getParameterTypes()[0])));
            
            if (!flag.get()) {
                throw new NoSuchMethodException(target.getClass().getName() + "." + setMethodName);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ReflectException(e);
        }
        return target;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getValueByField(Object target, String field) {
        try {
            Method getMethod = org.springframework.util.ReflectionUtils.findMethod(target.getClass(), "get" + upperCaseFirst(field));
            return (T) org.springframework.util.ReflectionUtils.invokeMethod(getMethod, target);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
    
    public static <T extends Annotation> T getFieldAnnotation(Class<?> targetClass, String field, Class<T> annotation) {
        Field f = org.springframework.util.ReflectionUtils.findField(targetClass, field);
        if (f == null) {
            return null;
        }
        return f.getAnnotation(annotation);
    }
    
    private static String upperCaseFirst(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
    
    public static Class<?> resolveFieldClass(Class<?> cls, String field) {
        String methodName = "get" + upperCaseFirst(field);
        return org.springframework.util.ReflectionUtils.findMethod(cls, methodName).getReturnType();
    }
}
