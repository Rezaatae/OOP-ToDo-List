//dependencies
package model.data;

import model.CatchingBadData;
import model.ErrorCatch;
import model.accuracygauge.Accuracy;
import model.scaling.UrgencyAccuracy;

import javax.swing.tree.TreeNode;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Enumeration;
// responsibility of Huang, Remi

//edited by Beckett

//Last checked 22 Oct 04:22

//this is the class that collates the date, time, task
//its not utilised fully due to the lack of time to implement a conversion scale for grading tasks
public class UrgencyAdd implements TreeNode {
    private Calendar date; //urgency involves a date
    private int hours; //and a hour in which the due time resides
    private Task task; //and a specified task
    protected Accuracy accuracy; //and the selected accuracy

    /**
     * Constructing an urgency object
     * @param date The due date specified in the calendar
     * @param hours The hour in which it is due
     * @param task The task this belongs to
     * @param accuracy The value for urgency
     */
    UrgencyAdd(Calendar date, int hours, Task task, Accuracy accuracy) {
        this.date = date;
        this.hours = hours;
        this.task = task;
        this.accuracy = accuracy;
        task.add(this);
    }

    /**
     * Create a string to display what time the project is due
     * @return created string
     */
    public String getTimeString() {
        return "Due in the hour of: " + hours + ":00";
    } //return rough due time

    //Tree stuff

    /**
     * Return whether or not children may be added to this
     * @return false as a Due object shouldn't have children
     */
    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    /**
     * Return whether or not this is a leaf i.e. childless
     * @return true if childless, else false
     */
    @Override
    public boolean isLeaf() {
        return true;
    }

    /**
     * Return the child at the chosen index
     * @param childIndex Index to seek
     * @return the child at that index
     */
    @Override //return child at stated index
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    /**
     * Returns how many children there are
     * @return number of children, should be 0
     */
    @Override //return no of children
    public int getChildCount() {
        return 0;
    }

    /**
     * return parent of this urgency object
     * @return the task that is the parent
     */
    @Override //return parent
    public TreeNode getParent() {
        return task;
    }

    /**
     * return the index of this node
     * @param node the node in question
     * @return 0 as there should not be a full index of these
     */
    @Override //return index no
    public int getIndex(TreeNode node) {
        return 0;
    }

    /**
     * Returns children as enum, should be null due to there being no children
     * @return null
     */
    @Override //returns children as enum
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    /**
     * Return string of due by information
     * @return the created string
     */
    public String toString() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date.getTime()) + " " +
                getTimeString() ;
    }

    /**
     * Construct an error reporting object for validating input data for the UrgencyAdd object
     * @param date input date
     * @param hour input hour
     * @param minutes input mins
     * @param task assigned parent
     * @param urgencyAccuracy assigned scale
     * @param urgency input urgency value
     * @return the result of error catching for the UrgencyAdd object
     */
    //Catch potential bad data and output an error code to the console
    //for dev purposes - good to know what went wrong!
    private static ErrorCatch parameterValid(Calendar date, int hour, int minutes, Task task,
                                             UrgencyAccuracy urgencyAccuracy, String urgency) {
        ErrorCatch errorCode = new ErrorCatch();
        //if the time makes no sense
        if (hour < 0 || hour > 23 || minutes < 0 || minutes > 59) {
            System.out.println("Invalid time was input");
        }
        //if there is no task selected
        if(task == null) System.out.println("Please select task");
        //if there is no accuracy selected
        if(urgencyAccuracy == null) System.out.println("Please select accuracy");
        try {
            Double.parseDouble(urgency);
        } catch (NumberFormatException e) {
            //or if accuracy is a bad input
            System.out.println("Accuracy must be number");
        }
        return errorCode;
    }

    /**
     * Construct a validation object for checking inputs in the UrgencyAdd object
     * @param date input date
     * @param hour input hour
     * @param minutes input mins
     * @param task assigned task
     * @param urgencyAccuracy assigned scale
     * @param urgency input urgency value
     * @return result of validation for UrgencyAdd object
     */
    //Construct method for rejecting or adding
    public static CatchingBadData<UrgencyAdd> create(Calendar date, int hour, int minutes,
                                                     Task task, UrgencyAccuracy urgencyAccuracy,
                                                     String urgency) {
        ErrorCatch errorCode = parameterValid(date, hour, minutes, task, urgencyAccuracy, urgency);
        if (errorCode.isEmpty()) {
            return new CatchingBadData<>(new UrgencyAdd(date, hour , task,
                    urgencyAccuracy.makeUrg(Double.parseDouble(urgency))));
        }
        return new CatchingBadData<>(errorCode);
    }
}
