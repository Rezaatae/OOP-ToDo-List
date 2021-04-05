//dependencies
package model.scaling;
import model.accuracygauge.Accuracy;
import model.accuracygauge.Low;
//Beckett, Remi

//The stuff in this package isn't fully utilised as we don't have enough time, but it still has some
//important stuff to do, so please leave it in.

//A class that uses the urgencyaccuracy interface
public class LowAcc implements UrgencyAccuracy {
   public double getUrgAcc(Accuracy num) { return num.getLow(); } //get low scale
    public Accuracy makeUrg(double urgency) { return new Low(urgency);} //make new urgency obj
    public String toString() {
        return "Low";
    } //append the right label
}
