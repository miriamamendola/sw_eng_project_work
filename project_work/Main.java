package project_work;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;
import project_work.view.FrameView;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main {
    public static final String appTitle = "Drawing software";
    public static void main(String[] args) {
        if (SystemInfo.isMacOS) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", appTitle);
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", appTitle);
            System.setProperty("apple.awt.application.appearance", "system");
        }
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Logger logger = Logger.getLogger("root");
                logger.setLevel(Level.ALL);
                Context.getInstance().setSaved(true);
                JFrame frame = FrameView.createView(appTitle);
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.setVisible(true);//making the frame visible
            }
        });
    }
}