package menu.model;

import static menu.enums.Day.DAYS;

import java.util.HashMap;
import java.util.Map;
import menu.enums.Category;
import menu.enums.Day;
import menu.utils.Selector;

public class DailyCategory {
    private static final int MOST_DUPLICATE = 2;
    private final Map<Day, Category> dailyCategory;

    public DailyCategory() {
        dailyCategory = new HashMap<>();
        while (dailyCategory.size() != DAYS.getNumber()) {
            addCategory();
        }
    }

    private void addCategory() {
        Category newCategory = Selector.selectCategory();
        boolean valid = dailyCategory.values().stream()
                .filter(category -> category == newCategory)
                .count() != MOST_DUPLICATE;
        if (valid) {
            dailyCategory.put(Day.getDay(dailyCategory.size()), newCategory);
        }
    }

    public Category getCategory(Day day) {
        return dailyCategory.get(day);
    }

    public String getCategoryName(Day day) {
        return getCategory(day).getName();
    }
}
