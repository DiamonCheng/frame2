package com.dc.frame2.view.view.freemarker;

import java.util.HashMap;
import java.util.Map;

/**
 * For custom write component.
 *
 * @author DC
 * @date 2018/4/24.
 */
public class CustomFreemarkerView implements FreemarkerView{
    private String templateName;
    Map<String,Object> param=new HashMap<>();
    public CustomFreemarkerView(String templateName){
        this.templateName=templateName;
    }
    @Override
    public String getTemplateName() {
        return templateName;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return param;
    }
    
    public CustomFreemarkerView addParam(String key,Object value){
        param.put(key,value);
        return this;
    }
}
