//dependencies
package view;
import model.Model;
import model.data.Project;
import model.scaling.UrgencyAccuracy;

import javax.swing.tree.TreeNode;
import java.awt.*;
//Responsibility of Rez
//edited by: Rez

//Last checked 22 Oct 05:46

//constructing a tasks panel
public class TasksPanel extends AbstrNodePanel {
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 165;
    private Project treeNode;
    private Component addButton;
    private Component modifyButton;
    private Label descriptionEntry;


    public TasksPanel(String title, Model model, int width, int height, Color colour) {
        super(title, model, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), Color.pink);

    }

    @Override
    void treeSelectionChanged(TreeNode treeNode) {

    }

    @Override
    protected void changeNode(String name, String description, Project parent, UrgencyAccuracy medScale) {
        if (treeNode == null || treeNode.getClass() != Project.class) {
            addButton.setEnabled(true);
            addButton.setVisible(true);
            modifyButton.setVisible(false);
            modifyButton.setEnabled(false);
            System.out.println("Not modifying a project");
        } else {
            addButton.setEnabled(false);
            addButton.setVisible(false);
            modifyButton.setVisible(true);
            modifyButton.setEnabled(true);
            Project project = (Project) treeNode;
            nameEntry.setText(project.getName());
            descriptionEntry.setText(project.getDesc());
            System.out.println(treeNode);
        }
    }


    //Override for adding nodes
    @Override
    protected void addNode(String name, String description, Project parent, UrgencyAccuracy defaultScale) {
        model.addTask(name, description, parent, defaultScale);
    }


}
