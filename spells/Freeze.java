package spells;

import org.json.JSONObject;

import classes.Player;
import util.Action;

import java.util.ArrayList;
import java.util.Scanner;

public class Freeze extends Spell {
    Scanner scan;

    public Freeze(Scanner scanner) {
        super("Freeze",
                new String[] { "15 MAG damage + INT and gives enemy 4 freeze", "30 MAG damage + INT and gives enemy 8 freeze",
                        "45 MAG damage + INT and gives enemy 12 freeze", "60 MAG damage + INT and gives enemy 16 freeze",
                        "75 MAG damage + INT and gives enemy 20 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 50, 30);
        scan = scanner;
    }
    public Freeze(JSONObject saved) {
        super(saved,
                new String[] { "15 MAG damage + INT and gives enemy 4 freeze", "30 MAG damage + INT and gives enemy 8 freeze",
                        "45 MAG damage + INT and gives enemy 12 freeze", "60 MAG damage + INT and gives enemy 16 freeze",
                        "75 MAG damage + INT and gives enemy 20 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 30);
    }

    @Override
    public int use(int current, ArrayList<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player defender;
        String type = "snow";
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
                    user.damage(defender, (15 * level) + user.getINT() - defender.getMagDef() + user.getMagAtk());
                    defender.freeze(level * 4);
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
                    user.damage(defender, (15 * level) + user.getINT() - defender.getMagDef() + user.getMagAtk());
                    defender.freeze(level * 4);
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