package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SelectionToolbarItem extends ToolbarItemFactory {

    public SelectionToolbarItem(CanvasView canvasView, Invoker invoker) {
        super(canvasView, invoker);
    }

    /**
     * @return
     */
    @Override
    public JButton itemCreate() {
        URL url = getClass().getResource("/cursor.png");
        JButton cursorButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        cursorButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new SelectionTool(canvasView)));
        return cursorButton;
    }
}
