package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import util.Item;

public class Assassin extends Player {
    public Assassin(String name) {
        super(name, 45, 20, 0, 2, 0, 0);
    }
    public Assassin(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }
    
    public String getClassName() {
        return "Assassin";
    }
}