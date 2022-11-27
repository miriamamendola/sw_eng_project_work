package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPanel extends JPanel implements ActionListener {
    private final CanvasView canvas;
    private ColorButton fillButton;

    private ColorButton strokeButton;

    public final static int FILL = 1;
    public final static int STROKE = 2;

    public ColorPanel(CanvasView canvas) {
        this.canvas = canvas;
        this.add(createSinglePanel(FILL));
        this.add(createSinglePanel(STROKE));
    }

    public JPanel createSinglePanel(int type) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        if (type == FILL) {
            fillButton = new ColorButton(null);
            panel.add(new JLabel("Fill: "));
            fillButton.setName("fill");
            fillButton.changeColor(canvas.getCurrentFillColor());
            fillButton.addActionListener(this);
            panel.add(fillButton);
        } else {
            strokeButton = new ColorButton(null);
            panel.add(new JLabel("Stroke: "));
            strokeButton.setName("stroke");
            strokeButton.changeColor(canvas.getCurrentStrokeColor());
            strokeButton.addActionListener(this);
            panel.add(strokeButton);
        }

        return panel;
    }

    public void updateButtonColor(Color color, int type) {
        if (type == FILL) {
            fillButton.changeColor(color);
        } else {
            strokeButton.changeColor(color);
        }
    }

    /**
     * When the ColorButton is pressed, it will show a JColorChooser dialog, from which the user
     * will select the desired color, which will be saved as a property of the canvas.
     * The behaviour will change accordingly to the button pressed (fill or stroke).
     * Once the color is selected, it will affect the coloring of new shapes.
     *
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ColorButton source = (ColorButton) actionEvent.getSource();
        Color firstColor;
        if (source.getName().equals("fill")) {
            firstColor = canvas.getCurrentFillColor();
        } else {
            firstColor = canvas.getCurrentStrokeColor();
        }
        Color color = JColorChooser.showDialog(canvas, "Select color", firstColor);
        source.changeColor(color);
        if (source.getName().equals("fill")) {
            canvas.setCurrentFillColor(color);
        } else {
            canvas.setCurrentStrokeColor(color);
        }
    }

}
