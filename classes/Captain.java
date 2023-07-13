package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import util.Item;

public class Captain extends Player {
    public Captain(String name) {
        super(name, 50, 5, 1, 0, 0, 1);
    }
    public Captain(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    @Override
    public void postDefend(Player attacker, int basic, String type, boolean counter) {
        if (right != null) {
            if (right.getType().contains("Shield") && !dead && counter) {
                damage(attacker, 6);
            }
        }

        super.postDefend(attacker, basic, type, counter);
    }

    public String getClassName() {
        return "Captain";
    }
}