//dependencies... this is the big one
package model;

import model.data.AbstrNode;
import model.data.Project;
import model.data.Task;
import model.data.UrgencyAdd;
import model.scaling.HighAcc;
import model.scaling.LowAcc;
import model.scaling.MediumAcc;
import model.scaling.UrgencyAccuracy;
import view.SelectedNodeChanged;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;
//Beckett, Remy, Ugonna, Huang, Rez


//edited by: Beckett, Rez
//Last checked: 22 Oct 04:50


//declaring scales for accuracy
public class Model {
    public static UrgencyAccuracy HIGH_SCALE = new HighAcc();
    public static UrgencyAccuracy LOW_SCALE = new LowAcc();
    public static UrgencyAccuracy MED_SCALE = new MediumAcc();
    public static UrgencyAccuracy[] ACCURACY_GAUGE = {HIGH_SCALE, LOW_SCALE, MED_SCALE};


    //This is the only time the root should be visible for the purposes of selecting a parent project
    private Project rootProject =
            //default value is no parent, high scale
            Project.create("Select parent", " ", HIGH_SCALE).getObj();

    //Don't remove or jtree will get angry
    private MyTreeModel myTree = new MyTreeModel(rootProject);
    private TreeMap<String, Project> projects = new TreeMap<>();
    private TreeMap<String, Task> projs = new TreeMap<>();
    private HashSet<String> names = new HashSet<>();

    //Create a list of strings for errors.
    private DefaultListModel<String> errorModel = new DefaultListModel<>();
    //declaring current selected node
    private TreeNode selectedNode;
    //Vector containing the listeners for changing selected node
    private Vector<SelectedNodeChanged> selectionListeners = new Vector<>();

    public Model() {
        projects.put(" ", rootProject);
    }

    /**
     * Obtain the data for the tree model and return it as tree
     * @return the tree data
     */
    public TreeModel getTreeModel() {
        return myTree;
    }

    /**
     * This boolean checks whether or not a name is in use, and outputs an error to console if so
     * @param name name to test
     * @param anyNode references the abstract to see if any other nodes contain this name
     * @return true if there is an issue with the name, else false
     */
    private boolean checkNameUse(String name, AbstrNode.AnyNode anyNode) {
        ErrorCatch errorCatch = new ErrorCatch(CatchingBadData.isData(name,
                "No name.")); //Catchall, leave this in.

        if (!errorCatch.isEmpty()) {
            errorModel.addAll(errorCatch);
            return true;
        }
        if (names.contains(name)) { //if the list of name contains this entered name already, then print
            System.out.println("Duplication error."); //if the name isn't unique, this prints to console
            return true;
        }
        return false;  //otherwise don't worry about it
    }

    /**
     * Adding a project node. This passes it through the validation to see if it can be added or discarded.
     * @param name name input
     * @param description desc input
     * @param parent assigned parent
     * @param defaultScale assigned scale
     */
    //Stops bad stuff from going to tree branches please don't remove this bit
    public void addNode(String name, String description, Project parent, UrgencyAccuracy defaultScale) {
        errorModel.clear();
        CatchingBadData<Project> projectValid = Project.create(name, description, parent == null ?
                rootProject : parent, defaultScale); // is input valid for all these and not null
        if (projectValid.isData()) { //has it been confirmed ok
            final Project project = projectValid.getObj(); //project is a final now as we don't want to edit it
            projects.put(project.getName(), project);
            names.add(project.getName());
            myTree.executeNewNode(project); //all is good, so execute adding to the tree
        } else {
            System.out.println("Validation error at addNode."); // if it somehow messes up this will go to console
        }
    }

    /**
     * As above, but for task nodes
     * @param name name input
     * @param description desc input
     * @param parent assigned parent
     * @param defaultScale assigned scale
     */
    public void addTask(String name, String description, Project parent, UrgencyAccuracy defaultScale) { //new task
        errorModel.clear(); //flush any errors from console
        if (checkNameUse(name, AbstrNode.AnyNode.TASK)) return; //is the name in use?
        CatchingBadData<Task> taskBad = Task.create(name, description,
                51, defaultScale, parent); //does it have valid inputs for all of these?
        if (taskBad.isData()) {
            final Task task = taskBad.getObj();
            projs.put(task.getName(), task);
            names.add(task.getName());
            myTree.executeNewNode(task); //if it passed, you can add it to the tree
        } else {
            System.out.println("Validation error at addTask."); //if it messes up this will go to console
        }

    }

    /**
     * As above, but for due date information objects
     * @param date date input
     * @param hour hour input
     * @param minutes mins input
     * @param task assigned task
     * @param urgencyAccuracy assigned accuracy
     * @param urgency urgence input
     */
    public void addData(Calendar date, int hour, int minutes, Task task,
                        UrgencyAccuracy urgencyAccuracy, String urgency) {
        errorModel.clear();
        CatchingBadData<UrgencyAdd> urgValid =
                //what a valid one should look like
                UrgencyAdd.create(date, hour, minutes, task, urgencyAccuracy, urgency);
        if (urgValid.isData()) { //if what we have is a valid one
            final UrgencyAdd dataNode = urgValid.getObj();
            myTree.executeNewNode(dataNode); //put it on the tree
        } else {
            System.out.println("Data entry error at addData."); //print to console
        }
    }

    //references that need to be public, don't delete

    /**
     * Returns a tree map of the projects
     * @return treemap
     */
    public TreeMap<String, Project> getProjects() {
        return projects;
    }

    /**
     * returns the combo box model for project selection
     * @return box
     */
    public ComboBoxModel<Project> getPComboBox() {
        return new ProjsComboModel(this);
    }

    /**
     * returns the combo box model for task selection
     * @return box
     */
    public ComboBoxModel<Task> getTComboBox() {
        return new TasksComboModel(this);
    }

    /**
     * Creates an array for projects/tasks
     * @return the values into an array
     */
    Task[] getProjs() {
        return projs.values().toArray(new Task[0]);
    }


    /**
     * Change the selected node when a listener detects it
     * @param selectedNode the node the user selects
     */
    public void setSelected(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
        for (SelectedNodeChanged treeModListenr : selectionListeners) {
            treeModListenr.selectionChanger(selectedNode);
        }
    }

    /**
     * For deleting a tree entry
     * @return true for deletion
     */
    public boolean deleteTreeEntry() {

        AbstrNode parent = (AbstrNode) selectedNode.getParent();
        final boolean success = parent.deleteChild(selectedNode);
        myTree.executeNewNode(parent);
        return success;
    }

    /**
     * Adds a listener
     * @param listener the listener to add
     */
    public void addSelectionListener(SelectedNodeChanged listener) {
        selectionListeners.add(listener);

    }


    public void modifyProject(String name, String description, Project paren, UrgencyAccuracy defaultScale) {
        //unused
    }

    /**
     * List string of potential error messages
     * @return list of potential errors
     */
    public ListModel<String> errorMessages() {
        return errorModel;
    }
}
