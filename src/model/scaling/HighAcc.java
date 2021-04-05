//dependencies
package model.scaling;

import model.accuracygauge.Accuracy;
import model.accuracygauge.High;
//Beckett, Remi

//The stuff in this package isn't fully utilised as we don't have enough time, but it still has some
//important stuff to do, so please leave it in.

//a class using the urgencyaccuracyinterface
public class HighAcc implements UrgencyAccuracy {
    public double getUrgAcc(Accuracy num) {
        return num.getHigh();
    } //get high scale
    public Accuracy makeUrg(double urgency) {
        return new High(urgency);
    } //make new urgency obj
    public String toString() {
        return "High";
    } //append the right label
}