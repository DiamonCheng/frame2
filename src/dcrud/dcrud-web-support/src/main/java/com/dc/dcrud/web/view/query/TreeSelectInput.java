package com.dc.dcrud.web.view.query;

import com.dc.frame2.util.MapBuilder;

import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/25.
 */
public class TreeSelectInput extends ConditionView {
    private static final String TEMPLATE_NAME = "/common/crud/query/condition.wider.html.ftl";
    
    private TreeSelectInputNode treeSelectInputNode = new TreeSelectInputNode();
    
    public TreeSelectInput setNodes(List<TreeSelectInputNode.TreeNode> treeNodes) {
        treeSelectInputNode.setNodeList(treeNodes);
        return this;
    }
    
    public TreeSelectInput setName(String name) {
        treeSelectInputNode.setNodeName(name);
        return this;
    }
    
    public TreeSelectInput setId(String id) {
        treeSelectInputNode.setId(id);
        return this;
    }
    
    public String getId() {
        return treeSelectInputNode.getId();
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("inputs", treeSelectInputNode)
                       .put("label", getLabel())
                       .build();
    }
}
