package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Archer extends Player {
    public Archer(String name) {
        super(name, 50, 40, new GlobalStats(0, 1, 0, 1));
    }
    public Archer(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        return super.preAttack(d20, type, basic) ? right == null ? true : !right.getType().contains("Bow") : false;
    }

    public String getClassName() {
        return "Archer";
    }
}