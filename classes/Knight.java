package classes;

import org.json.JSONObject;

import global.GlobalStats;

public class Knight extends Player {
    int snow = 65;

    public Knight(String name) {
        super(name, 65, 15, new GlobalStats(1, 0, 0, 1));
    }
    public Knight(JSONObject saved) {
        super(saved);
    }

    @Override
    public void preDefend(Player attacker, String type) {
        if (type.equals("snow")) {
            snow = hp;
            hp = 10000;
        }

        super.preDefend(attacker, type);
    }

    @Override
    public void postDefend(Player attacker, int basic, String type, boolean counter) {
        if (type.equals("snow")) {
            int tmp = 10000 - hp;
            hp = snow;
            healHP(tmp);
            snow = hp;
        }

        super.postDefend(attacker, basic, type, counter);
    }

    public String getClassName() {
        return "Dark Knight";
    }
}