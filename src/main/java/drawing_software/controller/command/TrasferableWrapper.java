package drawing_software.controller.command;

import drawing_software.model.Drawable;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class TrasferableWrapper implements Transferable {
    public TrasferableWrapper(Drawable selectedShape) {
        this.selectedShape = selectedShape;
    }

    private Drawable selectedShape;
    private static DataFlavor dmselFlavor = new DataFlavor(TrasferableWrapper.class, "Test data flavor");

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[0];
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return false;
    }

    @Override
    public Drawable getTransferData(DataFlavor flavor) {
        return this.selectedShape;

    }

    public Drawable getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Drawable selectedShape) {
        this.selectedShape = selectedShape;
    }


}

