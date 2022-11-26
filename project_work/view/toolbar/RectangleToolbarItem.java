package project_work.view.toolbar;

import project_work.controller.command.Invoker;
import project_work.controller.tool.RectangleTool;
import project_work.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class RectangleToolbarItem extends ToolbarItemFactory {

    private final Invoker invoker;

    public RectangleToolbarItem(CanvasView canvasView, Invoker invoker) {
        super(canvasView);
        this.invoker = invoker;
    }

    /**
     * @return
     */
    @Override
    public JButton itemCreate() {
        JButton rectangleButton = new JButton(new ImageIcon(new ImageIcon("assets/icons/rectangle.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        rectangleButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new RectangleTool(canvasView, invoker)));
        return rectangleButton;
    }
}
