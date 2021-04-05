//dependency
package model.scaling;
import model.accuracygauge.Accuracy;
//Beckett


//Interface for the accuracy scale. They would display the full accuracy label beside their node, and
    //would ideally show average urgency for the project, however we didn't get a chance to implement the
    //conversion scale or the display

//Anyway, it's left in for the sake of showing what we did. And some stuff still leans on it.

public interface UrgencyAccuracy {
    Accuracy makeUrg(double urgency); //creates an urgency object which is a double
    @Override
    String toString(); //would've displayed which level of accuracy is assigned to this urgency
    double getUrgAcc(Accuracy accuracy); //get number as a double, doesn't get called in this version of program
}