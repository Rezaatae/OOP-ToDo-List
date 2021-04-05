//dependencies
package model.accuracygauge;
//responsibility of: Beckett

//edited by: Beckett
// Last checked 22 Oct 04:31 AM

//This is where we would have put all the stuff to calculate accuracy if we had time for the stretch goal
//As it stands we can declare some behaviours for our accuracies anyway
//These strings are unused but I feel it's worth leaving them in as a proof of concept
public interface Accuracy {
    String HIGH = "(high)";
    String LOW = "(low)";
    String MED = "(med)";

    //Returning scale data... not fully used but don't delete yet. Working on getting it out safely.
    double getHigh();
    double getLow();
    double getMed();

}

//Easter egg comment! :)