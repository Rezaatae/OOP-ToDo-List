package model.data;

import model.Model;
import org.junit.jupiter.api.Test;

import javax.swing.tree.TreeModel;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//Author Rez
//Last checked 22 oct 13:59

public class AddnodeModelTest {

    /*
     Given that I have a model with no projects
     When I add a project ("Gym", "Cardio", no parent, Medium Scale)
     Then this should be in the region tree attached to the root node
    */
    @Test
    public void addValidNode(){
        Model model = new Model();
        model.addNode("Treadmill", "Cardio", null, Model.MED_SCALE);
        TreeModel treeModel = model.getTreeModel();
        Project root = (Project) treeModel.getRoot();
        assertEquals(1, root.getChildCount());
        assertEquals(1, treeModel.getChildCount(root));

    }


    /*
    Given I add a "Gym" project with no parent
    And I add a "oop" project with no parent
    And I add a "uI" project as a child of "oop"
    When I add a "meeting" project with no parent
    Then I expect there to be 3 root projects
    And I expect the projects to have the right names
    And I expect the "Gym" project to have no children
    And I expect the "oop" project to have 1 child
    And I expect the "oop" project have the "uI" as its child project
    And I expect all except the root project and the "oop" project to be reported as leaf nodes
     */
    @Test
    public void verifyProjectTree(){
        final Model model = new Model();
        TreeModel treeModel = model.getTreeModel();
        Project root = (Project) treeModel.getRoot();
        model.addNode("Gym", "Cardio", root, Model.MED_SCALE);
        Project gym = (Project) treeModel.getChild(root, 0);
        model.addNode("OOP", "project", root, Model.HIGH_SCALE);
        Project oop = (Project) treeModel.getChild(root, 0);
        model.addNode("User Interface", "to-do list", oop, Model.MED_SCALE );
        Project uI =  (Project) treeModel.getChild(oop, 0);
        model.addNode("Meeting", "Campus", root, Model.HIGH_SCALE);
        Project meeting = (Project) treeModel.getChild(root, 2);

        assertEquals(3, treeModel.getChildCount(root));
        assertEquals("Gym", gym.getName());
        assertEquals("OOP", oop.getName());
        assertEquals("User Interface", uI.getName());
        assertEquals(0, treeModel.getChildCount(gym));
        assertEquals(1, treeModel.getChildCount(oop));
        assertEquals(uI, treeModel.getChild(oop, 0));
        assertFalse(treeModel.isLeaf(root));
        assertTrue(treeModel.isLeaf(gym));
        assertFalse(treeModel.isLeaf(oop));
        assertTrue(treeModel.isLeaf(uI));
        assertTrue(treeModel.isLeaf(meeting));
        assertEquals(0, treeModel.getIndexOfChild(root, oop));


    }
}