package drawing_software.view.menu;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.LoadCommand;
import drawing_software.view.CanvasView;
import drawing_software.view.FileDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class LoadMenuItem implements MenuItemFactory {

    private final CanvasView canvasView;
    private final Invoker invoker;

    public LoadMenuItem(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker = invoker;
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
                    selectedFile = fileDialog.show(canvasView, FileDialog.OPEN_DIALOG);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger("root").info("No such file selected");
                }

                invoker.executeCommand(new LoadCommand(canvasView, selectedFile));
            }
        });
        return loadMenuItem;
    }
}
