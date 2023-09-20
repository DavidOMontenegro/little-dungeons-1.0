package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Thief extends Player {
    public Thief(String name) {
        super(name, 40, 50, new GlobalStats(0, 1, 0, 0));
    }
    public Thief(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    public String getClassName() {
        return "Thief";
    }
}