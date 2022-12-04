package drawing_software.view.colors;

import drawing_software.controller.command.FillCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;

public class FillPanel extends ColorPanelFactory {

    public FillPanel(CanvasView canvas, Invoker invoker) {
        super(canvas, invoker);
    }

    @Override
    public JPanel createPanel() {

        this.setLayout(new FlowLayout());
        this.button = new ColorButton(null);
        this.add(new JLabel("Fill: "));
        this.button.setName("fill");
        this.button.changeColor(canvas.getCurrentFillColor());
        this.button.addActionListener(actionEvent -> {
            ColorButton source = (ColorButton) actionEvent.getSource();
            invoker.executeCommand(new FillCommand(canvas, source));
        });
        this.add(this.button);

        return this;
    }
}
