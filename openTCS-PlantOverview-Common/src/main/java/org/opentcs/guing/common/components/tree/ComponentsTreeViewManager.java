/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.common.components.tree;

import jakarta.inject.Inject;
import java.awt.event.MouseListener;
import javax.swing.tree.TreeSelectionModel;
import org.opentcs.guing.base.model.ModelComponent;
import org.opentcs.guing.common.components.tree.elements.UserObjectContext;
import org.opentcs.guing.common.components.tree.elements.UserObjectUtil;

/**
 * The tree view manager for components.
 */
public class ComponentsTreeViewManager
    extends TreeViewManager {

  /**
   * Creates a new instance.
   *
   * @param treeView The tree view
   * @param userObjectUtil The user object util
   * @param mouseListener The mouse listener
   */
  @Inject
  public ComponentsTreeViewManager(TreeView treeView,
                                   UserObjectUtil userObjectUtil,
                                   MouseListener mouseListener) {
    super(treeView, userObjectUtil, mouseListener);
    treeView.getTree().getSelectionModel().setSelectionMode(
        TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
  }

  @Override
  public void addItem(Object parent, ModelComponent item) {
    if (item.isTreeViewVisible()) {
      UserObjectContext context
          = userObjectUtil.createContext(UserObjectContext.ContextType.COMPONENT);
      getTreeView().addItem(parent, userObjectUtil.createUserObject(item, context));
    }
  }
}
