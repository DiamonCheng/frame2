package com.dc.dcrud.web.view.support.viewpojo.inputview;

import com.dc.dcrud.web.view.query.TreeSelectInputNode;
import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;
import com.dc.frame2.util.SpringContextUtils;
import com.dc.frame2.view.Frame2View;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public class TreeSelectInputGenerator implements ViewGenerator {
    private TreeSelectInput selectInputAnnotation;
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
        this.selectInputAnnotation = (TreeSelectInput) viewAnnotation;
    }
    
    @Override
    public void configure(String path) {
        this.path = path;
    }
    
    @Override
    public Frame2View generate(Object data1, List<Field> fieldChain, Object value) {
        com.dc.dcrud.web.view.query.TreeSelectInput treeSelectInput = new com.dc.dcrud.web.view.query.TreeSelectInput();
        treeSelectInput.setId(selectInputAnnotation.id());
        treeSelectInput.setLabel(selectInputAnnotation.label());
        String nameConfigured = selectInputAnnotation.name();
        if (StringUtils.isEmpty(nameConfigured)) {
            treeSelectInput.setName(path);
        } else {
            treeSelectInput.setName(nameConfigured);
        }
        String labelConfigured = selectInputAnnotation.label();
        if (StringUtils.isEmpty(labelConfigured)) {
            treeSelectInput.setLabel(root.getName() + "." + path);
        } else {
            treeSelectInput.setLabel(labelConfigured);
        }
        
        // validators Arrays.stream(selectInputAnnotation.validators()).forEach(treeSelectInput::addValidator);
        
        // do options
        String className = selectInputAnnotation.optionProvider();
        TreeNodeProvider treeNodeProvider = null;
        Exception ex = null;
        try {
            Class<?> cls = Class.forName(className);
            if (TreeNodeProvider.class.isAssignableFrom(cls)) {
                treeNodeProvider = (TreeNodeProvider) SpringContextUtils.tryGetInstanceByClass(cls);
            } else {
                throw new IllegalStateException("Class not an instance of treeNodeProvider, class:" + cls);
            }
        } catch (Exception e) {
            ex = e;
        }
        try {
            if (treeNodeProvider == null) {
                treeNodeProvider = (TreeNodeProvider) SpringContextUtils.getBean(className);
            }
        } catch (Exception e) {
            IllegalStateException ex2 = new IllegalStateException("Cannot find bean or initialize class for config class name :" + className, e);
            if (ex != null) {
                ex2.addSuppressed(ex);
            }
            throw ex2;
        }
        
        List<TreeSelectInputNode.TreeNode> treeNodes = treeNodeProvider.listRootTreeNode();
        
        String valueStr = SelectInputGenerator.extractValueStrByFieldChain(data1, fieldChain, value);
        
        if (valueStr != null) {
            Set<String> values = new HashSet<>(Arrays.asList(valueStr.split(",")));
            checkTreeNodes(values, treeNodes);
        }
        treeSelectInput.setNodes(treeNodes);
        return treeSelectInput;
    }
    
    private static void checkTreeNodes(Set<String> values, List<TreeSelectInputNode.TreeNode> treeNodes) {
        treeNodes.forEach(treeNode -> {
            if (values.contains(treeNode.getNodeValue())) {
                treeNode.setChecked(true);
                if (treeNode.getChildren() != null && !treeNode.getChildren().isEmpty()) {
                    checkTreeNodes(values, treeNode.getChildren());
                }
            }
        });
    }
    
}
