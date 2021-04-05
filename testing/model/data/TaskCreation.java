package model.data;
import model.CatchingBadData;
import model.Model;
import model.scaling.MediumAcc;
import model.scaling.UrgencyAccuracy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Authors: Rez, Beckett
//Last checked 22 Oct 04:36 AM
//Rez made this test case file
//Beckett changed some of the names to test the program handling letters, numbers, and punctuation

public class TaskCreation {

    private final Project project = Project.create("123abc", "!", Model.MED_SCALE).getObj();

     /*
    When I create a task with valid fields
    Then I expect it to be created
    And no error messages returned
    And the task name to match that which I used
    And the description to match that which I used
    And with a default urgency scale
     */
     @Test
     void locationCreation() {
         UrgencyAccuracy urgencyAccuracy = new MediumAcc();
         CatchingBadData<Task> validTask = Task.create("123abc", "!", 65, project.defaultScale, project);
         assertTrue(validTask.isData());
         Task task = validTask.getObj();
         assertEquals("123abc", task.getName());
         assertEquals("!", task.getDesc());
         //assertSame(urgencyAccuracy, task.defaultScale);
     }

}
