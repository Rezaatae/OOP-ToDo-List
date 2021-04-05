//dependencies
package view;
import model.Model;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION;
//Responsibility of Ugonna, Beckett
//edited by: Beckett

//Last checked 22 Oct 5:44

//constructing the panel where the tree will go
public class MyTreePanel extends JPanel {
    public static final int MIN_WIDTH = 200;
    public static final int MIN_HEIGHT = 150; //smaller if possible though
    private final JLabel panelLabel;
    private final JScrollPane scroll;
    private Model model;
    private JTree myTree;
    private JButton modifyButton;
    private TreeNode selectedItem;
    private JButton deleteButton;
    private JButton findButton;


    //setting the aesthetics of this panel
    public MyTreePanel(String title, Model model, int width, int height, Color colour) {
        this.model = model;
        this.setLayout(null);
        this.setSize(width, 500); //just dont resize the window please or the buttons will be half eaten
        this.setBorder(BorderFactory.createLineBorder(Color.pink));
        panelLabel = new JLabel(title, SwingConstants.CENTER);
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(colour);
        panelLabel.setFocusable(false);
        this.add(panelLabel);

        JTree myTree = new JTree(model.getTreeModel());
        myTree.setRootVisible(false); //user should not be able to see the root
        scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //should be able to scroll if required for long stuff
        scroll.getViewport().setView(myTree);
        //I looked at Errol's default for the following as I'm not sure any other way to make it look
        myTree.getSelectionModel().setSelectionMode(SINGLE_TREE_SELECTION); //only select 1 at a time
        myTree.addTreeSelectionListener(this::nodeSelection);
        scroll.setBounds(5, 19, width - 10, height - 43); //setting bounds of the scroll
        this.add(scroll);

        int middle = width / 2;

        modifyButton = new JButton("Mod");
        modifyButton.setBounds(middle - 95, height - 22, 70, 20);
        modifyButton.setEnabled(true);
        modifyButton.addActionListener(this::changeNode);
        this.add(modifyButton);

        findButton = new JButton("Find");
        findButton.setBounds(middle - 30, height - 22, 70, 20);
        //find.addActionListener(this.findRegionLocation);
        this.add(findButton);

        deleteButton = new JButton("Del");
        deleteButton.setBounds(middle + 35, height - 22, 70, 20);
        deleteButton.setEnabled(true);
        deleteButton.addActionListener(this::deleteSelectedItem);
        this.add(deleteButton);
    }



    private void deleteSelectedItem(ActionEvent actionEvent) {
        model.setSelected(selectedItem);
        model.deleteTreeEntry();
    }

    private void changeNode(ActionEvent actionEvent) {
        model.setSelected(selectedItem);

    }


    private void nodeSelection(TreeSelectionEvent treeSelectionEvent) { //behaviour for selecting nodes
    }

    public void treeSelection(TreeSelectionEvent treeSelectionEvent) {
        selectedItem = (TreeNode) myTree.getLastSelectedPathComponent();
        modifyButton.setEnabled(selectedItem != null);
        deleteButton.setEnabled(selectedItem != null);
    }



    @Override //Don't mess with this, it makes sure the program acts normally when the window is resized.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int width = this.getWidth();
        int middle = width / 2;
        panelLabel.setSize(width, 15);
        scroll.setSize(width - 10, this.getHeight() - 43);
        modifyButton.setLocation(middle - 95, this.getHeight() - 22);
        findButton.setLocation(middle - 30, this.getHeight() - 22);
        deleteButton.setLocation(middle + 35, this.getHeight() - 22);
    }
}
