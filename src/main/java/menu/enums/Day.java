package menu.enums;

import java.util.Arrays;

public enum Day {
    MON(0),
    TUE(1),
    WED(2),
    THU(3),
    FRI(4),
    DAYS(5);

    private final int number;

    Day(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public static Day getDay(int number) {
        return Arrays.stream(values())
                .filter(day -> day.number == number)
                .findFirst()
                .orElse(DAYS);
    }
}
