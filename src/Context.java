package src;

import java.io.File;

public class Context {

    private static Context instance = null;
    private File currentFile;

    private boolean saved;

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isSaved() {
        return saved;
    }

}
