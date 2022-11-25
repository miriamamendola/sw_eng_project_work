package project_work.view;
import project_work.controller.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;


public class FrameView {


    public static JFrame createView(){
        Invoker invoker = new Invoker();
        CanvasView canvas = new CanvasView(); //create canvas for draw
        JFrame frame = new JFrame("Drawing software");
        frame.add(canvas);
        JPanel toolBar =createToolBar(invoker,canvas);
        frame.add(toolBar, BorderLayout.WEST);

        frame.add(createMenuBar(canvas, invoker), BorderLayout.NORTH);
        ImageIcon icon = new ImageIcon("assets/icons/palette.png");          //app icon
        frame.setIconImage(icon.getImage());
        return frame;
    }

    private static JMenuBar createMenuBar(CanvasView canvasView, Invoker invoker){
        JMenuBar menuBar =new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem, loadMenuItem, saveAsMenuItem;
        saveMenuItem=new JMenuItem("Save");
        fileMenu.add(saveMenuItem);


        loadMenuItem=new JMenuItem("Load");
        fileMenu.add(loadMenuItem);
        saveAsMenuItem=new JMenuItem("Save as");
        fileMenu.add(saveAsMenuItem);
        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File selectedFile = showSaveDialog(menuBar.getParent());
                if(selectedFile != null)
                    invoker.executeCommand(new SaveCommand(canvasView, selectedFile));
                else
                    Logger.getLogger("root").info("No such file selected");
            }
        });

        menuBar.add(fileMenu);

        return menuBar;
    }


    private static JPanel createToolBar(Invoker invoker,CanvasView canvas){
        JPanel toolPanel = new JPanel();
        BoxLayout boxButton = new BoxLayout(toolPanel, BoxLayout.Y_AXIS);      //create a "vbox " for  tools
        toolPanel.setLayout(boxButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));        //may i can create a method to insert automatically space between component
        JButton cursorButton = cursorButtonCreate(canvas);
        toolPanel.add(cursorButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton lineButton = lineButtonCreate(invoker,canvas);
        toolPanel.add(lineButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton rectangleButton = rectangleButtonCreate(invoker,canvas);
        toolPanel.add(rectangleButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton ellipseButton = ellipseButtonCreate(invoker,canvas);
        toolPanel.add(ellipseButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        return toolPanel;
    }




    private static JButton cursorButtonCreate(CanvasView canvas){
        JButton cursorButton = new JButton(new ImageIcon("assets/icons/cursor.png"));
        cursorButton.addActionListener(actionEvent -> canvas.setCurrentTool(new SelectionTool(canvas)));
        return cursorButton;
    }

    private static  JButton lineButtonCreate(Invoker invoker, CanvasView canvas){
        JButton lineButton = new JButton(new ImageIcon("assets/icons/line.png"));
        lineButton.addActionListener(actionEvent -> canvas.setCurrentTool(new LineTool(canvas, invoker)));
        return lineButton;
    }
    private static  JButton rectangleButtonCreate(Invoker invoker, CanvasView canvas){
        JButton rectangleButton = new JButton(new ImageIcon("assets/icons/rectangle.png"));
        rectangleButton.addActionListener(actionEvent -> canvas.setCurrentTool(new RectangleTool(canvas, invoker)));
        return rectangleButton;
    }
    private static  JButton ellipseButtonCreate(Invoker invoker,CanvasView canvas){
        JButton ellipseButton = new JButton(new ImageIcon("assets/icons/ellipse.png"));
        ellipseButton.addActionListener(actionEvent -> canvas.setCurrentTool(new EllipseTool(canvas, invoker)));
        return ellipseButton;
    }

    private static File showSaveDialog(Component parent){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Drawing file", "draw");
        chooser.addChoosableFileFilter(filter);
        int returnVal = chooser.showSaveDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return new File(chooser.getSelectedFile().toString() + ".draw");
        }
        return null;
    }
}