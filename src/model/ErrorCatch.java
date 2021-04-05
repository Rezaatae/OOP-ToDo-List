//dependencies
package model;
import java.util.ArrayList;
//Responsibility of Huang, Remy

//edited by Beckett

//Last checked: 22 Oct 04:47


//based on the validation lecture stuff
//feel free to remove if it's breaking more important things
public class ErrorCatch extends ArrayList<String> { //The list of potential clashes is stores as a list of strings
    //It needs to be public as it gets called on elsewhere

    /**
     * A string called err belongs to an object named ErrorCatch
     * @param err if the error is not null i.e. there is an error detected, include it
     */
    public ErrorCatch(String err) {
        super();
        if (err != null && !err.equals("")) {
            this.add(err);
        }
    } //what does this bit do?
    public ErrorCatch() {
        super();
    }

    /**
     * Boolean checks for addition of error
     * @param error the error whose existence we are checking
     * @return false
     */
    @Override
    public boolean add(String error) {
        if (error != null && !error.equals("")) {
            return super.add(error);
        }
        return false;
    }

//Haven't implemented a full error console system like Errol's so I'm not sure how effective this all is
    //but we can still print some basic errors to the console for dev purposes
    public static ErrorCatch create(String error) {
        return new ErrorCatch(error);
    }
}