//dependencies
package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
//responsibility of Ugonna, Rez, Beckett
//edited by: Beckett, Rez

//Last checked 22 oct 05:46

//defining the frame for the application to run in
public class TodoListFrame extends JFrame {
    private final int minimumWidth = //width is determined by the minimum width defined for the panels
            Math.max(ProjectsPanel.MIN_WIDTH, TasksPanel.MIN_WIDTH) + MyTreePanel.MIN_WIDTH;
    private final int minimumHeight = //same applies for height
            Math.max(ProjectsPanel.MIN_HEIGHT + TasksPanel.MIN_HEIGHT + UrgencyPanel.MIN_HEIGHT
                    + TasksPanel.MIN_HEIGHT, MyTreePanel.MIN_HEIGHT + 50); //bc the errors is too big
    private int height = 700; //these are just default values... this size is nice I like it square
    private int width = 700;
    //i like the ordering of nodes from errols example so I don't think there's anything wrong with being similar
    private MyTreePanel nodes;
    private ProjectsPanel projEntry;
    private TasksPanel taskEntry;
    private UrgencyPanel urgEntry;
    private view.CompleteBox CompleteBox;
    private ErrorPanel errorPanel;

    //Constructing this large frame

    /**
     * Establishes an instance in which the program can be executed
     * @param model
     * @throws HeadlessException
     */
    public TodoListFrame(Model model) throws HeadlessException {
        super("Todo List 1.0"); //the window title
        setDefaultBehaviour();
        initFields(model);
        this.setVisible(true); //we want to see it
    }

    //Basic aesthetic info, and behaviour such as making sure the app closes when we press X
    private void setDefaultBehaviour() {
        getContentPane().setLayout(null); //don't edit this swing has sufficient default layout for us already
        getContentPane().setBackground(Color.WHITE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //factor in screen size
        this.setMinimumSize(new Dimension(minimumWidth, minimumHeight));
        this.setMaximumSize(new Dimension(screenSize.width - 50, screenSize.height - 50));
        if (height > screenSize.height) { //adjusting to fit in the screen
            height = screenSize.height;
        }
        if (width > screenSize.width) {
            width = screenSize.width;
        }
        this.setLocation((screenSize.width - width) / 2, //middle of the screen on startup
                (screenSize.height - height) / 2);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //program exits upon close
        this.setResizable(true);
        this.setFont(new Font(Font.SERIF, Font.PLAIN, 14)); //why wont my serif font work :(
    }

    /**
     * Initializing the fields and adding content to them
     * @param model
     */
    private void initFields(Model model) {
        Container pane = this.getContentPane();
        final int rightColumnWidth = Math.max(ProjectsPanel.MIN_WIDTH, //calc right width of panels
                TasksPanel.MIN_WIDTH);
        final int rightColumnStart = this.width - rightColumnWidth - 5;

        nodes = new MyTreePanel("Todo", model,
                rightColumnStart - 10,height - 30, Color.PINK);
        nodes.setLocation(5, 5);
        nodes.setFocusable(false);
        pane.add(nodes); //insert this pane

        projEntry = new ProjectsPanel("Project", model, rightColumnWidth,
                ProjectsPanel.MIN_HEIGHT, Color.PINK);
        projEntry.setLocation(rightColumnStart, 5);
        projEntry.setFocusable(false);
        pane.add(projEntry); //insert this pane

        taskEntry = new TasksPanel("Task", model, rightColumnWidth,
                TasksPanel.MIN_HEIGHT, Color.PINK);
        taskEntry.setLocation(rightColumnStart, ProjectsPanel.MIN_HEIGHT + 7);
        taskEntry.setFocusable(false);
        pane.add(taskEntry); //insert this pane

        urgEntry = new UrgencyPanel("Due By",
                rightColumnWidth, UrgencyPanel.MIN_HEIGHT, Color.PINK, model);
        urgEntry.setLocation(rightColumnStart,
                ProjectsPanel.MIN_HEIGHT + TasksPanel.MIN_HEIGHT + 9);
        urgEntry.setFocusable(false);
        pane.add(urgEntry); //insert this pane

        final int completeBoxY =
                ProjectsPanel.MIN_HEIGHT + TasksPanel.MIN_HEIGHT + UrgencyPanel.MIN_HEIGHT +11;
        CompleteBox = new CompleteBox(100,100);
        CompleteBox.setLocation(rightColumnStart, completeBoxY);
        CompleteBox.setFocusable(false);
        this.add(CompleteBox);

        final int errorPanelY =
                ProjectsPanel.MIN_HEIGHT + TasksPanel.MIN_HEIGHT + UrgencyPanel.MIN_HEIGHT +11;
        errorPanel = new ErrorPanel(rightColumnWidth, height - errorPanelY - 29,
                model.errorMessages());
        errorPanel.setLocation(rightColumnStart + 105, errorPanelY);
        errorPanel.setFocusable(false);
        this.add(errorPanel);


    }


    //don't mess with this, it sorts out how the program behaves when resized
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        setBackground(Color.magenta);
        if (this.getHeight() != height || this.getWidth() != width) {
            height = this.getHeight();
            width = this.getWidth();
            final int rightColumnWidth = Math.max(ProjectsPanel.MIN_WIDTH,
                    TasksPanel.MIN_WIDTH);
            final int rightColumnStart = this.width - rightColumnWidth - 5;
            nodes.setSize(rightColumnStart - 10, height - 30);
            projEntry.setLocation(rightColumnStart, 5);
            taskEntry.setLocation(rightColumnStart, ProjectsPanel.MIN_HEIGHT + 7);
            urgEntry.setLocation(rightColumnStart,
                    ProjectsPanel.MIN_HEIGHT + TasksPanel.MIN_HEIGHT + 9);
            final int errorPanelY = CompleteBox.getY();
            CompleteBox.setBounds(rightColumnStart, errorPanelY, 100,
                    errorPanelY);
            errorPanel.setBounds(rightColumnStart + 105, errorPanelY, rightColumnWidth,
                    errorPanelY);;
        }
    }
}
