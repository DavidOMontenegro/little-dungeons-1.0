package com.ld.spells;

import org.json.JSONObject;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;
import com.ld.util.Action;

import java.util.List;

public class Healing extends Spell {
    public Healing() {
        super("Divine Healing",
                new String[] { "Heal someone 25 HP + WIS", "Heal someone 40 HP + WIS", "Heal someone 55 HP + WIS",
                        "Heal someone 70 HP + WIS", "Heal someone 85 HP + WIS" },
                new String[] { "Active", "Heal" }, 50, 25);
    }
    public Healing(JSONObject saved) {
        super(saved,
                new String[] { "Heal someone 25 HP + WIS", "Heal someone 40 HP + WIS", "Heal someone 55 HP + WIS",
                        "Heal someone 70 HP + WIS", "Heal someone 85 HP + WIS" },
                new String[] { "Active", "Heal" }, 25);
    }

    @Override
    public int use(int current, List<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player healed;
        String id;
        if (user.getMP() < mpCost) {
            System.out.println("You don't have enough MP.");
            return current;
        }
        while (!selected) {
            System.out.println("Which player will you heal?");
            for (int i = 0; i < activeNumber; i++) {
                Player player = active.get(i);
                System.out.println((i + 1) + "- " + player.getName());
            }
            System.out.println((activeNumber) + "- Exit");
            id = GlobalScanner.nextLine();
            try {
                int playerId = Integer.parseInt(id);
                if (playerId < activeNumber && playerId > 0) {
                    super.use(current, active);
                    healed = active.get(playerId - 1);
                    user.healHP(healed, (level * 15) + user.getStat("basic", 'w'));
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