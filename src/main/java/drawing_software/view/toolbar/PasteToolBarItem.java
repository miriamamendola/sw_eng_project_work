package drawing_software.view.toolbar;


import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.PasteTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PasteToolBarItem extends ToolbarItemFactory{

    public  PasteToolBarItem(CanvasView canvasView, Invoker invoker){
        super(canvasView,invoker);
    }
    public JButton itemCreate() {
        URL url = getClass().getResource("/rectangle.png");

        JButton pasteButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        pasteButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new PasteTool(canvasView, invoker)));
        //pasteButton.addActionListener(actionEvent ->invoker.executeCommand(new PasteCommand(canvasView)));
        return pasteButton;
    }

}


