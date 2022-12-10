package drawing_software.controller.tool.handler;


import drawing_software.controller.tool.MouseRequest;
import drawing_software.view.Canvas;

public abstract class Handler {

    protected Handler nextHandler;
    protected Canvas canvas;

    public Handler(Canvas canvas) {
        this.canvas = canvas;
    }

    abstract void handlePressed(MouseRequest request);

    abstract void handleDragged(MouseRequest request);

    void setNext(Handler next) {
        this.nextHandler = next;
    }
}
