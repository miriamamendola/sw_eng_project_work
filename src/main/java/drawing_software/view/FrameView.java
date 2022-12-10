package drawing_software.view;

import drawing_software.Context;
import drawing_software.Main;
import drawing_software.controller.command.Invoker;
import drawing_software.view.colors.FillPanel;
import drawing_software.view.colors.StrokePanel;
import drawing_software.view.menu.*;
import drawing_software.view.toolbar.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
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
        JPanel p= new JPanel();
        p.add(canvas);
        p.setBackground(Color.LIGHT_GRAY);
        p.setOpaque(true);
        p.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                canvas.setScalePoint(e.getPoint());
                double n = canvas.getScaleFactor();
                n= n+(-e.getWheelRotation()*0.2);
                canvas.setScaleFactor(n);
            }
        });

        frame.add(p);

        JToolBar toolBar = createToolBar(canvas, invoker);
        toolBar.setFocusable(false);
        frame.add(toolBar, BorderLayout.WEST);

        JPanel colorPanel = new JPanel();
        colorPanel.add(new FillPanel(canvas, invoker).createPanel());
        colorPanel.add(new StrokePanel(canvas, invoker).createPanel());
        colorPanel.add(Box.createHorizontalStrut(50));
        JCheckBox scale = new JCheckBox("Fixed proportions");
        scale.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Context.getInstance().setFixed(true);
                } else {
                    Context.getInstance().setFixed(false);
                }
                ;
            }
        });
        colorPanel.add(scale);

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

        JScrollBar hbar=new JScrollBar(JScrollBar.HORIZONTAL, 0, 50, 0, 500);
        JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 0, 40, 0, 500);
        class MyAdjustmentListener implements AdjustmentListener {
            private int valueh;
            private int valuev;
            public MyAdjustmentListener(){
                valueh =canvas.getX();
                valuev=canvas.getY();
            }
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if(e.getSource().equals(hbar)){
                    Point2D p = new Point(-e.getValue(),-valuev);
                    valueh= e.getValue();
                    System.out.println("horizontal");
                    canvas.setScalePoint(p);
                }else{
                    Point2D p = new Point(-valueh,-e.getValue());
                    valuev=e.getValue();
                    System.out.println("vertical");
                    canvas.setScalePoint(p);
                }
                System.out.println("repaint");
                canvas.repaint();
                frame.repaint();
            }
        }
        MyAdjustmentListener list = new MyAdjustmentListener();
        hbar.addAdjustmentListener(list);
        vbar.addAdjustmentListener(list);
        //frame.getContentPane().add(hbar, BorderLayout.SOUTH);
        frame.getContentPane().add(vbar, BorderLayout.EAST);

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

        JToggleButton zoomInButton = new ZoomInToolbarItem(canvas, invoker).itemCreate();
        zoomInButton.setFocusable(false);
        group.add(zoomInButton);
        toolPanel.add(zoomInButton);


        return toolPanel;
    }

}
