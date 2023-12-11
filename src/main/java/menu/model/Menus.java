package menu.model;

import static menu.enums.Day.DAYS;

import java.util.ArrayList;
import java.util.List;
import menu.enums.Category;
import menu.enums.Day;
import menu.utils.Selector;

public class Menus {
    private final DailyCategory dailyCategory;
    private final Coaches coaches;
    private final List<String>[] menus;

    public Menus(Coaches coaches, DailyCategory dailyCategory) {
        this.dailyCategory = dailyCategory;
        this.coaches = coaches;
        menus = new ArrayList[coaches.getCoachNumber()];
        for (int i = 0; i < menus.length; i++) {
            menus[i] = new ArrayList<>();
        }
        for (int i = 0; i < DAYS.getNumber(); i++) {
            setMenu(dailyCategory.getCategory(Day.getDay(i)));
        }
    }

    public String getCategoryName(Day day) {
        return dailyCategory.getCategoryName(day);
    }

    public String getMenu(int coachIndex, int i) {
        return menus[coachIndex].get(i);
    }


    private void setMenu(Category category) {
        int coachIndex = 0;
        while (coachIndex != menus.length) {
            String menu = Selector.selectMenu(category);
            if (edible(coachIndex, menu)) {
                menus[coachIndex].add(menu);
                coachIndex++;
            }
        }
    }

    private boolean edible(int coachIndex, String menu) {
        if (coaches.unedible(coachIndex, menu)) {
            return false;
        }
        return !menus[coachIndex].contains(menu);
    }
}
