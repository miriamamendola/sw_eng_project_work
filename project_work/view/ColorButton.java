package project_work.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ColorButton extends JButton {

    private ColorButtonUI buttonUI;

    /**
     *
     */
    public ColorButton(Color color) {
        super();
        this.setPreferredSize(new Dimension(20, 20));
        buttonUI = new ColorButtonUI(color);
        this.setUI(buttonUI);
    }

    public void changeColor(Color color) {
        buttonUI.setColor(color);
    }

    private class ColorButtonUI extends BasicButtonUI {

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
            repaint();
        }

        private Color color;

        ColorButtonUI(Color color) {
            this.color = color;
        }

        /**
         * @param g
         * @param c
         */
        @Override
        public void paint(Graphics g, JComponent c) {
            JButton button = (JButton) c;
            g.setColor(color);
            g.fill3DRect(0, 0, c.getWidth(), c.getHeight(), true);
            super.paint(g, c);
        }
    }
}
