package drawing_software.view.colors;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FillPanel extends ColorPanelFactory {

    public FillPanel(CanvasView canvas) {
        super(canvas);
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.button = new ColorButton(null);
        panel.add(new JLabel("Fill: "));
        this.button.setName("fill");
        this.button.changeColor(canvas.getCurrentFillColor());
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ColorButton source = (ColorButton) actionEvent.getSource();
                Color firstColor = canvas.getCurrentFillColor();
                Color color = JColorChooser.showDialog(canvas, "Select color", firstColor);
                if (canvas.getSelectionGrid() != null) {
                    Shape s = canvas.getSelectionGrid().getSelectedShape();
                    source.changeColor((Color) s.getFillColor());
                    s.setFillColor(color);
                    canvas.repaint();
                } else {
                    source.changeColor(color);
                    canvas.setCurrentFillColor(color);
                }
            }
        });
        panel.add(this.button);

        return panel;
    }
}
