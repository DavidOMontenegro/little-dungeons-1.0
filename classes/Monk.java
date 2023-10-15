package classes;

import org.json.JSONObject;

import global.GlobalStats;

public class Monk extends Player {
    public Monk(String name) {
        super(name, 55, 50, new GlobalStats(0, 1, 1, 0));
    }
    public Monk(JSONObject saved) {
        super(saved);
    }

    public String getClassName() {
        return "Monk";
    }
}