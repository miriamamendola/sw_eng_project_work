package drawing_software.controller;

import drawing_software.Context;
import drawing_software.Main;
import drawing_software.view.Window;
import drawing_software.view.menu.SaveMenuItem;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

public class WindowController {
    private Window window;

    public WindowController(String appTitle) {
        window = new Window(appTitle);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                //check if modified
                Logger.getLogger("root").info("Closing...");
                if (Context.getInstance().isSaved()) {
                    window.dispose();
                } else {
                    String title;
                    if (Context.getInstance().getCurrentFile() == null) {
                        title = "untitled";
                    } else {
                        title = Context.getInstance().getCurrentFile().getName();
                    }
                    String message = "Do you want to save changes to " + title + " ? ";

                    int confirmed = JOptionPane.showConfirmDialog(null,
                            message, Main.appTitle,
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (confirmed == JOptionPane.YES_OPTION) {
                        new SaveMenuItem(window.getCanvas(), window.getInvoker()).actionPerformed(null);
                        window.dispose();
                    } else if (confirmed == JOptionPane.NO_OPTION) {
                        window.dispose();
                    }
                }
            }
        });

        window.setFocusable(false);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setVisible(true);

        {
            setCheckBoxScaleVisible(false);
            setCutMenuItemEnabled(false);
            setCopyMenuItemEnabled(false);
            setPasteMenuItemEnabled(false);
            setDeleteMenuItemEnabled(false);
            setUndoItemEnabled(false);
        }


    }

    public void setFrameTitle(String title) {
        window.setTitle(title);
    }

    public void setFillPanelEnabled(boolean state) {
        window.getFillPanel().getButton().setEnabled(state);
    }

    public void setStrokePanelEnabled(boolean state) {
        window.getStrokePanel().getButton().setEnabled(state);
    }

    public void setCheckBoxScaleVisible(boolean state) {
        window.getCheckBoxScale().setVisible(state);
    }

    public void setCutMenuItemEnabled(boolean state) {
        window.getCutMenuItem().setEnabled(state);
    }

    public void setCopyMenuItemEnabled(boolean state) {
        window.getCopyMenuItem().setEnabled(state);
    }

    public void setPasteMenuItemEnabled(boolean state) {
        window.getPasteMenuItem().setEnabled(state);
    }

    public void setDeleteMenuItemEnabled(boolean state) {
        window.getDeleteMenuItem().setEnabled(state);
    }

    public void setUndoItemEnabled(boolean state) {
        window.getUndoMenuItem().setEnabled(state);
    }

}
