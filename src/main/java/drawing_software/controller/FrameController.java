package drawing_software.controller;

import drawing_software.view.FrameView;

public class FrameController {
    private FrameView frameView;

    public void setFrameTitle(String title) {
        frameView.getFrame().setTitle(title);
    }

    public void setFillPanelDisabled(boolean state) {
        frameView.getFillPanel().getButton().setEnabled(state);
    }
}
