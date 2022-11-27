package src.view.menu;

import src.Main;
import src.controller.command.Invoker;
import src.controller.command.SaveCommand;
import src.view.CanvasView;
import src.view.FileDialog;

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
