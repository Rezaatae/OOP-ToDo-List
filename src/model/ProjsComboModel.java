//dependencies
package model;

import model.data.Project;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import java.util.Arrays;
import java.util.Vector;
//Responsibility of Ugonna, Beckett
//Edited by: Beckett

//Last checked: 22 Oct 5:10 AM

//let's make the project selection combobox!
public class ProjsComboModel implements ComboBoxModel<Project>, TreeModelListener {
    private Model model; //incorporates the model
    private Project[] projects; //incorporates the project list
    private Vector<ListDataListener> listofListeners = new Vector<>();
    private Project selectedItem;

    /**
     * The combobox is a model that incorporates the list of projects as specified by
     * the listeners for each project, which put the projects into an array depending
     * on index numbers.
     * @param model the combo box model
     */
    public ProjsComboModel(Model model) {
        this.model = model;
        //incorporated the tree
        MyTreeModel myTreeModel = (MyTreeModel) model.getTreeModel();
        myTreeModel.addTreeModelListener(this);
        projects = model.getProjects().values().toArray(new Project[0]);
        Arrays.sort(projects);
        setSelectedItem(projects[0]);
    }

    /**
     * Sets the selected item via listeners
     * @param anItem the currently selected item
     */
    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Project) anItem;
    }

    /**
     * Returns the item that's currently selected
     * @return selected item
     */
    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Returns the current length of the list of projects
     * @return integer length
     */
    @Override
    public int getSize() {
        return model.getProjects().size();
    }

    /**
     * Returns the element at the specified index number
     * @param index index specified
     * @return the project at that point in the array of projects
     */
    @Override
    public Project getElementAt(int index) {
        return projects[index];
    }

    /**
     * Add a data listener l to check for updates to data
     * @param l listener
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        listofListeners.add(l);
    }

    /**
     * Remove the listener l when it has fulfilled use
     * @param l listener
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        listofListeners.remove(l);
    }

    //execute changes to tree
    private void executeTreeChanges() {
        ListDataEvent event = new ListDataEvent(projects, ListDataEvent.CONTENTS_CHANGED, 0,
                projects.length); //get new contents
        for (ListDataListener listDataListener : listofListeners) { //update for each listener in list
            listDataListener.contentsChanged(event);
        }
    }

    /**
     * Execute when there has been a change to a node
     * @param e event when tree nodes are updated
     */
    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * Execute event when a new node is inserted
     * @param e event
     */
    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * Execute event when a node is removed
     * @param e event
     */
    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * Execute event when the structure has changed drastically.
     * The projects are reassigned array values, and the selected item is set again.
     * @param e event
     */
    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        projects = model.getProjects().values().toArray(new Project[0]);
        Arrays.sort(projects);
        setSelectedItem(selectedItem);
        executeTreeChanges();
    }
}