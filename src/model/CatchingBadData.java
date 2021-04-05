//dependencies
package model;
import java.util.Optional;
//Responsibility of Beckett, Huang, Remy

//Last checked: 22 Oct, 04:45 AM

//edited by Beckett

//CatchingBadData
//Based on the lecture stuff
//Please don't mess with it too much I based it mostly off temperaturerecording because I'm not too knowledgeable
// at error catchment - Beckett

public class CatchingBadData<T> {
    private T obj; //what we're testing
    private ErrorCatch error;

    /**
     * When we can return an object we are happy with, we don't need to do anything else with the object
     * @param obj the object of the test which is allowed to stay
     */
    public CatchingBadData(T obj) {
        this.obj = obj;
        error = new ErrorCatch();
    }

    /**
     * When we've found an error, we want to nullify the object to prevent creation
     * @param error the error has been found
     */
    //When we can return an object we have an error for
    public CatchingBadData(ErrorCatch error) {
        this.error = error;
        this.obj = null; //Make sure we dont save the bad object
    }


    //Errol's model contains this and for some reason it makes everything work.
    public Optional<T> get() {
        return Optional.ofNullable(obj);
    }
    public T getObj() {
        return obj;
    }

    /**
     * If there is data we want, do not nullify the object
     * @return object returned as not null
     */
    public boolean isData() {
        return obj != null;
    }

    /**
     * If there is no data, ensure the object is nullified
     * @return the object is null
     */
    public boolean isEmpty() {
        return obj == null;
    }

    /**
     * If there is no data, we want to set the length to 0 and return an error in the console
     * @param string the string we are testing
     * @param errorMessage the message to return in console
     * @return null
     */
    public static String isData(String string, String errorMessage) {
        if (string == null || string.trim().length() == 0) { return errorMessage; }
        return null;
    }

}
