package drawing_software.controller;

import drawing_software.Main;
import drawing_software.controller.tool.LineTool;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.view.Window;
import drawing_software.view.menu.SaveMenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class WindowController {
    private final Window window;

    public WindowController(String appTitle) {
        window = new Window(appTitle);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                //check if modified
                Logger.getLogger("root").info("Closing...");
                if (window.isModified()) {
                    String title;
                    if (window.getCurrentFile() == null) {
                        title = "untitled";
                    } else {
                        title = window.getCurrentFile().getName();
                    }
                    String message = "Do you want to save changes to " + title + " ? ";

                    int confirmed = JOptionPane.showConfirmDialog(null,
                            message, Main.appTitle,
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (confirmed == JOptionPane.YES_OPTION)
                        new SaveMenuItem(window, window.getInvoker()).actionPerformed(null);
                }
                window.dispose();
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
        window.getInvoker().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                // se è vuoto
                setUndoItemEnabled(!((boolean) propertyChangeEvent.getNewValue()));
            }
        });
        window.getCanvas().addPropertyChangeListener("SELECTION", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                // c'è un oggetto selezionato
                if (propertyChangeEvent.getNewValue() != null) {
                    Shape selected = ((SelectionGrid) propertyChangeEvent.getNewValue()).getSelectedShape();
                    setCutMenuItemEnabled(true);
                    setCopyMenuItemEnabled(true);
                    setDeleteMenuItemEnabled(true);
                    setFillPanelColor((Color) selected.getFillColor());
                    setStrokePanelColor((Color) selected.getStrokeColor());
                } else {
                    setCutMenuItemEnabled(false);
                    setCopyMenuItemEnabled(false);
                    setDeleteMenuItemEnabled(false);
                    setFillPanelColor(window.getCanvas().getCurrentFillColor());
                    setStrokePanelColor(window.getCanvas().getCurrentStrokeColor());
                }
            }
        });

        window.getCanvas().addPropertyChangeListener("CURRENT_TOOL", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                setCheckBoxScaleVisible(propertyChangeEvent.getNewValue() instanceof SelectionTool);
                setFillPanelEnabled(!(propertyChangeEvent.getNewValue() instanceof LineTool));
            }
        });

        window.getCanvas().addPropertyChangeListener("MODIFIED", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                window.setModified((boolean) propertyChangeEvent.getNewValue());
                String title;
                if (window.getCurrentFile() == null) {
                    title = "untitled";
                } else {
                    title = window.getCurrentFile().getName();
                }
                if (window.isModified()) {
                    window.setTitle(title + " * - " + Main.appTitle);
                } else {
                    window.setTitle(title + " - " + Main.appTitle);
                }
            }
        });

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

    public void setFillPanelColor(Color color) {
        window.getFillPanel().getButton().changeColor(color);
    }

    public void setStrokePanelColor(Color color) {
        window.getStrokePanel().getButton().changeColor(color);
    }

}

