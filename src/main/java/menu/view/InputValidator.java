package menu.view;

import java.util.List;
import menu.enums.Category;

public class InputValidator {
    private static final String NAME_ERR_MSG = "[ERROR] 이름의 길이는 최소 2글자, 최대 4글자입니다.";
    private static final String NAMES_SIZE_ERR_MSG = "[ERROR] 코치의 수는 최소 2명, 최대 5명입니다.";
    private static final String MENU_ERR_MSG = "[ERROR] 존재하지 않는 메뉴입니다.";
    private static final String MENUS_SIZE_ERR_MSG = "[ERROR] 못먹는 음식은 최소 0개, 최대 2개입니다.";

    public void validateNames(List<String> coachNames) {
        if (invalidNamesSize(coachNames)) {
            throw new IllegalArgumentException(NAMES_SIZE_ERR_MSG);
        }
        if (invalidName(coachNames)) {
            throw new IllegalArgumentException(NAME_ERR_MSG);
        }
    }

    private boolean invalidName(List<String> coachNames) {
        return coachNames.stream()
                .anyMatch(name -> name.length() > 4 || name.length() < 2);
    }

    private boolean invalidNamesSize(List<String> coachNames) {
        return coachNames.size() < 2 || coachNames.size() > 5;
    }

    public void validateMenus(List<String> unedibleMenus) {
        if (invalidMenuSize(unedibleMenus)) {
            throw new IllegalArgumentException(MENUS_SIZE_ERR_MSG);
        }
        if (invalidMenu(unedibleMenus)) {
            throw new IllegalArgumentException(MENU_ERR_MSG);
        }
    }

    private boolean invalidMenuSize(List<String> unedibleMenus) {
        return unedibleMenus.size() > 2;
    }

    private boolean invalidMenu(List<String> unedibleMenus) {
        return unedibleMenus.stream().anyMatch(Category::invalidMenu);
    }
}
