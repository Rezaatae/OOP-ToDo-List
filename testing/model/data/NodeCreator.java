package model.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Model;

//Author: Rez
//Last checked: 22 Oct 04:37


class NodeCreator {
    /*
          Given I have no project or task
          When I create a project "Gym" with description "Cardio" with no parent project
          Then I expect the project to be created
          And the project's name to be "Gym"
          And the project's description to be "Cardio"
          And the project to have no parent
          And the default urgency scale is the Medium scale
        */
    @Test
    public void verifyProjectCreation(){
        Project project = Project.create("Gym", "Cardio", Model.MED_SCALE).getObj();
        assertEquals("Gym", project.getName());
        assertEquals("Cardio", project.getDesc());
        assertNull(project.getParent(), "");
        assertSame(Model.MED_SCALE, project.defaultScale);
    }

    /*
   Given I have project Gym ("Gym", "Cardio")
   When I add a child task UK ("Treadmill", "30 mins") to Gym
   Then I expect Gym to have Treadmill as a child
   And I expect the Treadmill to have Gym as parent
   */
    @Test
    void verifyAddingOfParent() {
        Project parent = Project.create("Gym", "Cardio", Model.MED_SCALE).getObj();
        Project child =  Project.create("Treadmill", "30 mins", parent, Model.MED_SCALE).getObj();
        assertNotNull(child.getParent());
        assertSame(parent, child.getParent());
        assertTrue(child.isLeaf());
        assertFalse(parent.isLeaf());
        assertEquals(1, parent.getChildCount());
        assertEquals(0, child.getChildCount());
    }

    /*
    Given I have project Gym ("Gym", "Cardio")
    When I add a child project Treadmill ("Treadmill", "30 mins") to Gym
    When I add a child project PersonalBest ("PersonalBest", "40 mins") to Treadmill
    Then I expect Gym to have Treadmill as a child
    And I expect the Treadmill to have Gym as parent
    And I expect PersonalBest to have treadmill as a parent
    */
    @Test
    void verifyAddingOfASecondLayer() {
        Project parent = Project.create("Gym", "Cardio", Model.MED_SCALE).getObj();
        Project child =  Project.create("Treadmill", "30 mins", parent, Model.MED_SCALE).getObj();
        Project personalBest = Project.create("PersonalBest", "40 mins", child, Model.MED_SCALE).getObj();
        assertNotNull(child.getParent());
        assertSame(parent, child.getParent());
    }

}