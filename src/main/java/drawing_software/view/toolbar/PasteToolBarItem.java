package drawing_software.view.toolbar;


import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.PasteTool;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Defines the behaviour of the Paste button that is present on the toolbar.
 */
public class PasteToolBarItem extends ToolbarItemFactory {

    public PasteToolBarItem(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    /**
     * This method allows defines the button's behaviour: that is, a listener is set on the button which will, when
     * prompted, set the state of the canvas to PasteTool.
     *
     * @return is the button on which the listener has been added.
     */
    public JButton itemCreate() {
        URL url = getClass().getResource("/paste.png");
        JButton pasteButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        pasteButton.addActionListener(actionEvent -> canvas.setCurrentTool(new PasteTool(canvas, invoker)));
        //pasteButton.addActionListener(actionEvent ->invoker.executeCommand(new PasteCommand(canvasView)));
        return pasteButton;
    }

}


