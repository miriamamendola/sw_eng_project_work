package drawing_software.view.menu;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.LoadCommand;
import drawing_software.view.Canvas;
import drawing_software.view.FileDialog;
import drawing_software.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class LoadMenuItem extends MenuItemFactory {

    private Window window;

    public LoadMenuItem(Canvas canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    public LoadMenuItem(Window window, Invoker invoker) {
        super(window.getCanvas(), invoker);
        this.window = window;
    }

    @Override
    public JMenuItem createMenuItem() {
        JMenuItem loadMenuItem = new JMenuItem("Open...");
        loadMenuItem.addActionListener(new ActionListener() {
            /**
             * When the menu item is pressed, a new file dialog will show and the file
             * selected by the user will be used to load the drawing.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog();
                File selectedFile = null;

                try {
                    selectedFile = fileDialog.show(canvas, FileDialog.OPEN_DIALOG);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger("root").info("No such file selected");
                }

                invoker.executeCommand(new LoadCommand(window, selectedFile));
            }
        });
        return loadMenuItem;
    }
}
