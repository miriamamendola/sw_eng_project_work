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
        CanvasController cc = new CanvasController();
        CanvasView canvas = new CanvasView(); //create canvas for draw
        JFrame frame = new JFrame("Drawing software");
        frame.add(canvas);
        JPanel toolBar =createToolBar(cc,canvas);
        frame.add(toolBar, BorderLayout.WEST);
        ImageIcon icon = new ImageIcon("assets/icons/palette.png");          //app icon
        frame.setIconImage(icon.getImage());
        return frame;
    }



    private static JPanel createToolBar(CanvasController cc,CanvasView canvas){
        JPanel toolPanel = new JPanel();
        BoxLayout boxButton = new BoxLayout(toolPanel, BoxLayout.Y_AXIS);      //create a "vbox " for  tools
        toolPanel.setLayout(boxButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));        //may i can create a method to insert automatically space between component
        JButton cursorButton = cursorButtonCreate(cc,canvas);
        toolPanel.add(cursorButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton lineButton = lineButtonCreate(cc,canvas);
        toolPanel.add(lineButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton rectangleButton = rectangleButtonCreate(cc,canvas);
        toolPanel.add(rectangleButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton ellipseButton = ellipseButtonCreate(cc,canvas);
        toolPanel.add(ellipseButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        return toolPanel;
    }




    private static JButton cursorButtonCreate(CanvasController cc,CanvasView canvas){
        JButton cursorButton = new JButton(new ImageIcon("assets/icons/cursor.png"));
        cursorButton.addActionListener(actionEvent ->{});

        return cursorButton;
    }

    private static  JButton lineButtonCreate(CanvasController cc,CanvasView canvas){
        JButton lineButton = new JButton(new ImageIcon("assets/icons/line.png"));
        lineButton.addActionListener(actionEvent ->{
            cc.setCurrentTool(new LineTool(canvas));
            cc.executeTool();
        });
        return lineButton;
    }
    private static  JButton rectangleButtonCreate(CanvasController cc,CanvasView canvas){
        JButton rectangleButton = new JButton(new ImageIcon("assets/icons/rectangle.png"));
        rectangleButton.addActionListener(actionEvent ->{
            cc.setCurrentTool(new RectangleTool(canvas));
            cc.executeTool();
        });
        return rectangleButton;
    }
    private static  JButton ellipseButtonCreate(CanvasController cc,CanvasView canvas){
        JButton ellipseButton = new JButton(new ImageIcon("assets/icons/ellipse.png"));
        ellipseButton.addActionListener(actionEvent ->{
            cc.setCurrentTool(new EllipseTool(canvas));
            cc.executeTool();
        });
        return ellipseButton;
    }
}