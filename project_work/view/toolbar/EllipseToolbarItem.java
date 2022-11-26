package project_work.view.toolbar;

import project_work.controller.command.Invoker;
import project_work.controller.tool.EllipseTool;
import project_work.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class EllipseToolbarItem extends ToolbarItemFactory {

    private final Invoker invoker;

    public EllipseToolbarItem(CanvasView canvasView, Invoker invoker) {
        super(canvasView);
        this.invoker = invoker;
    }

    /**
     * @return
     */
    @Override
    public JButton itemCreate() {
        JButton cursorButton = new JButton(new ImageIcon(new ImageIcon("assets/icons/ellipse.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        cursorButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new EllipseTool(canvasView, invoker)));
        return cursorButton;
    }
}
