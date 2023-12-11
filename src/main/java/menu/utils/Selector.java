package menu.utils;

import camp.nextstep.edu.missionutils.Randoms;
import menu.enums.Category;

public class Selector {
    public static Category selectCategory() {
        return Category.getCategory(Randoms.pickNumberInRange(1, 5));
    }

    public static String selectMenu(Category category) {
        return Randoms.shuffle(category.getMenus()).get(0);
    }
}
