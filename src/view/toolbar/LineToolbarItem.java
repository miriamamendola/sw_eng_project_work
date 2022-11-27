package src.view.toolbar;

import src.controller.command.Invoker;
import src.controller.tool.LineTool;
import src.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class LineToolbarItem extends ToolbarItemFactory {
    private Invoker invoker;

    public LineToolbarItem(CanvasView canvasView, Invoker invoker) {
        super(canvasView);
        this.invoker = invoker;
    }

    /**
     * @return
     */
    @Override
    public JButton itemCreate() {
        JButton lineButton = new JButton(new ImageIcon(new ImageIcon("assets/icons/line.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        lineButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new LineTool(canvasView, invoker)));
        return lineButton;
    }
}
