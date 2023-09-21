package spells;

import org.json.JSONObject;

import global.GlobalScanner;
import classes.Player;
import util.Action;

import java.util.ArrayList;

public class Tackle extends Spell {
    public Tackle() {
        super("Tackle",
                new String[] { "8 BRU damage + BRU, SAC, or MAG DEF", "16 BRU damage + BRU, SAC, or MAG DEF",
                        "24 BRU damage + BRU, SAC, or MAG DEF", "32 BRU damage + BRU, SAC, or MAG DEF",
                        "40 BRU damage + BRU, SAC, or MAG DEF" },
                new String[] { "Active", "Brute" }, 50, 25);
    }
    public Tackle(JSONObject saved) {
        super(saved,
                new String[] { "8 BRU damage + BRU, SAC, or MAG DEF", "16 BRU damage + BRU, SAC, or MAG DEF",
                        "24 BRU damage + BRU, SAC, or MAG DEF", "32 BRU damage + BRU, SAC, or MAG DEF",
                        "40 BRU damage + BRU, SAC, or MAG DEF" },
                new String[] { "Active", "Brute" }, 25);
    }

    @Override
    public int use(int current, ArrayList<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player defender;
        String type = "spell";
        String id = "1";
        int power = 8;

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
                    while (!selected) {
                        System.out.println(
                                "Which armour type will you use?\n1- Brute (" + user.getStat("defense", 's') + " DEF)\n2- Sacred ("
                                        + user.getStat("defense", 'w') + " DEF)\n3- Magic (" + user.getStat("defense", 'i') + " DEF)");
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                power = (20 * level) - defender.getStat("defense", 's') + user.getStat("defense", 's');
                                selected = true;
                                break;
                            case "2":
                                power = (20 * level) - defender.getStat("defense", 's') + user.getStat("defense", 'w');
                                selected = true;
                                break;
                            case "3":
                                power = (20 * level) - defender.getStat("defense", 's') + user.getStat("defense", 'i');
                                selected = true;
                                break;
                        }
                    }
                    defender.preDefend(user, type);
                    user.preAttack(0, type, false);
                    user.damage(defender, power);
                    defender.postDefend(user, 0, type, false);
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