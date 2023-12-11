package menu.model;

import java.util.ArrayList;
import java.util.List;
import menu.enums.Category;
import menu.utils.Selector;

public class DailyCategory {
    private final List<Category> dailyCategory;
    public static final int NUMBER_OF_DAYS = 5;
    private static final int MOST_DUPLICATE = 2;

    public DailyCategory() {
        dailyCategory = new ArrayList<>();
        while (dailyCategory.size() != NUMBER_OF_DAYS) {
            addCategory();
        }
    }

    private void addCategory() {
        Category newCategory = Selector.selectCategory();
        boolean valid = dailyCategory.stream()
                .filter(category -> category == newCategory)
                .count() != MOST_DUPLICATE;
        if (valid) {
            dailyCategory.add(newCategory);
        }
    }

    public Category getCategory(int i) {
        return dailyCategory.get(i);
    }

    public String getCategoryName(int i) {
        return dailyCategory.get(i).getName();
    }
}
