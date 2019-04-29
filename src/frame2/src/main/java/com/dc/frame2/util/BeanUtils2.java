package com.dc.frame2.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2019/4/17.
 */
public class BeanUtils2 extends BeanUtils {
    
    public static <T> T typeMappingCopyProperties(Object source, Map<Class<?>, Class<?>> typeMapping) {
        if (source==null){
            return null;
        }
        Class<?> targetClass=typeMapping.entrySet().stream()
                                     .filter(entry->entry.getKey().isAssignableFrom(source.getClass()))
                                     .findFirst()
                                     .map(Map.Entry::getValue)
                                     .orElse(null);
        
        if (targetClass==null){
            return null;
        }else{
            T targetInstance= null;
            try {
                targetInstance = (T) targetClass.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
            PropertyDescriptor[] targetPds = getPropertyDescriptors(targetClass);
            for (PropertyDescriptor targetPd : targetPds) {
                Method writeMethod = targetPd.getWriteMethod();
                if (writeMethod != null) {
                    PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                    if (sourcePd != null) {
                        Method readMethod = sourcePd.getReadMethod();
                        try {
                            if (readMethod != null) {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }
                                Object value=readMethod.invoke(source);
                                if (value==null){
                                    continue;
                                }
                                if (Iterable.class.isAssignableFrom(readMethod.getReturnType())){
                                    List<Object> valueList=new ArrayList<>();
                                    Iterable valueSourceList= (Iterable) value;
                                    for(Object valueSource:valueSourceList){
                                        Object listValue=typeMappingCopyProperties(valueSource,typeMapping);
                                        if (valueSource!=null&&listValue==null) {
                                            continue;
                                        }
                                        valueList.add(listValue);
                                    }
                                    if (List.class.isAssignableFrom(writeMethod.getParameterTypes()[0])){
                                        value=valueList;
                                    }else if (Set.class.isAssignableFrom(writeMethod.getParameterTypes()[0])){
                                        value=new HashSet<>(valueList);
                                    }else if (writeMethod.getParameterTypes()[0].isArray()){
                                        value=valueList.toArray( Arrays.copyOf(
                                                new Object[0], valueList.size(),
                                                (Class<? extends Object[]>) writeMethod.getParameterTypes()[0].getComponentType()
                                        ));
                                    }
                                }else if (!ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())){
                                    value=typeMappingCopyProperties(value,typeMapping);
                                }
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(targetInstance, value);
                                
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
            return targetInstance;
        
        }
    }
    
}
