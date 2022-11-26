package project_work.view.menu;

import project_work.controller.command.Invoker;
import project_work.controller.command.SaveCommand;
import project_work.view.CanvasView;
import project_work.view.FileDialog;

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
        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog();
                File selectedFile = null;

                try {
                    selectedFile = fileDialog.show(canvasView, FileDialog.SAVE_DIALOG);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger("root").info("No such file selected");
                }

                invoker.executeCommand(new SaveCommand(canvasView, selectedFile));
            }
        });
        return saveAsMenuItem;
    }
}
