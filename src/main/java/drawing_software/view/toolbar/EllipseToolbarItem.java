package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.EllipseTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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
        URL url = getClass().getResource("/ellipse.png");
        JButton cursorButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        cursorButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new EllipseTool(canvasView, invoker)));
        return cursorButton;
    }
}
