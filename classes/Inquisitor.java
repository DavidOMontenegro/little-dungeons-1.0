package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import util.Item;

public class Inquisitor extends Player {
    public Inquisitor(String name) {
        super(name, 60, 50, 1, 0, 1, 0);
    }
    public Inquisitor(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    @Override
    public int postAttack(Player defender, String type, boolean basic, int current) {
        super.postAttack(defender, type, basic, current);
        return defender.isDead() ? --current : current;
    }

    public String getClassName() {
        return "Inquisitor";
    }
}