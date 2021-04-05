//dependencies
package model.data;

import model.CatchingBadData;
import model.scaling.UrgencyAccuracy;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Stream;
//responsibility of Huang, Beckett, Rez

//edited by Beckett

//last checked 22/10 04:21 AM

//a task is one of our abstract node types
public class Task extends AbstrNode implements Comparable<Task> {
    private final ArrayList<UrgencyAdd> accuracy = new ArrayList<>(); //it has an accuracy

    /**
     * Constructs a task node out of the following inputs
     * @param name task name
     * @param description task description
     * @param urgencyAccuracy the desired default accuracy scale
     * @param project the parent project
     */
    private Task(String name, String description,
                 UrgencyAccuracy urgencyAccuracy, Project project) {
        super(name, description, project, urgencyAccuracy);
        this.name = name;
        this.desc = description;
    }

    /**
     * Add the accuracy for this node
     * @param accuracy the desired accuracy
     */
    public void add(UrgencyAdd accuracy) {
        this.accuracy.add(accuracy);
    }

    /**
     * Get a stream of accuracies for this node
     * @return stream
     */
    public Stream<UrgencyAdd> getAccuracy() {
        return accuracy.stream();
    }


    /**
     * Compare the value of nodes for the purposes of ordering
     * @param objC the object of comparison
     * @return the integer value of the difference between them
     */
    @Override
    public int compareTo(Task objC) {
        return getName().compareTo(objC.getName());
    }

    /**
     * Shows a string containing the input name and description
     * @return The created string
     */
    @Override
    public String toString() {
        return getName() + ": " + getDesc();
    }

    /**
     * Removing a node (does not yet work fully as intended)
     * @param selectedItem is what's being deleted
     * @return false
     */
    @Override
    public boolean deleteChild(TreeNode selectedItem) {
         accuracy.remove(selectedItem);
        return false;
    }

    //Tree stuff

    /**
     * Get child at the desired index
     * @param childIndex the index number sought
     * @return that child for the index
     */
    @Override //get child at desire index
    public TreeNode getChildAt(int childIndex) {
        return accuracy.get(childIndex);
    }

    /**
     * Getting how many children there are
     * @return the value of how many
     */
    @Override
    public int getChildCount() {
        return accuracy.size();
    }

    /**
     * Finds out whether the node is a leaf i.e. childless
     * @return true if leaf, else false
     */
    @Override
    public boolean isLeaf() {
        return accuracy.isEmpty();
    }

    /**
     * Get the index of specified node
     * @param node node to specify
     * @return index of node
     */
    @Override //get desired index
    public int getIndex(TreeNode node) {
        return -1;
    }

    /**
     * Gets enum of children
     * @return enum
     */
    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(accuracy);
    }

    /**
     * Setting up the ability to catch bad inputs from attempted node creations
     * @param name specified name
     * @param description specified desc
     * @param i integer value
     * @param urgencyAccuracy scale
     * @param project parent
     * @return the result of CatchingBadData for this
     */
    public static CatchingBadData<Task> create(String name, String description,
                                               int i, UrgencyAccuracy urgencyAccuracy, Project project) {
    //ended up not fully implementing this in time...
        return new CatchingBadData<>(new Task(name, description,
                urgencyAccuracy, project));
    }
}