package classes;

import org.json.JSONObject;

import global.GlobalStats;

public class Inquisitor extends Player {
    public Inquisitor(String name) {
        super(name, 60, 50, new GlobalStats(1, 0, 1, 0));
    }
    public Inquisitor(JSONObject saved) {
        super(saved);
    }

    @Override
    public int postAttack(Player defender, String type, boolean basic, int current) {
        super.postAttack(defender, type, basic, current);
        return defender.isDead() ? --current : current;
    }

    public String getClassName() {
        return "Inquisitor";
    }
}