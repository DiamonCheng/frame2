package com.dc.dcrud.web.view.support.viewpojo.inputview;

import com.dc.dcrud.web.view.query.TreeSelectInputNode;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/25.
 */
public interface TreeNodeProvider {
    List<TreeSelectInputNode.TreeNode> listRootTreeNode();
}
