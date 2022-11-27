package project_work.view;

import project_work.Context;
import project_work.Main;
import project_work.controller.command.Invoker;
import project_work.view.menu.LoadMenuItem;
import project_work.view.menu.SaveAsMenuItem;
import project_work.view.menu.SaveMenuItem;
import project_work.view.toolbar.EllipseToolbarItem;
import project_work.view.toolbar.LineToolbarItem;
import project_work.view.toolbar.RectangleToolbarItem;
import project_work.view.toolbar.SelectionToolbarItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;


public class FrameView {


    private final static int whiteIntensity = 230;

    public static JFrame createView(String appTitle) {
        Invoker invoker = new Invoker();


        String title = "untitled - " + appTitle;
        JFrame frame = new JFrame(title);
        frame.setBackground(new Color(whiteIntensity, whiteIntensity, whiteIntensity));


        CanvasView canvas = new CanvasView();
        frame.add(canvas);

        JToolBar toolBar = createToolBar(canvas, invoker);
        frame.add(toolBar, BorderLayout.WEST);

        ColorPanel colorPanel = new ColorPanel(canvas);
        frame.add(colorPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = createMenuBar(canvas, invoker);
        frame.setJMenuBar(menuBar);

        ImageIcon icon = new ImageIcon("assets/icons/icon.png");
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

        JMenu fileMenu = new JMenu("File");


        JMenuItem loadMenuItem = new LoadMenuItem(canvasView, invoker).createMenuItem();
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new SaveMenuItem(canvasView, invoker).createMenuItem();
        fileMenu.add(saveMenuItem);

        JMenuItem saveAsMenuItem = new SaveAsMenuItem(canvasView, invoker).createMenuItem();
        fileMenu.add(saveAsMenuItem);

        menuBar.add(fileMenu);

        return menuBar;
    }


    private static JToolBar createToolBar(CanvasView canvas, Invoker invoker) {
        JToolBar toolPanel = new JToolBar(JToolBar.VERTICAL);

        BoxLayout layout = new BoxLayout(toolPanel, BoxLayout.PAGE_AXIS);
        toolPanel.setLayout(layout);

        toolPanel.setFloatable(false);
        JButton selectionButton = new SelectionToolbarItem(canvas).itemCreate();
        toolPanel.add(selectionButton);
        JButton lineButton = new LineToolbarItem(canvas, invoker).itemCreate();
        toolPanel.add(lineButton);
        JButton rectangleButton = new RectangleToolbarItem(canvas, invoker).itemCreate();
        toolPanel.add(rectangleButton);
        JButton ellipseButton = new EllipseToolbarItem(canvas, invoker).itemCreate();
        toolPanel.add(ellipseButton);

        return toolPanel;
    }

}
