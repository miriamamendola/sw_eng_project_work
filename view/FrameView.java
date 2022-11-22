package view;

import javax.swing.*;
import java.awt.*;


public class FrameView {


    public static void createView(){
        JFrame frame = new JFrame("Programma di disegno");
        frame.setSize(600,600);
        CanvasView canvas = new CanvasView();
        frame.add(canvas);
        JPanel vPanel = new JPanel();
        BoxLayout boxButton = new BoxLayout(vPanel, BoxLayout.Y_AXIS);
        vPanel.setLayout(boxButton);
        vPanel.add(new JButton(new ImageIcon("linea.png")));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        vPanel.add(new JButton(new ImageIcon("rectangle.png") ));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        vPanel.add(new JButton(new ImageIcon("ellipse.png") ));
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        frame.add(vPanel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//making the frame visible

    }
}