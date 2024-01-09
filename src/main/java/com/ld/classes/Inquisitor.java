package com.ld.classes;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalStats;

public class Inquisitor extends Player {
    public Inquisitor(String name) {
        super(name, 60, 50, new GlobalStats(1, 0, 1, 0));
    }
    public Inquisitor(JSONObject saved) {
        super(saved);
    }

    @Override
    public void postAttack(Player defender, String type, boolean basic) {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        super.postAttack(defender, type, basic);
        if (defender.isDead()) {
            playerHandler.halt();
        }
    }

    public String getClassName() {
        return "Inquisitor";
    }
}