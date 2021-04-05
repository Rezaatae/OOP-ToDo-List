//dependencies
package view;

import model.EnsureDoubles;
import model.Model;
import model.data.Task;
import model.scaling.UrgencyAccuracy;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
//responsibility of Rez
//edited by: Beckett, Rez

//Last checked: 22 Oct 05:47


//I like the JDatePicker Errol showed us more than the prospect of having to add data information manually
//To add this library to your own machine do the following
//File > Project Structure > Libraries > + > Maven > Search and add jdatepicker

//Basic constructor for this panel
public class UrgencyPanel extends JPanel {
    public static final int MIN_WIDTH = 400;
    public static final int MIN_HEIGHT = 125;
    private static final int FIELD_START = 100;
    //This should be a combobox to allow you to choose the task from the list
    private final JComboBox<Task> taskEntry;
    //Here's that nice calendar we imported!
    private final UtilCalendarModel calModel;
    //Setting hours and minutes of the due time
    private final JSpinner hour;
    private final JSpinner minutes;
    //Gauging accuracy of your input and adding the urgency, picking from high/med/low accuracy
    private final JComboBox<UrgencyAccuracy> accuracyEntry;
    private final JTextField urgEntry;
    //Model
    private final Model model;


    //Setting how we want the panel to look. It would be nice to have a consistent colour scheme.
    //Errol said we could take his default layout style for panels, so I did mostly that with some tweaks.
    public UrgencyPanel(String title, int width, int height, Color colour,
                        Model model) {
        this.model = model;
        setLayout(null);
        this.setSize(Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height));
        this.setBorder(BorderFactory.createLineBorder(Color.pink));
        JLabel panelLabel = new JLabel(title, SwingConstants.CENTER); //sets title (see below_
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(Color.pink);
        panelLabel.setFocusable(false);
        this.add(panelLabel); //adding a label
        JLabel dateLabel = new JLabel("Time/Date");
        dateLabel.setBounds(5, 20, FIELD_START - 2, 15);
        dateLabel.setFocusable(false);
        this.add(dateLabel);
        calModel = new UtilCalendarModel(); //adding a calendar
        JDatePanelImpl datePanel = new JDatePanelImpl(calModel);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(FIELD_START, 15, 150, 24);
        this.add(datePicker);
        //This sets out layout for spinners (where time data is added)
        hour = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        hour.setBounds(FIELD_START + 150, 17, 50, 20);
        this.add(hour);
        JLabel colon = new JLabel(":");
        colon.setBounds(FIELD_START + 202, 17, 5, 20);
        colon.setFocusable(false);
        this.add(colon);
        minutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        minutes.setBounds(FIELD_START + 208, 17, 50, 20);
        this.add(minutes);

        //This is the combobox where the task to apply the urgency to is selected
        JLabel taskLabel = new JLabel("Task");
        taskLabel.setBounds(5, 40, FIELD_START - 2, 15);
        taskLabel.setFocusable(false);
        this.add(taskLabel);
        taskEntry = new JComboBox<>();
        taskEntry.setModel(model.getTComboBox());
        taskEntry.setBounds(FIELD_START, 40, 200, 20);
        this.add(taskEntry);

        //Choose the scale of your accuracy from the dropdown menu.
        //Figuring this out took me a while, so I set the aesthetic look of it based on what Errol's example
        //of the accuracy scales looked like.
        JLabel scaleLabel = new JLabel("Accuracy");
        scaleLabel.setBounds(5, 60, FIELD_START - 2, 15);
        scaleLabel.setFocusable(false);
        this.add(scaleLabel);
        accuracyEntry = new JComboBox<>(Model.ACCURACY_GAUGE);
        accuracyEntry.setBounds(FIELD_START, 58, 200, 20);
        this.add(accuracyEntry);
        //And here is where the user inputs their relative importance (urgency)
        //Had to play around with the layout here to fit the whole word in
        JLabel urgencyLabel = new JLabel("Importance");
        urgencyLabel.setBounds(0, 80, FIELD_START - 10, 15);
        urgencyLabel.setFocusable(false);
        this.add(urgencyLabel);
        urgEntry = new JTextField();
        urgEntry.setHorizontalAlignment(SwingConstants.RIGHT);
        urgEntry.setBounds(FIELD_START, 80, 100, 20);
        urgEntry.setInputVerifier(new EnsureDoubles());
        this.add(urgEntry);


        //Button to add the data
        JButton add = new JButton("+");
        add.setBounds(50, Math.max(height, MIN_HEIGHT) - 22, 60, 20);
        add.addActionListener(this::dataEntryEvent); //listener to make the button work
        this.add(add);

        //Button to delete the data
        JButton del = new JButton("-");
        del.setBounds(150, Math.max(height, MIN_HEIGHT) - 22, 60, 20);
        del.addActionListener(this::dataRemovalEvent);
        this.add(del);


    }

   //The program needs to be aware of the current selections impact on the tree
    private void dataEntryEvent(ActionEvent e) { //Defining what constitutes a data entry event
        Calendar date = calModel.getValue(); // get the date entry
        int hour = (Integer) this.hour.getValue(); // get the hour time
        int minutes = (Integer) this.minutes.getValue(); // get the mins time
        Task task = (Task) taskEntry.getSelectedItem(); // get the selected current task
        UrgencyAccuracy urgencyAccuracy =
                (UrgencyAccuracy) accuracyEntry.getSelectedItem(); //get the accuracy entry
        String urgency = urgEntry.getText(); // get the urgency value entry
        model.addData(date, hour, minutes, task, urgencyAccuracy, urgency); //adds data to the tree node
    }

    //testing removal button
    private void dataRemovalEvent(ActionEvent e) {
        Calendar date = calModel.getValue();
        int hour = (Integer) this.hour.getValue();
        int minutes = (Integer) this.minutes.getValue();
        Task task = (Task) taskEntry.getSelectedItem();
        UrgencyAccuracy urgencyAccuracy =
                (UrgencyAccuracy) accuracyEntry.getSelectedItem();
        String urgency = urgEntry.getText();
        //put line to delete stuff here
    }
}
