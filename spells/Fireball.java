package spells;

import classes.Player;
import util.Action;

import java.util.ArrayList;
import java.util.Scanner;

public class Fireball extends Spell {
    Scanner scan;

    public Fireball(Scanner scanner) {
        super("Fireball",
                new String[] { "20 MAG damage + INT", "40 MAG damage + INT", "60 MAG damage + INT",
                        "80 MAG damage + INT", "100 MAG damage + INT" },
                new String[] { "Active", "Magic", "Fire" }, 50, 25);
        scan = scanner;
    }

    @Override
    public int use(int current, ArrayList<Player> active) {
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
                id = scan.nextLine();
            }
            try {
                int playerId = Integer.parseInt(id);
                if (playerId <= active.indexOf(user) && playerId > 0) {
                    super.use(current, active);
                    defender = active.get(playerId - 1);
                    defender.preDefend(user, type);
                    user.preAttack(0, type, false);
                    user.damage(defender, (20 * level) + user.getINT() - defender.getMagDef() + user.getMagAtk());
                    defender.postDefend(user, 3, type, false);
                    current = user.postAttack(defender, type, false, current);
                    current = Action.next(current, activeNumber);
                    Action.wizard(active.get(current));
                    return current;
                } else if (playerId < activeNumber && playerId > 0) {
                    super.use(current, active);
                    defender = active.get(playerId);
                    defender.preDefend(user, type);
                    user.preAttack(0, type, false);
                    user.damage(defender, (20 * level) + user.getINT() - defender.getMagDef() + user.getMagAtk());
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