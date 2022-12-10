package drawing_software.view;

import drawing_software.Context;
import drawing_software.controller.CanvasController;
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
import java.awt.event.ItemEvent;
import java.net.URL;


public class Window extends JFrame {
    private final Invoker invoker;
    private final Canvas canvas;
    private final FillPanel fillPanel;
    private final StrokePanel strokePanel;
    private final JCheckBox checkBoxScale;

    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem undoMenuItem;

    private final static int whiteIntensity = 230;


    public Window(String appTitle) {
        super("untitled - " + appTitle);
        invoker = new Invoker();
        this.setFocusable(false);
        this.setBackground(new Color(whiteIntensity, whiteIntensity, whiteIntensity));

        canvas = new Canvas(invoker);
        new CanvasController(canvas);
        canvas.setFocusable(true);
        this.add(canvas);


        /* ToolBar */

        JToolBar toolBar = createToolBar(canvas, invoker);
        toolBar.setFocusable(false);
        this.add(toolBar, BorderLayout.WEST);



        /* BottomPanel */

        JPanel bottomPanel = new JPanel();
        fillPanel = new FillPanel(canvas, invoker);
        bottomPanel.add(fillPanel);
        strokePanel = new StrokePanel(canvas, invoker);
        bottomPanel.add(strokePanel);
        bottomPanel.add(Box.createHorizontalStrut(50));


        checkBoxScale = new JCheckBox("Fixed proportions");
        checkBoxScale.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Context.getInstance().setFixed(true);
            } else {
                Context.getInstance().setFixed(false);
            }
        });
        bottomPanel.add(checkBoxScale);


        this.add(bottomPanel, BorderLayout.SOUTH);



        /* MenuBar */

        JMenuBar menuBar = createMenuBar(canvas, invoker);
        menuBar.setFocusable(false);
        this.setJMenuBar(menuBar);



        /* Icon */

        URL url = Window.class.getResource("/icon.png");
        ImageIcon icon = new ImageIcon(url);
        this.setIconImage(icon.getImage());

    }

    private JMenuBar createMenuBar(Canvas canvas, Invoker invoker) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFocusable(false);

        /* File Menu */

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFocusable(false);

        JMenuItem loadMenuItem = new LoadMenuItem(canvas, invoker).createMenuItem();
        loadMenuItem.setFocusable(false);
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new SaveMenuItem(canvas, invoker).createMenuItem();
        saveMenuItem.setFocusable(false);
        fileMenu.add(saveMenuItem);

        JMenuItem saveAsMenuItem = new SaveAsMenuItem(canvas, invoker).createMenuItem();
        saveAsMenuItem.setFocusable(false);
        fileMenu.add(saveAsMenuItem);

        menuBar.add(fileMenu);



        /* Edit Menu */

        JMenu editMenu = new JMenu("Edit");
        editMenu.setFocusable(false);

        cutMenuItem = new CutMenuItem(canvas, invoker).createMenuItem();
        cutMenuItem.setFocusable(false);
        editMenu.add(cutMenuItem);

        copyMenuItem = new CopyMenuItem(canvas, invoker).createMenuItem();
        copyMenuItem.setFocusable(false);
        editMenu.add(copyMenuItem);

        pasteMenuItem = new PasteMenuItem(canvas, invoker).createMenuItem();
        pasteMenuItem.setFocusable(false);
        editMenu.add(pasteMenuItem);

        deleteMenuItem = new DeleteMenuItem(canvas, invoker).createMenuItem();
        deleteMenuItem.setFocusable(false);
        editMenu.add(deleteMenuItem);

        undoMenuItem = new UndoMenuItem(canvas, invoker).createMenuItem();
        deleteMenuItem.setFocusable(false);
        editMenu.add(undoMenuItem);

        menuBar.add(editMenu);


        return menuBar;
    }


    private static JToolBar createToolBar(Canvas canvas, Invoker invoker) {
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

    public Invoker getInvoker() {
        return invoker;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public FillPanel getFillPanel() {
        return fillPanel;
    }

    public StrokePanel getStrokePanel() {
        return strokePanel;
    }

    public JCheckBox getCheckBoxScale() {
        return checkBoxScale;
    }

    public JMenuItem getCutMenuItem() {
        return cutMenuItem;
    }

    public JMenuItem getCopyMenuItem() {
        return copyMenuItem;
    }

    public JMenuItem getPasteMenuItem() {
        return pasteMenuItem;
    }

    public JMenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    public JMenuItem getUndoMenuItem() {
        return undoMenuItem;
    }
}