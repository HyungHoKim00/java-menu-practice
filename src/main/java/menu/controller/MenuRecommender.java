package menu.controller;

import static menu.enums.Day.DAYS;

import java.util.ArrayList;
import java.util.List;
import menu.enums.Day;
import menu.model.Coaches;
import menu.model.DailyCategory;
import menu.model.Menus;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuRecommender {
    private final InputView inputView;
    private final OutputView outputView;
    private Coaches coaches;
    private Menus menus;

    public MenuRecommender(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setCoaches();
        DailyCategory dailyCategory = new DailyCategory();
        this.menus = new Menus(coaches, dailyCategory);
        outputView.printTotalMenu(buildOutput(menus));
    }

    private void setCoaches() {
        outputView.printStartMessage();
        outputView.printCoachNamesRequestMessage();
        List<String> coachNames = validateCoachNames();
        List<List<String>> unedibleMenus = new ArrayList<>();
        for (String coachName : coachNames) {
            outputView.printUnedibleMenuRequestMessage(coachName);
            List<String> menus = validateUnedibleMenus();
            unedibleMenus.add(menus);
        }
        coaches = new Coaches(coachNames, unedibleMenus);
    }

    private List<String> buildOutput(Menus menus) {
        List<String> buildOutput = new ArrayList<>();
        StringBuilder dailyCategories = new StringBuilder("[ 카테고리");
        for (int i = 0; i < DAYS.getNumber(); i++) {
            dailyCategories.append(" | ").append(menus.getCategoryName(Day.getDay(i)));
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

    private String coachMenuRecommendation(int coachIndex) {
        StringBuilder coachMenuRecommendation = new StringBuilder("[ ");
        coachMenuRecommendation.append(coaches.getCoachName(coachIndex));
        for (int i = 0; i < DAYS.getNumber(); i++) {
            coachMenuRecommendation.append(" | ").append(menus.getMenu(coachIndex, i));
        }
        coachMenuRecommendation.append(" ]");
        return String.valueOf(coachMenuRecommendation);
    }
}
