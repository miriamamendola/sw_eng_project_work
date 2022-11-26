package project_work;

import project_work.model.Drawing;
import project_work.view.FrameView;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final String appTitle = "Drawing software";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Logger logger = Logger.getLogger("root");
                logger.setLevel(Level.ALL);

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }

                Context.getInstance().setCurrentDrawing(new Drawing());
                JFrame frame = FrameView.createView(appTitle);

                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);//making the frame visible
            }
        });
    }
}
