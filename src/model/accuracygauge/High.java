//dependency
package model.accuracygauge;
//responsibility of: Beckett

//edited by: Beckett
//Last checked 22 Oct 04:32 AM

//defining what High is
public class High implements Accuracy {
    private double accuracy;

    /**
     * Holds a high accuracy value as a double number
     * @param accuracy the accuracy value
     */
    public High(double accuracy) {
        this.accuracy = accuracy;
    }


    //Ignore the other stuff in accuracy interface as we did not get chance to create a conversion scale
    @Override
    public double getHigh() {
        return 0;}
    @Override
    public double getLow() {
        return 0;}
    @Override
    public double getMed() {
        return 0;}

}

