package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Priest extends Player {
    public Priest(String name) {
        super(name, 55, 65, new GlobalStats(0, 0, 2, 0));
    }
    public Priest(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    public String getClassName() {
        return "Priest";
    }
}