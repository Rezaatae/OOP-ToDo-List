//dependency
package model.data;

import model.CatchingBadData;
import model.scaling.UrgencyAccuracy;

import javax.swing.tree.TreeNode;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Stream;
//responsibility of Huang

//edited by Beckett

//Last checked 22/10 03:54 AM

//A project is one of the things we can create from our abstract node
public class Project extends AbstrNode implements Comparable<Project> {
    private HashMap<String, AbstrNode> children = new HashMap<>();
    //projects can contain other projects and can also have null parents, hence hashmap of projs

    /**
     * Make a project with null parents
     * @param name Project name
     * @param description Project description
     * @param urgencyAccuracy default accuracy scale
     */
    private Project(String name, String description, UrgencyAccuracy urgencyAccuracy) {
        super(name, description, null, urgencyAccuracy);
    }

    /**
     * Or make a sub-project which is a child of something
     * @param name name
     * @param description description
     * @param parent designated parent
     * @param urgencyAccuracy default accuracy scale
     */
    private Project(String name, String description, Project parent, UrgencyAccuracy urgencyAccuracy) {
        super(name, description, parent, urgencyAccuracy);
    }


    /**
     *  Get a stream of accuracies
     * @return stream
     */
    public Stream<UrgencyAdd> getAccuracy() {
        return (children.values()
                .parallelStream()
                .flatMap(AbstrNode::getAccuracy));
    }

    /**
     * Add a node to this project
     * @param project the node that will be receiving this project as a parent
     */
    void add(AbstrNode project) {
        children.put(project.getName(), project);
    }

    /**
     * Handles ordering within the tree
     * @param objC the object of comparison for the sake of ordering
     * @return an integer value of how much greater/less than this is to the objC
     */
    @Override
    public int compareTo(Project objC) { //compare the int to project
        return "root".equals(getName()) ? -1 :
                "root".equals(objC.getName()) ? 1 : getName().compareTo(objC.getName());
    }

    /**
     * displays a string next containing the details of the project
     * @return the string
     */
    @Override
    public String toString() {
        return ("ROOT".equals(getName()) ? "" : getName() + ": ") + getDesc(); //display name and description
    }

    /**
     * Deleting a child from the selected item
     * @param selectedItem is what's being deleted
     * @return false
     */
    @Override
    public boolean deleteChild(TreeNode selectedItem) {
        return false;
    }

    //Tree stuff

    /**
     * Get the child at the specified index
     * @param childIndex index to find the child for
     * @return child
     */
    @Override
    public TreeNode getChildAt(int childIndex) {
        return (TreeNode) children.values().toArray()[childIndex];
    }

    /**
     * Get the amount of children for the selected node
     * @return number of children
     */
    @Override
    public int getChildCount() {
        return children.size();
    }

    /**
     * Finding out whether the node is a leaf i.e. it is childless
     * @return true if leaf, false if not
     */
    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Get the desired index of a node
     * @param node The node to carry out this operation on
     * @return index no.
     */
    @Override
    public int getIndex(TreeNode node) {
        Object[] projectArray = children.values().toArray();
        for (int i = 0; i < children.size(); i++) {
            if (node == projectArray[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the enum of all the children
     * @return enum
     */
    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children.values());
    }

    /**
     * Constructed for the purpose of catching potential bad inputs out of the input given
     * @param name input name
     * @param description input desc
     * @param urgencyAccuracy input scale default
     * @return the result of CatchingBadData for this node
     */
    public static CatchingBadData<Project> create(String name, String description, UrgencyAccuracy urgencyAccuracy) {
        return new CatchingBadData<>(new Project(name, description, urgencyAccuracy));
    }

    /**
     * As above, but for nested projects.
     * @param name name
     * @param description description
     * @param parent parent node
     * @param urgencyAccuracy scale default
     * @return the result of CatchingBadData for this node
     */
    public static CatchingBadData<Project> create(String name, String description, Project parent, UrgencyAccuracy urgencyAccuracy) {
        return new CatchingBadData<>(new Project(name, description, parent, urgencyAccuracy));
    }
}