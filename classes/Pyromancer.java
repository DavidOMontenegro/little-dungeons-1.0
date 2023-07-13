package classes;

import java.util.ArrayList;

import org.json.JSONObject;

import util.Item;

public class Pyromancer extends Player {
    public Pyromancer(String name) {
        super(name, 60, 50, 0, 0, 0, 1);
    }
    public Pyromancer(JSONObject saved, ArrayList<Item> items) {
        super(saved, items);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        if (type.equals("fire")) {
            bruATK += 6;
            quiATK += 6;
            sacATK += 6;
            magATK += 6;
        }

        return super.preAttack(d20, type, basic);
    }

    @Override
    public int postAttack(Player defender, String type, boolean basic, int current) {
        if (type.equals("fire")) {
            bruATK -= 6;
            quiATK -= 6;
            sacATK -= 6;
            magATK -= 6;
        }

        return super.postAttack(defender, type, basic, current);
    }

    @Override
    public void preDefend(Player attacker, String type) {
        if (type.equals("fire")) {
            bruDEF += 8000;
            quiDEF += 8000;
            sacDEF += 8000;
            magDEF += 8000;
        }

        super.preDefend(attacker, type);
    }

    @Override
    public void postDefend(Player attacker, int basic, String type, boolean counter) {
        if (type.equals("fire")) {
            bruDEF -= 8000;
            quiDEF -= 8000;
            sacDEF -= 8000;
            magDEF -= 8000;
        }

        super.postDefend(attacker, basic, type, counter);
    }

    public String getClassName() {
        return "Pyromancer";
    }
}