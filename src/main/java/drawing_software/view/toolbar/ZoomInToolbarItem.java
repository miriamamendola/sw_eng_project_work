package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.RectangleTool;
import drawing_software.controller.tool.ZoomInTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ZoomInToolbarItem extends  ToolbarItemFactory{
    public ZoomInToolbarItem(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    @Override
    public JToggleButton itemCreate() {
        URL url = getClass().getResource("/zoomIN.png");
        JToggleButton zoomInButton = new JToggleButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        zoomInButton.addActionListener(actionEvent -> canvas.setCurrentTool(new ZoomInTool(canvas, invoker)));
        return zoomInButton;
    }
}
