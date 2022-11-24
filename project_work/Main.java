package project_work;

import javax.swing.*;

import project_work.model.Drawing;
import project_work.view.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Context.getInstance().setCurrentDrawing(new Drawing());
                JFrame frame = FrameView.createView();
                frame.setSize(600,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);//making the frame visible
            }
        });
    }
}
