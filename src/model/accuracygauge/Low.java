//dependency
package model.accuracygauge;
//responsibility of Beckett

//edited by: Beckett
//Last checked: 22 Oct 04:33

//defining what low is
public class Low implements Accuracy {
    private double accuracy;

    /**
     * Holds the low value as a double
     * @param accuracy the value to hold
     */
    //holds a low value which cant be changed
    public Low(double accuracy) {
        this.accuracy = accuracy;
    }


    //Ignore the other stuff in accuracy interface as we did not get chance to create a conversion scale
    @Override
    public double getHigh() {
        return 0;
    }
    @Override
    public double getLow() {
        return 0;
    }
    @Override
    public double getMed() {
        return 0;
    }
}
