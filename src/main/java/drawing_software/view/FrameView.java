package drawing_software.view;

import drawing_software.Context;
import drawing_software.Main;
import drawing_software.controller.command.Invoker;
import drawing_software.view.colors.FillPanel;
import drawing_software.view.colors.StrokePanel;
import drawing_software.view.menu.*;
import drawing_software.view.toolbar.EllipseToolbarItem;
import drawing_software.view.toolbar.LineToolbarItem;
import drawing_software.view.toolbar.RectangleToolbarItem;
import drawing_software.view.toolbar.SelectionToolbarItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.logging.Logger;


public class FrameView {


    private final static int whiteIntensity = 230;

    public static JFrame createView(String appTitle) {
        Invoker invoker = new Invoker();


        String title = "untitled - " + appTitle;
        JFrame frame = new JFrame(title);
        frame.setFocusable(false);
        frame.setBackground(new Color(whiteIntensity, whiteIntensity, whiteIntensity));


        CanvasView canvas = new CanvasView(invoker);
        canvas.setFocusable(true);
        frame.add(canvas);

        JToolBar toolBar = createToolBar(canvas, invoker);
        toolBar.setFocusable(false);
        frame.add(toolBar, BorderLayout.WEST);

        JPanel colorPanel = new JPanel();
        colorPanel.add(new FillPanel(canvas).createPanel());
        colorPanel.add(new StrokePanel(canvas).createPanel());
        frame.add(colorPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = createMenuBar(canvas, invoker);
        menuBar.setFocusable(false);
        frame.setJMenuBar(menuBar);

        URL url = FrameView.class.getResource("/icon.png");
        ImageIcon icon = new ImageIcon(url);
        frame.setIconImage(icon.getImage());


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                //check if modified
                Logger.getLogger("root").info("byebye");
                if (Context.getInstance().isSaved()) {
                    frame.dispose();
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
                        new SaveMenuItem(canvas, invoker).actionPerformed(null);
                        frame.dispose();
                    } else if (confirmed == JOptionPane.NO_OPTION) {
                        frame.dispose();
                    }

                }

            }
        });


        return frame;
    }

    private static JMenuBar createMenuBar(CanvasView canvasView, Invoker invoker) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFocusable(false);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFocusable(false);

        JMenuItem loadMenuItem = new LoadMenuItem(canvasView, invoker).createMenuItem();
        loadMenuItem.setFocusable(false);
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new SaveMenuItem(canvasView, invoker).createMenuItem();
        saveMenuItem.setFocusable(false);
        fileMenu.add(saveMenuItem);

        JMenuItem saveAsMenuItem = new SaveAsMenuItem(canvasView, invoker).createMenuItem();
        saveAsMenuItem.setFocusable(false);
        fileMenu.add(saveAsMenuItem);

        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setFocusable(false);

        JMenuItem cutMenuItem = new CutMenuItem(canvasView, invoker).createMenuItem();
        cutMenuItem.setFocusable(false);
        editMenu.add(cutMenuItem);

        JMenuItem copyMenuItem = new CopyMenuItem(canvasView, invoker).createMenuItem();
        copyMenuItem.setFocusable(false);
        editMenu.add(copyMenuItem);

        JMenuItem pasteMenuItem = new PasteMenuItem(canvasView, invoker).createMenuItem();
        pasteMenuItem.setFocusable(false);
        editMenu.add(pasteMenuItem);

        JMenuItem deleteMenuItem = new DeleteMenuItem(canvasView, invoker).createMenuItem();
        deleteMenuItem.setFocusable(false);
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        return menuBar;
    }


    private static JToolBar createToolBar(CanvasView canvas, Invoker invoker) {
        JToolBar toolPanel = new JToolBar(JToolBar.VERTICAL);
        toolPanel.setFocusable(false);
        toolPanel.setFloatable(false);

        BoxLayout layout = new BoxLayout(toolPanel, BoxLayout.PAGE_AXIS);
        toolPanel.setLayout(layout);

        ButtonGroup group = new ButtonGroup();

        JToggleButton selectionButton = new SelectionToolbarItem(canvas, invoker).itemCreate();
        selectionButton.setFocusable(false);
        group.add(selectionButton);
        toolPanel.add(selectionButton);

        JToggleButton lineButton = new LineToolbarItem(canvas, invoker).itemCreate();
        lineButton.setFocusable(false);
        group.add(lineButton);
        toolPanel.add(lineButton);

        JToggleButton rectangleButton = new RectangleToolbarItem(canvas, invoker).itemCreate();
        rectangleButton.setFocusable(false);
        group.add(rectangleButton);
        toolPanel.add(rectangleButton);

        JToggleButton ellipseButton = new EllipseToolbarItem(canvas, invoker).itemCreate();
        ellipseButton.setFocusable(false);
        group.add(ellipseButton);
        toolPanel.add(ellipseButton);

        return toolPanel;
    }

}
