//dependency
package model.accuracygauge;
//Responsibility of Beckett

//edited by Beckett
// Last checked 22 Oct 04:35

//defining what medium is
public class Medium implements Accuracy {
    private double accuracy;

    /**
     * Holds the medium value as a double
     * @param accuracy the value to hold
     */
    public Medium(double accuracy) {
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