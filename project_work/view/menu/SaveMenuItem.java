package project_work.view.menu;

import javax.swing.*;

public class SaveMenuItem implements MenuItemFactory {

    @Override
    public JMenuItem createMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save");

        return saveMenuItem;
    }
}
