package drawing_software.controller.command;

import drawing_software.model.Drawable;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TrasferableWrapper implements Transferable {

    public Drawable getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Drawable selectedShape) {
        this.selectedShape = selectedShape;
    }

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
    public Drawable getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

            System.out.println("seee");
            return this.selectedShape;

    }
}

