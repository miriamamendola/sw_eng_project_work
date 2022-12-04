package drawing_software.view.colors;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.StrokeCommand;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class StrokePanel extends ColorPanelFactory {

    public StrokePanel(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.button = new ColorButton(null);
        panel.add(new JLabel("Stroke: "));
        this.button.setName("stroke");
        this.button.changeColor(canvas.getCurrentStrokeColor());
        this.button.addActionListener(actionEvent -> {
            ColorButton source = (ColorButton) actionEvent.getSource();
            invoker.executeCommand(new StrokeCommand(canvas, source));
        });
        panel.add(this.button);
        return panel;
    }
}
