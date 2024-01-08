package com.ld.spells;

import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

public class Icicles extends Spell {
    public Icicles() {
        super("Icicles",
                new String[] { "Shoots an icicle doing 10 MAG damage + INT and giving enemy 3 freeze",
                        "Shoots two icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots three icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots four icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots five icicles doing 10 MAG damage + INT and giving enemy 3 freeze each" },
                new String[] { "Active", "Magic", "Snow" }, 50, 30);
    }
    public Icicles(JSONObject saved) {
        super(saved,
                new String[] { "Shoots an icicle doing 10 MAG damage + INT and giving enemy 3 freeze",
                        "Shoots two icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots three icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots four icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots five icicles doing 10 MAG damage + INT and giving enemy 3 freeze each" },
                new String[] { "Active", "Magic", "Snow" }, 30);
    }

    @Override
    public int use(int current, List<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player defender;
        String id = "1";
        int lvl = level;
        int power;
        int freeze = user.getFreeze();
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
                    power = 15 + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
                    while (!user.isDead() && !defender.isDead() && lvl != 0) {
                        current = user.attack(0, defender, power, current, "snow");
                        defender.freeze(3);
                        lvl--;
                    }
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