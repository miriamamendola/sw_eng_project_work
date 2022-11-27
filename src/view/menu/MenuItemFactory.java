package src.view.menu;

import javax.swing.*;

/**
 * The concrete classes of this interface are factory classes of menu items
 * placed inside the JMenuBar.
 */
public interface MenuItemFactory {
    JMenuItem createMenuItem();
}
