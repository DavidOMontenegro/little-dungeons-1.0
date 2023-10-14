package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Warlock extends Player {
    public Warlock(String name) {
        super(name, 50, 75, new GlobalStats(0, 1, 0, 1));
    }
    public Warlock(JSONObject saved) {
        super(saved);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        if (basic) {
            healHP((int) (d20 / 2));
        }
        return super.preAttack(d20, type, basic);
    }

    public String getClassName() {
        return "Warlock";
    }
}