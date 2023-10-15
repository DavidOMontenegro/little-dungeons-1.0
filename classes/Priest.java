package classes;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Priest extends Player {
    public Priest(String name) {
        super(name, 55, 65, new GlobalStats(0, 0, 2, 0));
    }
    public Priest(JSONObject saved) {
        super(saved);
    }

    public String getClassName() {
        return "Priest";
    }
}