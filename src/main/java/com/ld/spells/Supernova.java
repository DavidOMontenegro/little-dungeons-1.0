package com.ld.spells;

import org.json.JSONObject;

import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

public class Supernova extends Spell {
    public Supernova() {
        super("Supernova",
                new String[] { "15 MAG damage + INT and gives all enemies 2 freeze",
                        "30 MAG damage + INT and gives all enemies 4 freeze",
                        "45 MAG damage + INT and gives all enemies 6 freeze",
                        "60 MAG damage + INT and gives all enemies 8 freeze",
                        "75 MAG damage + INT and gives all enemies 10 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 80, 55);
    }
    public Supernova(JSONObject saved) {
        super(saved,
                new String[] { "15 MAG damage + INT and gives all enemies 2 freeze",
                        "30 MAG damage + INT and gives all enemies 4 freeze",
                        "45 MAG damage + INT and gives all enemies 6 freeze",
                        "60 MAG damage + INT and gives all enemies 8 freeze",
                        "75 MAG damage + INT and gives all enemies 10 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 55);
    }

    @Override
    public int use(int current, List<Player> active) {
        int activeNumber = active.size();
        Player user = active.get(current);
        int power;
        if (user.getMP() < mpCost) {
            System.out.println("You don't have enough MP.");
            return current;
        }
        super.use(current, active);
        for (Player defender : active) {
            if (user == defender) {
                continue;
            }
            power = (15 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
            current = user.attack(0, defender, power, current, "snow");
        }
        current = Action.next(current, activeNumber);
        Action.wizard(active.get(current));
        return current;
    }
}