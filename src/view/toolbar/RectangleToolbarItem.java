package src.view.toolbar;

import src.controller.command.Invoker;
import src.controller.tool.RectangleTool;
import src.view.CanvasView;

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
