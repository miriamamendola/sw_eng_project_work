package project_work.view.toolbar;

import project_work.controller.tool.SelectionTool;
import project_work.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class SelectionToolbarItem extends ToolbarItemFactory {

    public SelectionToolbarItem(CanvasView canvasView) {
        super(canvasView);
    }

    /**
     * @return
     */
    @Override
    public JButton itemCreate() {
        JButton cursorButton = new JButton(new ImageIcon(new ImageIcon("assets/icons/cursor.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        cursorButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new SelectionTool(canvasView)));
        return cursorButton;
    }
}
