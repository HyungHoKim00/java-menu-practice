package menu.model;

import java.util.ArrayList;
import java.util.List;

public class Coaches {
    private final List<String> coachNames;
    private final List<UnedibleMenus> totalUnedibleMenus;

    public Coaches(List<String> coachNames, List<List<String>> totalUnedibleMenus) {
        this.coachNames = coachNames;
        this.totalUnedibleMenus = new ArrayList<>();
        for (int i = 0; i < coachNames.size(); i++) {
            this.totalUnedibleMenus.add(new UnedibleMenus(totalUnedibleMenus.get(i)));
        }
    }

    public int getCoachNumber() {
        return coachNames.size();
    }

    public String getCoachName(int coachIndex) {
        return coachNames.get(coachIndex);
    }

    public boolean unedible(int coachIndex, String menu) {
        return totalUnedibleMenus.get(coachIndex).unedible(menu);
    }
}
