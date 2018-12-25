package com.dc.dcrud.web.view.query;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/25.
 */
public class TreeSelectInputNode implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/tree-select/tree-select.html.ftl";
    
    public static class TreeNode {
        private List<TreeNode> children = null;
        private String nodeText;
        private String nodeTitle;
        private String nodeValue;
        private String nodeIcon;
        private boolean checked = false;
        
        public List<TreeNode> getChildren() {
            return children;
        }
        
        public TreeNode setChildren(List<TreeNode> children) {
            this.children = children;
            return this;
        }
        
        public String getNodeText() {
            return nodeText;
        }
        
        public TreeNode setNodeText(String nodeText) {
            this.nodeText = nodeText;
            return this;
        }
        
        public String getNodeTitle() {
            return nodeTitle;
        }
        
        public TreeNode setNodeTitle(String nodeTitle) {
            this.nodeTitle = nodeTitle;
            return this;
        }
        
        public String getNodeValue() {
            return nodeValue;
        }
        
        public TreeNode setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
            return this;
        }
        
        public boolean isChecked() {
            return checked;
        }
        
        public TreeNode setChecked(boolean checked) {
            this.checked = checked;
            return this;
        }
        
        public String getNodeIcon() {
            return nodeIcon;
        }
        
        public TreeNode setNodeIcon(String nodeIcon) {
            this.nodeIcon = nodeIcon;
            return this;
        }
    }
    
    private String nodeName;
    private String id;
    private List<TreeNode> nodeList;
    
    public String getNodeName() {
        return nodeName;
    }
    
    public TreeSelectInputNode setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }
    
    public List<TreeNode> getNodeList() {
        return nodeList;
    }
    
    public TreeSelectInputNode setNodeList(List<TreeNode> nodeList) {
        this.nodeList = nodeList;
        return this;
    }
    
    public String getId() {
        return id;
    }
    
    public TreeSelectInputNode setId(String id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("id", id)
                       .put("nodeName", nodeName)
                       .put("nodeList", nodeList)
                       .build();
    }
}
