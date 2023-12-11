package menu.model;

import java.util.List;

public class UnedibleMenus {
    private final List<String> unedibleMenus;

    UnedibleMenus(List<String> unedibleMenus) {
        this.unedibleMenus = unedibleMenus;
    }

    public boolean unedible(String menu) {
        return unedibleMenus.contains(menu);
    }
}
