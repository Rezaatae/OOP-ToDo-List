//Dependencies
import model.Model;
import view.TodoListFrame;
import javax.swing.*;

//Responsible: Rez

//edited by: Rez
//Last checked 22 Pct 5:34


//Start here!
public class Start {
    /**
     * Creates an instance of TodoListFrame in which the program runs.
     * @param args paramters passed to the program when it opens
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> new TodoListFrame(new Model()));
    }
}
