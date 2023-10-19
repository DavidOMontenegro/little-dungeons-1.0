package com.ld.spells;

import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

public class Fireball extends Spell {
    public Fireball() {
        super("Fireball",
                new String[] { "20 MAG damage + INT", "40 MAG damage + INT", "60 MAG damage + INT",
                        "80 MAG damage + INT", "100 MAG damage + INT" },
                new String[] { "Active", "Magic", "Fire" }, 50, 25);
    }

    public Fireball(JSONObject saved) {
        super(saved,
                new String[] { "20 MAG damage + INT", "40 MAG damage + INT", "60 MAG damage + INT",
                        "80 MAG damage + INT", "100 MAG damage + INT" },
                new String[] { "Active", "Magic", "Fire" }, 25);
    }

    @Override
    public int use(int current, List<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player defender;
        String type = "fire";
        String id = "1";
        if (user.getMP() < mpCost) {
            System.out.println("You don't have enough MP.");
            return current;
        }
        while (!selected) {
            if (activeNumber != 2) {
                System.out.println("Which player will you attack?");
                for (int i = 0, j = 0; i < activeNumber; i++) {
                    Player player = active.get(i);
                    if (player == user) {
                        continue;
                    }
                    j++;
                    System.out.println(j + "- " + player.getName());
                }
                System.out.println((activeNumber) + "- Exit");
                id = GlobalScanner.nextLine();
            }
            try {
                int playerId = Integer.parseInt(id);
                if (playerId < activeNumber && playerId > 0) {
                    super.use(current, active);
                    defender = playerId <= active.indexOf(user) ? active.get(playerId - 1) : active.get(playerId);
                    defender.preDefend(user, type);
                    user.preAttack(0, type, false);
                    user.damage(defender, (20 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i'));
                    defender.postDefend(user, 3, type, false);
                    current = user.postAttack(defender, type, false, current);
                    current = Action.next(current, activeNumber);
                    Action.wizard(active.get(current));
                    return current;
                } else if (playerId == activeNumber) {
                    selected = true;
                }
            } catch (Exception ignored) {
            }
        }
        return current;
    }
}