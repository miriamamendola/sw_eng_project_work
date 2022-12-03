package drawing_software.view.menu;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.PasteCommand;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class PasteMenuItem extends MenuItemFactory {

    public PasteMenuItem(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    @Override
    public JMenuItem createMenuItem() {
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
        pasteMenuItem.addActionListener(new ActionListener() {
            /**
             * When the menu item is pressed, a new file dialog will show and the file
             * selected by the user will be used to load the drawing.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                invoker.executeCommand(new PasteCommand(canvas, canvas.getMousePosition()));
            }
        });
        return pasteMenuItem;
    }
}

