package project_work.view;

import project_work.Context;
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


public class FrameView {


    private final static int whiteIntensity = 230;

    public static JFrame createView(String appTitle) {
        Invoker invoker = new Invoker();

        String title = Context.getInstance().getCurrentFile().getName() + " - " + appTitle;
        JFrame frame = new JFrame(title);
        frame.setBackground(new Color(whiteIntensity, whiteIntensity, whiteIntensity));

        CanvasView canvas = new CanvasView();
        frame.add(canvas);

        JToolBar toolBar = createToolBar(canvas, invoker);
        frame.add(toolBar, BorderLayout.WEST);

        JMenuBar menuBar = createMenuBar(canvas, invoker);
        frame.add(menuBar, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon("assets/icons/icon.png");
        frame.setIconImage(icon.getImage());

        return frame;
    }

    private static JMenuBar createMenuBar(CanvasView canvasView, Invoker invoker) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem saveMenuItem = new SaveMenuItem().createMenuItem();
        fileMenu.add(saveMenuItem);

        JMenuItem loadMenuItem = new LoadMenuItem(canvasView, invoker).createMenuItem();
        fileMenu.add(loadMenuItem);

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
