package src.view.menu;

import src.Context;
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
