//dependencies
package model;

import model.data.Task;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import java.util.Arrays;
import java.util.Vector;
//Ugonna, Beckett
//Edited by: Beckett

//Last checked: 22 Oct 5:25

//lets construct the tasks combobox
public class TasksComboModel implements ComboBoxModel<Task>, TreeModelListener {
    private final Model model; //incorporates the model
    private Task[] tasks;  //incorporates list of tasks
    private Vector<ListDataListener> listOfListeners = new Vector<>(); //needs access to listener
    private Task selectedItem; //needs to specifically listen to selected item

    /**
     * The combobox is a model that incorporated the list of tasks specified by the listeners
     * for each task, which put the tasks into an array depending on index numbers.
     * @param model The combobox model
     */
    public TasksComboModel(Model model) {
        this.model = model;
        //incorporates the tree
        MyTreeModel myTreeModel = (MyTreeModel) model.getTreeModel(); //get the tree
        myTreeModel.addTreeModelListener(this); //add listener to tree
        tasks = model.getProjs(); //receive all the projects
        Arrays.sort(tasks); //Makes a nice list of the tasks to choose from
    }

    /**
     * Returns the currently selected item according to listeners
     * @return currently selected item
     */
    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Sets the selected item according to the listeners
     * @param itemSelected item selected
     */
    @Override
    public void setSelectedItem(Object itemSelected) {
        selectedItem = (Task) itemSelected;
    }

    /**
     * Returns length of the list of tasks
     * @return integer length
     */
    @Override
    public int getSize() {
        return tasks.length;
    }

    /**
     * Returns an element at the specified index for search purposes
     * @param index given index
     * @return element
     */
    @Override
    public Task getElementAt(int index) {
        return tasks[index];
    }

    /**
     * Adds data listener l
     * @param l listener
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        listOfListeners.add(l);
    }

    /**
     * Remove l once is has done its job
     * @param l listener
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        listOfListeners.remove(l);
    }

    //Update the tree when stuff has changed
    private void executeTreeChanges() {
        ListDataEvent event = new ListDataEvent(tasks, ListDataEvent.CONTENTS_CHANGED, 0,
                tasks.length); //get the new amount of tasks
        for (ListDataListener listDataListener : listOfListeners) {
            listDataListener.contentsChanged(event); //for each listener in the list, update contents
        }
    }

    /**
     * Executed event when the tree nodes have changed
     * @param e event
     */
    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * Executed event when tree nodes are inserted
     * @param e event
     */
    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * Executed event when nodes are deleted
     * @param e event
     */
    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * Execute event for when the structure has changed drastically.
     * The tasks are reassigned array values, and the selected item is set again.
     * @param e event
     */
    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        tasks = model.getProjs(); //get the projects
        Arrays.sort(tasks); //resort them
        setSelectedItem(selectedItem); //refresh selected item
        executeTreeChanges(); //carry out changes
    }
}
