package classes;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Ninja extends Player {
    public Ninja(String name) {
        super(name, 40, 50, new GlobalStats(1, 1, 0, 0));
    }
    public Ninja(JSONObject saved) {
        super(saved);
    }

    @Override
    public void postDefend(Player attacker, int basic, String type, boolean counter) {
        if (!dead && counter) {
            damage(attacker, (int)(Math.random() * 8.0) + 1);
        }

        super.postDefend(attacker, basic, type, counter);
    }

    public String getClassName() {
        return "Ninja";
    }
}