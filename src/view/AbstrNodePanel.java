//dependencies
package view;

import model.Model;
import model.ProjsComboModel;
import model.data.Project;
import model.scaling.UrgencyAccuracy;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
//reponsibility: rez, beckett


//edited by: Rez, Beckett
//Last checked 22 Oct 5:39

//creating the abstract node panel class which extends jpanel
public abstract class AbstrNodePanel extends JPanel {
    static int FIELD_START = 100; //What does this line do? can it be removed? - Beckett is curious
    protected final Model model; //a panel incorporates the model
    protected final JTextField nameEntry; //a panel needs space to put the name
    protected final JTextField descEntry; //a panel needs space to put the description
    private final JComboBox<Project> parentEntry; //a panel needs a box to pick the project parent
    private final JComboBox<UrgencyAccuracy> accScaleEntry; //a panel needs a box to pick your accuracy
    protected final JButton addBut; //a panel needs an add button
    private final JButton deleteButton;
    private final JButton modifyButton;
    private Label descriptionEntry; //currently unused
    private ProjsComboModel projectEntry;
    private TreeNode selectedItem;


    //setting layout of this abstract
    public AbstrNodePanel(String title, Model model, int width, int height, Color colour) {
        this.model = model;
        setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.pink)); //Everything will be pink.

        //the label at the top of a panel should be like this as a default
        JLabel panelLabel = new JLabel(title, SwingConstants.CENTER);
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(colour);
        panelLabel.setFocusable(false);
        this.add(panelLabel);
        //the labels we will be using
        JLabel nameLabel = new JLabel("Name"); //name entry
        nameLabel.setBounds(5, 20, FIELD_START - 2, 15);
        nameLabel.setFocusable(false);
        this.add(nameLabel);
        nameEntry = new JTextField();
        nameEntry.setBounds(FIELD_START, 18, 200, 20);
        this.add(nameEntry);
        JLabel descLabel = new JLabel("Desc"); //desc entry
        descLabel.setBounds(5, 40, FIELD_START - 2, 15);
        descLabel.setFocusable(false);
        this.add(descLabel);
        descEntry = new JTextField();
        descEntry.setBounds(FIELD_START, 38, 340, 20);
        this.add(descEntry);
        JLabel parentLabel = new JLabel("Parent"); //parent entry
        parentLabel.setBounds(5, 60, FIELD_START - 2, 15);
        parentLabel.setFocusable(false);
        this.add(parentLabel);
        parentEntry = new JComboBox<>();
        parentEntry.setModel(model.getPComboBox());
        parentEntry.setBounds(FIELD_START, 58, 200, 20);
        this.add(parentEntry);
        JLabel scaleLabel = new JLabel("Accuracy"); //default accuracy entry
        scaleLabel.setBounds(5, 80, FIELD_START - 2, 15);
        scaleLabel.setFocusable(false);
        this.add(scaleLabel);
        accScaleEntry = new JComboBox<>(Model.ACCURACY_GAUGE);
        accScaleEntry.setBounds(FIELD_START, 78, 200, 20);
        this.add(accScaleEntry);

        //this is what the button looks like
        addBut = new JButton("+"); //add button
        addBut.setBounds(50, height - 22, 60, 20);
        addBut.setEnabled(true);
        addBut.addActionListener(this::addNode);
        this.add(addBut);
        modifyButton = new JButton("Mod");
        modifyButton.setBounds(150, height - 22, 70, 20);
        modifyButton.setEnabled(true);
        modifyButton.addActionListener(this::changeNode);
        this.add(modifyButton);

        deleteButton = new JButton("Del");
        deleteButton.setBounds(250, height - 22, 70, 20);
        deleteButton.setEnabled(true);
        deleteButton.addActionListener(this::deleteSelectedItem);
        this.add(deleteButton);

        model.addSelectionListener(this::treeSelectionChanged);

    }

    abstract void treeSelectionChanged(TreeNode treeNode);

    private void deleteSelectedItem(ActionEvent actionEvent) {
        model.setSelected(selectedItem);
        model.deleteTreeEntry();
    }

    private void changeNode(ActionEvent actionEvent) {
        String name = nameEntry.getText();
        String description = descriptionEntry.getText();
        Project parent = (Project) projectEntry.getSelectedItem();
        changeNode(name, description, parent, Model.MED_SCALE);
        model.addSelectionListener((SelectedNodeChanged) selectedItem);

    }

    protected abstract void changeNode(String name, String description, Project parent, UrgencyAccuracy medScale);

    //the action event when the + button is pressed
    private void addNode(ActionEvent e){ //act upon new nodes added
        String name = nameEntry.getText(); //get all this data from the entry form
        String description = descEntry.getText();
        Project parent = (Project) parentEntry.getSelectedItem();
        UrgencyAccuracy defaultScale = (UrgencyAccuracy) accScaleEntry.getSelectedItem();
        addNode(name, description, parent, defaultScale); //add it to the tree
    }
    protected abstract void addNode(String name, String description, Project parent,
                                    UrgencyAccuracy defaultScale);

}