package drawing_software.view.menu;

import drawing_software.controller.command.ForwardCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.Canvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows to create the apposite item in the "Edit" menu.
 */
public class BringForwardItem extends MenuItemFactory {
    public BringForwardItem(Canvas canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    /**
     * Used to create the menu item to be pressed.
     */
    @Override
    public JMenuItem createMenuItem() {
        JMenuItem bringForwardMenuItem = new JMenuItem("Bring Forward");
        bringForwardMenuItem.addActionListener(new ActionListener() {
            /**
             * When the menu item is pressed, the BringForward Command will be called and its actions
             * will be executed.
             *
             * @param e the event to be processed.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                invoker.executeCommand(new ForwardCommand(canvas));
            }
        });
        return bringForwardMenuItem;
    }
}
