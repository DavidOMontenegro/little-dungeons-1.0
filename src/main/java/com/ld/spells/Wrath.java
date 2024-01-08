package com.ld.spells;

import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

public class Wrath extends Spell {
    public Wrath() {
        super("Holy Wrath",
                new String[] { "20 SAC damage + WIS", "40 SAC damage + WIS", "60 SAC damage + WIS",
                        "80 SAC damage + WIS", "100 SAC damage + WIS" },
                new String[] { "Active", "Sacred" }, 50, 20);
    }

    public Wrath(JSONObject saved) {
        super(saved,
                new String[] { "20 SAC damage + WIS", "40 SAC damage + WIS", "60 SAC damage + WIS",
                        "80 SAC damage + WIS", "100 SAC damage + WIS" },
                new String[] { "Active", "Sacred" }, 20);
    }

    @Override
    public int use(int current, List<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player defender;
        int power;
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
                    power = (20 * level) + user.getStat("basic", 'w') - defender.getStat("defense", 'w') + user.getStat("attack", 'w');
                    current = user.attack(0, defender, power, current, "spell");
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