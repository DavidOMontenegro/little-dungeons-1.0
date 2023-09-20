package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Wizard extends Player {
    public Wizard(String name) {
        super(name, 45, 80, new GlobalStats(0, 0, 0, 2));
    }
    public Wizard(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    public String getClassName() {
        return "Wizard";
    }
}