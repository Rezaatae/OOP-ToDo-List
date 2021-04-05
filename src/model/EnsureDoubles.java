//dependencies
package model;
import javax.swing.*;
//Responsibility of Huang, Remy

//edited by Beckett
//Last checked: 22 Oct 4:45 am

//Inputs should be doubles as opposed to ints as this allows for greater accuracy
public class EnsureDoubles extends InputVerifier {

    /**
     * The input to be verified i.e. can it be parsed as a double or not
     * @param input the input to test
     * @return false if we don't have a valid potential double, else true
     */
    @Override
    public boolean verify(JComponent input) {
        String inputText = ((JTextField) input).getText();
        try {
            Double.parseDouble(inputText); //can the input be read as a double?
        }
        catch (NumberFormatException ex) { //no, don't let it go through
            return false;
        }
        return true; //yes, it's fine let it go through
    }
}