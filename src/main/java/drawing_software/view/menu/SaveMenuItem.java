package drawing_software.view.menu;

import drawing_software.Context;
import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.SaveCommand;
import drawing_software.view.CanvasView;
import drawing_software.view.FileDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class SaveMenuItem implements MenuItemFactory, ActionListener {

    private final CanvasView canvasView;
    private final Invoker invoker;

    public SaveMenuItem(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker = invoker;
    }

    @Override
    public JMenuItem createMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        return saveMenuItem;
    }

    /**
     * When the menu item is pressed, if the user is working on a new drawing,
     * a new file dialog will show and the file selected by the user will be used to save the drawing,
     * otherwise the drawing will be saved on the previous selected file.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile = Context.getInstance().getCurrentFile();
        if (selectedFile == null) {
            FileDialog fileDialog = new FileDialog();
            try {
                selectedFile = fileDialog.show(canvasView, FileDialog.SAVE_DIALOG);

            } catch (FileNotFoundException ex) {
                Logger.getLogger("root").info("No such file selected");
            }
        }
        invoker.executeCommand(new SaveCommand(canvasView, selectedFile));

    }
}
