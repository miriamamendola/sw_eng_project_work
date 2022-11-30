package drawing_software.view.toolbar;


import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.CutTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CutToolBarItem extends ToolbarItemFactory {

    public CutToolBarItem(CanvasView canvasView, Invoker invoker) {
        super(canvasView, invoker);
    }

    public JButton itemCreate() {
        URL url = getClass().getResource("/scissors.png");

        JButton cutButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        cutButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new CutTool(canvasView, invoker)));
        //pasteButton.addActionListener(actionEvent ->invoker.executeCommand(new PasteCommand(canvasView)));
        return cutButton;
    }

}

