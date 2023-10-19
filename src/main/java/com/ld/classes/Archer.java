package com.ld.classes;

import org.json.JSONObject;

import com.ld.global.GlobalStats;

public class Archer extends Player {
    public Archer(String name) {
        super(name, 50, 40, new GlobalStats(0, 1, 0, 1));
    }
    public Archer(JSONObject saved) {
        super(saved);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        return super.preAttack(d20, type, basic) && (right == null || !right.getType().contains("Bow"));
    }

    public String getClassName() {
        return "Archer";
    }
}