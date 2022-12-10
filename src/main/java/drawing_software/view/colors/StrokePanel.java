package drawing_software.view.colors;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.StrokeCommand;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrokePanel extends ColorPanelFactory implements ActionListener {

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
        this.button.addActionListener(this);
        panel.add(this.button);
        return panel;
    }

    /**
     * Shows to the user the color chooser, executes StrokeCommand with the color selected by
     * the user and updates the ColorButton accordingly.
     *
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ColorButton source = (ColorButton) actionEvent.getSource();
        Color firstColor = canvas.getCurrentStrokeColor();
        Color color = JColorChooser.showDialog(canvas, "Select color", firstColor);
        invoker.executeCommand(new StrokeCommand(canvas, color));
        source.changeColor(color);
    }
}
