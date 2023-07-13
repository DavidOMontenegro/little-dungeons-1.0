package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import util.Item;

public class Paladin extends Player {
    public Paladin(String name) {
        super(name, 60, 30, 1, 0, 1, 0);
    }
    public Paladin(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    public String getClassName() {
        return "Paladin";
    }
}