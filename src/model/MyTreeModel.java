//dependencies
package model;

import model.data.Project;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Vector;
//Responsibility of Ugonna, Beckett
//Edited by: Beckett


//Last checked: 22 Oct 05:02

//constructing the tree!!!!!
public class MyTreeModel implements TreeModel {
    private final Project rootProject; //root should not be editable under any circumstance, so it's final
    private Vector<TreeModelListener> treeModelListeners = new Vector<>();

    /**
     * Make the tree accessible by defining a root project
     * @param rootProject the root
     */
    public MyTreeModel(Project rootProject) {
        this.rootProject = rootProject;
    }

    /**
     * Make sure our tree obtains the root to initialize
     * @return rootProject
     */
    @Override
    public Object getRoot() {
        return rootProject;
    }

    /**
     * Get a child node at a certain point in the index
     * @param parent the parent of the node
     * @param index the index specified
     * @return the child
     */
    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeNode) parent).getChildAt(index);
    }

    /**
     * Returns an int describing how many children there are
     * @param parent The parent to get the children for
     * @return number of children
     */
    @Override
    public int getChildCount(Object parent) {
        return ((TreeNode) parent).getChildCount();
    }

    /**
     * Returns whether or not the node is a leaf i.e. childless
     * @param node node to check status of
     * @return true if leaf, else false
     */
    @Override
    public boolean isLeaf(Object node) {
        return ((TreeNode) node).isLeaf();
    }

    /**
     * Update new value for path when nodes are appended
     * @param path the path to update
     * @param newValue new value for the node
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    /**
     * Get the index number of the supplied child
     * @param parent parent node
     * @param child child to get the index of
     * @return index number
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    /**
     * Adds a listener called l
     * @param l the listener being added
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.addElement(l);
    }

    /**
     * Removing l once it has done its job
     * @param l listener
     */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.remove(l);
    }

    /**
     * Triggered when new nodes are added
     * @param addProj adds the project at the right place in the tree by first checking for the parent
     *                , then checking for the index among the children of that parent
     */
    public void executeNewNode(TreeNode addProj) {
        ArrayList<TreeNode> path = new ArrayList<>();
        path.add(rootProject);
        for (TreeNode parent = addProj.getParent();
             parent != rootProject;
             parent = parent.getParent()) {
            path.add(1,parent);
        }
        final int[] indices = {addProj.getParent().getIndex(addProj)};
        TreeModelEvent e = new TreeModelEvent(this, path.toArray(), indices,
                new Object[] {addProj});
        for (TreeModelListener tml : treeModelListeners) {
            tml.treeStructureChanged(e);
        }
    }
}
