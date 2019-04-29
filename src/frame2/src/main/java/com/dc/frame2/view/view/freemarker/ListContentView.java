package com.dc.frame2.view.view.freemarker;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/19.
 */
public class ListContentView implements FreemarkerView {
    private static final String TEMPLATE_NAME="/test/test2.html.ftl";
    private List<Frame2View> list=new ArrayList<>();
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.<String,Object>hashMap().put("views",list).build();
    }
    
    public ListContentView addView(Frame2View view){
        list.add(view);
        return this;
    }
}
