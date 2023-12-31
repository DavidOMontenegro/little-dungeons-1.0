package com.ld.classes;

import org.json.JSONObject;

import com.ld.global.GlobalStats;

public class Assassin extends Player {
    public Assassin(String name) {
        super(name, 45, 20, new GlobalStats(0, 2, 0, 0));
    }
    public Assassin(JSONObject saved) {
        super(saved);
    }
    
    public String getClassName() {
        return "Assassin";
    }
}