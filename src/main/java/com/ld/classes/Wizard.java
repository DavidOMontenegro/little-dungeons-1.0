package com.ld.classes;

import org.json.JSONObject;

import com.ld.global.GlobalStats;

public class Wizard extends Player {
    public Wizard(String name) {
        super(name, 45, 80, new GlobalStats(0, 0, 0, 2));
    }
    public Wizard(JSONObject saved) {
        super(saved);
    }

    public String getClassName() {
        return "Wizard";
    }
}