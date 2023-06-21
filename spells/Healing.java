package spells;

import classes.Player;
import util.Action;

import java.util.ArrayList;
import java.util.Scanner;

public class Healing extends Spell {
    Scanner scan;

    public Healing(Scanner scanner) {
        super("Divine Healing",
                new String[] { "Heal someone 25 HP + WIS", "Heal someone 40 HP + WIS", "Heal someone 55 HP + WIS",
                        "Heal someone 70 HP + WIS", "Heal someone 85 HP + WIS" },
                new String[] { "Active", "Heal" }, 50, 25);
        scan = scanner;
    }

    @Override
    public int use(int current, ArrayList<Player> active) {
        int activeNumber = active.size();
        boolean selected = false;
        Player user = active.get(current);
        Player healed;
        String id = "1";
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
            id = scan.nextLine();
            try {
                int playerId = Integer.parseInt(id);
                if (playerId < activeNumber && playerId > 0) {
                    super.use(current, active);
                    healed = active.get(playerId - 1);
                    user.healHP(healed, (level * 15) + user.getWIS());
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