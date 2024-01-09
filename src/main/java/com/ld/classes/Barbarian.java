package com.ld.classes;

import org.json.JSONObject;

import com.ld.global.GlobalStats;

public class Barbarian extends Player {
    public Barbarian(String name) {
        super(name, 60, 15, new GlobalStats(2, 0, 0, 0));
    }
    public Barbarian(JSONObject saved) {
        super(saved);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        if (hp < totalhp / 2 && basic) {
            atkStats.boostAll(8);
        }

        return super.preAttack(d20, type, basic);
    }

    @Override
    public void postAttack(Player defender, String type, boolean basic) {
        if (hp < totalhp / 2 && basic) {
            atkStats.boostAll(-8);
        }

        super.postAttack(defender, type, basic);
    }

    public String getClassName() {
        return "Barbarian";
    }
}