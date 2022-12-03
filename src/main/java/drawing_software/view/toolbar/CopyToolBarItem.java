package drawing_software.view.toolbar;


import drawing_software.controller.command.CopyCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @return
 */
public class CopyToolBarItem extends ToolbarItemFactory {

    public CopyToolBarItem(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    public JButton itemCreate() {
        URL url = getClass().getResource("/copy.png");
        JButton copyButton = new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)));
        copyButton.addActionListener(actionEven -> invoker.executeCommand(new CopyCommand(canvas)));
        return copyButton;
    }

}


