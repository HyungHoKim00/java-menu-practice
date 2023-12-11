package menu.controller;

import static menu.model.DailyCategory.NUMBER_OF_DAYS;

import java.util.ArrayList;
import java.util.List;
import menu.enums.Category;
import menu.model.Coaches;
import menu.model.DailyCategory;
import menu.utils.Selector;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuRecommender {
    private final InputView inputView;
    private final OutputView outputView;
    private Coaches coaches;
    private List<String>[] menus;

    public MenuRecommender(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setCoaches();
        DailyCategory dailyCategory = new DailyCategory();
        setTotalMenu(dailyCategory);
        System.out.println("a");
        outputView.printTotalMenu(buildOutput(dailyCategory));
    }

    private void setCoaches() {
        outputView.printStartMessage();
        outputView.printCoachNamesRequestMessage();
        List<String> coachNames = validateCoachNames();
        List<List<String>> unedibleMenus = new ArrayList<>();
        for (int i = 0; i < coachNames.size(); i++) {
            outputView.printUnedibleMenuRequestMessage(coachNames.get(i));
            List<String> Menus = validateUnedibleMenus();
            unedibleMenus.add(Menus);
        }
        coaches = new Coaches(coachNames, unedibleMenus);
    }

    private void setTotalMenu(DailyCategory dailyCategory) {
        menus = new ArrayList[coaches.getCoachNumber()];
        for (int i = 0; i < coaches.getCoachNumber(); i++) {
            menus[i] = new ArrayList<>();
        }
        for (int i = 0; i < NUMBER_OF_DAYS; i++) {
            setMenu(dailyCategory.getCategory(i));
        }
    }

    private void setMenu(Category category) {
        int coachIndex = 0;
        while (coachIndex != coaches.getCoachNumber()) {
            String menu = Selector.selectMenu(category);
            if (edible(coachIndex, menu)) {
                menus[coachIndex].add(menu);
                coachIndex++;
            }
        }
    }

    private List<String> buildOutput(DailyCategory dailyCategory) {
        List<String> buildOutput = new ArrayList<>();
        StringBuilder dailyCategories = new StringBuilder("[ 카테고리");
        for (int i = 0; i < NUMBER_OF_DAYS; i++) {
            dailyCategories.append(" | ").append(dailyCategory.getCategoryName(i));
        }
        dailyCategories.append(" ]");
        buildOutput.add(String.valueOf(dailyCategories));
        for (int i = 0; i < coaches.getCoachNumber(); i++) {
            buildOutput.add(coachMenuRecommendation(i));
        }
        return buildOutput;
    }


    private List<String> validateCoachNames() {
        List<String> coachNames;
        while (true) {
            try {
                coachNames = inputView.readCoachNames();
                return coachNames;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<String> validateUnedibleMenus() {
        List<String> unedibleMenus;
        while (true) {
            try {
                unedibleMenus = inputView.readUnedibleMenus();
                return unedibleMenus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean edible(int coachIndex, String menu) {
        if (coaches.unedible(coachIndex, menu)) {
            return false;
        }
        if (menus[coachIndex].contains(menu)) {
            return false;
        }
        return true;
    }

    private String coachMenuRecommendation(int coachIndex) {
        StringBuilder coachMenuRecommendation = new StringBuilder("[ ");
        coachMenuRecommendation.append(coaches.getCoachName(coachIndex));
        for (int i = 0; i < NUMBER_OF_DAYS; i++) {
            coachMenuRecommendation.append(" | ").append(menus[coachIndex].get(i));
        }
        coachMenuRecommendation.append(" ]");
        return String.valueOf(coachMenuRecommendation);
    }
}
