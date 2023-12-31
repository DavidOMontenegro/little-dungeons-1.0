package com.ld.classes;

import org.json.JSONObject;

import com.ld.global.GlobalStats;

public class Thief extends Player {
    public Thief(String name) {
        super(name, 40, 50, new GlobalStats(0, 1, 0, 0));
    }
    public Thief(JSONObject saved) {
        super(saved);
    }

    public String getClassName() {
        return "Thief";
    }
}