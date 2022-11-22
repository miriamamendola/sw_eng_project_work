import javax.swing.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameView.createView();
            }
        });
    }
}
