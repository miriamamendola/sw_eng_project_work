package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.LineTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LineToolbarItem extends ToolbarItemFactory {

    public LineToolbarItem(CanvasView canvasView, Invoker invoker) {
        super(canvasView, invoker);
    }

    /**
     * @return
     */
    @Override
    public JButton itemCreate() {
        URL url = getClass().getResource("/line.png");
        JButton lineButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        lineButton.addActionListener(actionEvent -> canvasView.setCurrentTool(new LineTool(canvasView, invoker)));
        return lineButton;
    }
}
