package project_work.view;
import project_work.Context;
import project_work.controller.CanvasController;
import project_work.controller.EllipseTool;
import project_work.controller.LineTool;
import project_work.controller.RectangleTool;

import javax.swing.*;
import java.awt.*;


public class FrameView {


    public static JFrame createView(){

    return createFrame();
    }
    private static JFrame createFrame(){
        CanvasController cc = new CanvasController();
        JPanel vPanel = new JPanel();
        CanvasView canvas = new CanvasView(); //create canvas for draw
        JFrame frame = new JFrame("Drawing software");

        frame.setSize(600,600);
        frame.add(canvas);
        BoxLayout boxButton = new BoxLayout(vPanel, BoxLayout.Y_AXIS);      //create a "vbox " for  tools
        vPanel.setLayout(boxButton);

        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton cursorButton = new JButton(new ImageIcon("assets/icons/cursor.png"));
        vPanel.add(cursorButton);
        cursorButton.addActionListener(actionEvent ->{});
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton lineButton = new JButton(new ImageIcon("assets/icons/line.png"));
        vPanel.add(lineButton);
        lineButton.addActionListener(actionEvent ->{
            cc.setCurrentTool(new LineTool(canvas));
            cc.executeTool();
        });
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton rectangleButton = new JButton(new ImageIcon("assets/icons/rectangle.png"));
        vPanel.add(rectangleButton);
        rectangleButton.addActionListener(actionEvent ->{
            cc.setCurrentTool(new RectangleTool(canvas));
            cc.executeTool();
        });
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));


        JButton ellipseButton = new JButton(new ImageIcon("assets/icons/ellipse.png"));
        vPanel.add(ellipseButton);
        ellipseButton.addActionListener(actionEvent ->{
            cc.setCurrentTool(new EllipseTool(canvas));
            cc.executeTool();
        });
        vPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        frame.add(vPanel, BorderLayout.WEST);
        ImageIcon icon = new ImageIcon("assets/icons/palette.png");          //app icon

        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//making the frame visible
        return frame;
    }
}