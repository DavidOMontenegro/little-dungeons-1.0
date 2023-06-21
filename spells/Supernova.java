package spells;

import classes.Player;
import util.Action;

import java.util.ArrayList;
import java.util.Scanner;

public class Supernova extends Spell {
    Scanner scan;

    public Supernova(Scanner scanner) {
        super("Supernova",
                new String[] { "15 MAG damage + INT and gives all enemies 2 freeze",
                        "30 MAG damage + INT and gives all enemies 4 freeze",
                        "45 MAG damage + INT and gives all enemies 6 freeze",
                        "60 MAG damage + INT and gives all enemies 8 freeze",
                        "75 MAG damage + INT and gives all enemies 10 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 80, 55);
        scan = scanner;
    }

    @Override
    public int use(int current, ArrayList<Player> active) {
        int activeNumber = active.size();
        Player user = active.get(current);
        String type = "snow";
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
            user.damage(defender, (15 * level) + user.getINT() - defender.getMagDef() + user.getMagAtk());
            defender.freeze(level * 2);
            defender.postDefend(user, 3, type, false);
            current = user.postAttack(defender, type, false, current);
        }
        current = Action.next(current, activeNumber);
        Action.wizard(active.get(current));
        return current;
    }
}