package com.ld.spells;

import org.json.JSONObject;

import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

public class Armageddon extends Spell {
    public Armageddon() {
        super("Armageddon",
                new String[] { "30 MAG damage + INT to all enemies",
                        "60 MAG damage + INT to all enemies",
                        "90 MAG damage + INT to all enemies",
                        "120 MAG damage + INT to all enemies",
                        "150 MAG damage + INT to all enemies" },
                new String[] { "Active", "Magic", "Fire" }, 80, 60);
    }

    public Armageddon(JSONObject saved) {
        super(saved,
                new String[] { "30 MAG damage + INT to all enemies",
                        "60 MAG damage + INT to all enemies",
                        "90 MAG damage + INT to all enemies",
                        "120 MAG damage + INT to all enemies",
                        "150 MAG damage + INT to all enemies" },
                new String[] { "Active", "Magic", "Fire" }, 60);
    }

    @Override
    public int use(int current, List<Player> active) {
        int activeNumber = active.size();
        Player user = active.get(current);
        String type = "fire";
        if (user.getMP() < mpCost) {
            System.out.println("You don't have enough MP.");
            return current;
        }
        super.use(current, active);
        for (Player defender : active) {
            if (user == defender) {
                continue;
            }
            defender.preDefend(user, type);
            user.preAttack(0, type, false);
            user.damage(defender, (30 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i'));
            defender.postDefend(user, 3, type, false);
            current = user.postAttack(defender, type, false, current);
        }
        current = Action.next(current, activeNumber);
        Action.wizard(active.get(current));
        return current;
    }
}