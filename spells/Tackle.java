package spells;

import classes.Player;
import util.Action;

import java.util.ArrayList;
import java.util.Scanner;

public class Tackle extends Spell {
    Scanner scan;

    public Tackle(Scanner scanner) {
        super("Tackle",
                new String[] { "8 BRU damage + BRU, SAC, or MAG DEF", "16 BRU damage + BRU, SAC, or MAG DEF",
                        "24 BRU damage + BRU, SAC, or MAG DEF", "32 BRU damage + BRU, SAC, or MAG DEF",
                        "40 BRU damage + BRU, SAC, or MAG DEF" },
                new String[] { "Active", "Brute" }, 50, 25);
        scan = scanner;
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
                id = scan.nextLine();
            }
            try {
                int playerId = Integer.parseInt(id);
                if (playerId <= active.indexOf(user) && playerId > 0) {
                    super.use(current, active);
                    defender = active.get(playerId - 1);
                    while (!selected) {
                        System.out.println(
                                "Which armour type will you use?\n1- Brute (" + user.getBruDef() + " DEF)\n2- Sacred ("
                                        + user.getSacDef() + " DEF)\n3- Magic (" + user.getMagDef() + " DEF)");
                        switch (scan.nextLine()) {
                            case "1":
                                power = (20 * level) - defender.getBruDef() + user.getBruDef();
                                selected = true;
                                break;
                            case "2":
                                power = (20 * level) - defender.getBruDef() + user.getSacDef();
                                selected = true;
                                break;
                            case "3":
                                power = (20 * level) - defender.getBruDef() + user.getMagDef();
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
                } else if (playerId < activeNumber && playerId > 0) {
                    super.use(current, active);
                    defender = active.get(playerId);
                    while (!selected) {
                        System.out.println(
                                "Which armour type will you use?\n1- Brute (" + user.getBruDef() + " DEF)\n2- Sacred ("
                                        + user.getSacDef() + " DEF)\n3- Magic (" + user.getMagDef() + " DEF)");
                        switch (scan.nextLine()) {
                            case "1":
                                power = (20 * level) - defender.getBruDef() + user.getBruDef();
                                selected = true;
                                break;
                            case "2":
                                power = (20 * level) - defender.getBruDef() + user.getSacDef();
                                selected = true;
                                break;
                            case "3":
                                power = (20 * level) - defender.getBruDef() + user.getMagDef();
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