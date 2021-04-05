package view;
import javax.swing.*;
import java.awt.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

//edited by: Rez
//Last checked 22 Oct: 5:00

public class ErrorPanel extends JPanel {
    public static final int MIN_WIDTH = 100;
    public static final int MIN_HEIGHT = 100;

    private final JLabel panelLabel;
    private final JScrollPane scrollPane;

    public ErrorPanel(int width, int height, ListModel<String> errorMessages) {
        setLayout(null);
        this.setSize(330, 100);
        this.setBorder(BorderFactory.createLineBorder(Color.PINK));
        this.setFocusable(false);

        panelLabel = new JLabel("Errors", SwingConstants.CENTER);
        panelLabel.setBounds(5, 2, width , 15);
        panelLabel.setForeground(Color.PINK);
        panelLabel.setFocusable(false);
        this.add(panelLabel);

        new JList<>(errorMessages).setFocusable(false);

        scrollPane = new JScrollPane(VERTICAL_SCROLLBAR_AS_NEEDED,
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setView(new JList<>(errorMessages));
        scrollPane.setBounds(5, 20, width - 10, height - 25);
        this.add(scrollPane);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelLabel.setSize(this.getWidth(), 15);
        scrollPane.setSize(this.getWidth() - 10, this.getHeight());
    }
}
