package view;
import javax.swing.*;
import java.awt.*;

//Unfinished


//edited by: Beckett
//Last checked: 22 Oct 5:43

class CompleteBox extends JPanel{
    public static final int MIN_WIDTH = 100;
    public static final int MIN_HEIGHT = 100;
    public final JLabel panelLabel;

    public CompleteBox(int width, int height) {
        setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.PINK));
        this.setFocusable(false);

        panelLabel = new JLabel(" ", SwingConstants.CENTER);
        panelLabel.setBounds(5, 2, width - 10, 15);
        panelLabel.setForeground(Color.PINK);
        panelLabel.setFocusable(false);
        this.add(panelLabel);

        JCheckBox c1 = new JCheckBox("Complete");
        this.add(c1);
        c1.setSize(150,50);
        
    }

    //aesthetic info for resizing the window
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelLabel.setSize(this.getWidth(), 15);
    }
}
    