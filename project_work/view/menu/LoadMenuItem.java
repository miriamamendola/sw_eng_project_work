package project_work.view.menu;

import project_work.controller.command.Invoker;
import project_work.controller.command.LoadCommand;
import project_work.view.CanvasView;
import project_work.view.FileDialog;

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
        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(new ActionListener() {
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