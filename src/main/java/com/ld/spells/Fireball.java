package com.ld.spells;

import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;

public class Fireball extends Spell {
    public Fireball() {
        super("Fireball",
                new String[] { "20 MAG damage + INT", "40 MAG damage + INT", "60 MAG damage + INT",
                        "80 MAG damage + INT", "100 MAG damage + INT" },
                new String[] { "Active", "Magic", "Fire" }, 50, 25);
    }

    public Fireball(JSONObject saved) {
        super(saved,
                new String[] { "20 MAG damage + INT", "40 MAG damage + INT", "60 MAG damage + INT",
                        "80 MAG damage + INT", "100 MAG damage + INT" },
                new String[] { "Active", "Magic", "Fire" }, 25);
    }

    @Override
    public void use() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        Player defender;
        int power;
        String id = "1";

        if (!mpCheck(user)) {
            return;
        }

        defender = PlayerChooser.choosePlayer("Which player will you attack?");

        if (!defender.equals(null)) {
            super.use();
            power = (20 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
            user.attack(0, defender, power, "fire");
            playerHandler.next();
        }
    }
}