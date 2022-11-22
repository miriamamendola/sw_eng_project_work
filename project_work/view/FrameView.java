package project_work.view;
import project_work.Context;

import javax.swing.*;
import java.awt.*;


public class FrameView {


    public static void createView(){
        JFrame frame = new JFrame("Drawing software");
        frame.setSize(600,600);
        CanvasView canvas = new CanvasView(); //create canvas for draw
        frame.add(canvas);
        JPanel vPanel = new JPanel();
        BoxLayout boxButton = new BoxLayout(vPanel, BoxLayout.Y_AXIS);      //create a "vbox " for  tools
        vPanel.setLayout(boxButton);
        vPanel.add(new JButton(new ImageIcon("assets/icons/cursor.png")));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        vPanel.add(new JButton(new ImageIcon("assets/icons/line.png")));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        vPanel.add(new JButton(new ImageIcon("assets/icons/rectangle.png") ));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        vPanel.add(new JButton(new ImageIcon("assets/icons/ellipse.png") ));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        frame.add(vPanel, BorderLayout.WEST);
        ImageIcon icon = new ImageIcon("assets/icons/palette.png");          //app icon
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//making the frame visible

    }
}