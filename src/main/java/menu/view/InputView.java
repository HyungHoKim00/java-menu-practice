package menu.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    InputValidator inputValidator;
    Scanner in;

    public InputView() {
        this.inputValidator = new InputValidator();
        in = new Scanner(System.in);
    }

    public List<String> readCoachNames() {
        String input = in.nextLine();
        List<String> coachNames = List.of(input.split(","));
        inputValidator.validateNames(coachNames);
        return coachNames;
    }

    public List<String> readUnedibleMenus() {
        String input = in.nextLine();
        List<String> unedibleMenus = List.of(input.split(","));
        inputValidator.validateMenus(unedibleMenus);
        return unedibleMenus;
    }
}
