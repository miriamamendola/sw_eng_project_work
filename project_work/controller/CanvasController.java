package project_work.controller;

public class CanvasController {
    private Tool currentTool;

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public void executeTool(){
        this.currentTool.execute();
    }
}
