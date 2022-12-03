package drawing_software.view.colors;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrokePanel extends ColorPanelFactory {

    public StrokePanel(CanvasView canvas) {
        super(canvas);
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.button = new ColorButton(null);
        panel.add(new JLabel("Stroke: "));
        this.button.setName("stroke");
        this.button.changeColor(canvas.getCurrentStrokeColor());
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ColorButton source = (ColorButton) actionEvent.getSource();
                Color firstColor = canvas.getCurrentStrokeColor();
                Color color = JColorChooser.showDialog(canvas, "Select color", firstColor);
                if(canvas.getSelectionGrid() != null){
                    Shape s = (Shape) canvas.getSelectionGrid().getSelectedShape();
                    source.changeColor((Color) s.getStrokeColor());
                    s.setStrokeColor(color);
                    canvas.repaint();
                } else {
                    source.changeColor(color);
                    canvas.setCurrentStrokeColor(color);
                }
            }
        });
        panel.add(this.button);
        return panel;
    }
}
