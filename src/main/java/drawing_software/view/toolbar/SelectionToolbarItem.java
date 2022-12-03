package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SelectionToolbarItem extends ToolbarItemFactory {

    public SelectionToolbarItem(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    /**
     * @return
     */
    @Override
    public JToggleButton itemCreate() {
        URL url = getClass().getResource("/cursor.png");
        JToggleButton cursorButton = new JToggleButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        cursorButton.addActionListener(actionEvent -> canvas.setCurrentTool(new SelectionTool(canvas, invoker)));
        return cursorButton;
    }
}
