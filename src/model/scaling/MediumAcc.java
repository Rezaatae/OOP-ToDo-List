//dependency
package model.scaling;

import model.accuracygauge.Accuracy;
import model.accuracygauge.Medium;
//Beckett, Remi

//The stuff in this package isn't fully utilised as we don't have enough time, but it still has some
//important stuff to do, so please leave it in.

//A class that uses the urgencyaccuracy interface
public class MediumAcc implements UrgencyAccuracy {
    public double getUrgAcc(Accuracy num) {
        return num.getMed();
    } //get med scale
    public Accuracy makeUrg(double urgency) {
        return new Medium(urgency);
    } //make a new urgency obj
    public String toString() {
        return "Medium";
    } //append the right label
}
