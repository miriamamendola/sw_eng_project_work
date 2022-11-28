package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.RectangleTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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
        URL url = getClass().getResource("/rectangle.png");
        JButton rectangleButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        rectangleButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new RectangleTool(canvasView, invoker)));
        return rectangleButton;
    }
}
