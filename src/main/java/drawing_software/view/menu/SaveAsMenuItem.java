package drawing_software.view.menu;

import drawing_software.Main;
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

public class SaveAsMenuItem implements MenuItemFactory {

    private final CanvasView canvasView;
    private final Invoker invoker;

    public SaveAsMenuItem(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker = invoker;
    }

    @Override
    public JMenuItem createMenuItem() {
        JMenuItem saveAsMenuItem = new JMenuItem("Save as...");
        saveAsMenuItem.addActionListener(new ActionListener() {
            /**
             * When the menu item is pressed, a new file dialog will show and the file
             * selected by the user will be used to save the drawing.
             * If the selected file already exists, the user will be prompted with
             * a dialog asking to confirm overwriting the file.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog();
                File selectedFile;

                try {
                    selectedFile = fileDialog.show(canvasView, FileDialog.SAVE_DIALOG);
                    if (selectedFile.exists()) {
                        String message = "Are you sure you want to overwrite " + selectedFile.getName() + " ? ";

                        int confirmed = JOptionPane.showConfirmDialog(null, message, Main.appTitle, JOptionPane.YES_NO_OPTION);

                        if (confirmed == JOptionPane.YES_OPTION) {
                            invoker.executeCommand(new SaveCommand(canvasView, selectedFile));
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger("root").info("No such file selected");
                }


            }
        });
        return saveAsMenuItem;
    }
}
