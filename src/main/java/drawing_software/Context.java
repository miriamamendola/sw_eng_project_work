package drawing_software;

import java.io.File;

public class Context {

    private static Context instance = null;
    private File currentFile;
    private boolean saved;
    private boolean fixed;

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

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
