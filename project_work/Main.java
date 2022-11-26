package project_work;

import javax.swing.*;

import project_work.model.Drawing;
import project_work.view.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                Logger logger = Logger.getLogger("root");
                logger.setLevel(Level.ALL);
                Context.getInstance().setCurrentDrawing(new Drawing());
                JFrame frame = FrameView.createView();
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);//making the frame visible
            }
        });
    }
}
