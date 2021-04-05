//Dependencies
package view;
import model.Model;
import model.data.Project;
import model.scaling.UrgencyAccuracy;

import javax.swing.tree.TreeNode;
import java.awt.*;
//Rez, Ugonna

//Author: Rez

//edited by: Rez
//Last checked: 5:45

//Constructing the projects panel as an ext of our abstract class
public class ProjectsPanel extends AbstrNodePanel {
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 125;
    //Default aesthetics etc etc
    public ProjectsPanel(String title, Model model, int width, int height, Color colour) {
        super(title, model, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), Color.pink);
    }

    @Override
    void treeSelectionChanged(TreeNode treeNode) {

    }

    @Override
    protected void changeNode(String name, String description, Project parent, UrgencyAccuracy medScale) {

    }


    @Override //updating when a new node is added
    protected void addNode(String name, String description, Project parent, UrgencyAccuracy defaultScale) {
        model.addNode(name, description, parent, defaultScale);
    }

}
