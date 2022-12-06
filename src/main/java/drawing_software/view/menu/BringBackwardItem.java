package drawing_software.view.menu;

import drawing_software.controller.command.BackwardCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BringBackwardItem extends MenuItemFactory{


    public BringBackwardItem(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    @Override
    public JMenuItem createMenuItem() {
        JMenuItem bringBackwardMenuItem = new JMenuItem("Bring Backwards");
        bringBackwardMenuItem.addActionListener(new ActionListener() {
            /**
             * When the menu item is pressed, a new file dialog will show and the file
             * selected by the user will be used to load the drawing.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                invoker.executeCommand(new BackwardCommand(canvas));
            }
        });
        return bringBackwardMenuItem;
    }
}