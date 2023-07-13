package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import util.Item;

public class Monk extends Player {
    public Monk(String name) {
        super(name, 55, 50, 0, 1, 1, 0);
    }
    public Monk(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    public String getClassName() {
        return "Monk";
    }
}