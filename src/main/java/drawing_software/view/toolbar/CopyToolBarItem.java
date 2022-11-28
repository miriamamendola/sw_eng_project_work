package drawing_software.view.toolbar;



import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.CopyTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class CopyToolBarItem extends ToolbarItemFactory{
    private final Invoker invoker;

    public  CopyToolBarItem(CanvasView canvasView, Invoker invoker){
        super(canvasView);
        this.invoker= invoker;
    }
    public JButton itemCreate() {
        JButton copyButton = new JButton(new ImageIcon(new ImageIcon("assets/icons/ellipse.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        copyButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new CopyTool(canvasView, invoker)));
        return copyButton;
    }

}


