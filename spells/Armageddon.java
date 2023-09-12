package spells;

import org.json.JSONObject;

import classes.Player;
import util.Action;

import java.util.ArrayList;

public class Armageddon extends Spell {
    public Armageddon() {
        super("Armageddon",
                new String[] { "30 MAG damage + INT to all enemies",
                        "60 MAG damage + INT to all enemies",
                        "90 MAG damage + INT to all enemies",
                        "120 MAG damage + INT to all enemies",
                        "150 MAG damage + INT to all enemies" },
                new String[] { "Active", "Magic", "Fire" }, 80, 60);
    }

    public Armageddon(JSONObject saved) {
        super(saved,
                new String[] { "30 MAG damage + INT to all enemies",
                        "60 MAG damage + INT to all enemies",
                        "90 MAG damage + INT to all enemies",
                        "120 MAG damage + INT to all enemies",
                        "150 MAG damage + INT to all enemies" },
                new String[] { "Active", "Magic", "Fire" }, 60);
    }

    @Override
    public int use(int current, ArrayList<Player> active) {
        int activeNumber = active.size();
        Player user = active.get(current);
        String type = "fire";
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
            user.damage(defender, (30 * level) + user.getINT() - defender.getMagDef() + user.getMagAtk());
            defender.postDefend(user, 3, type, false);
            current = user.postAttack(defender, type, false, current);
        }
        current = Action.next(current, activeNumber);
        Action.wizard(active.get(current));
        return current;
    }
}