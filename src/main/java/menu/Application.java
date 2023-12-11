package menu;

import menu.controller.MenuRecommender;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MenuRecommender menuRecommender = new MenuRecommender(new InputView(), new OutputView());
        menuRecommender.run();
    }
}
