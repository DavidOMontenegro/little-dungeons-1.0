package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import global.GlobalStats;
import util.Item;

public class Paladin extends Player {
    public Paladin(String name) {
        super(name, 60, 30, new GlobalStats(1, 0, 1, 0));
    }
    public Paladin(JSONObject saved) {
        super(saved);
    }

    public String getClassName() {
        return "Paladin";
    }
}