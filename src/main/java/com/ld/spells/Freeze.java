package com.ld.spells;

import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;

public class Freeze extends Spell {
    public Freeze() {
        super("Freeze",
                new String[] { "15 MAG damage + INT and gives enemy 4 freeze", "30 MAG damage + INT and gives enemy 8 freeze",
                        "45 MAG damage + INT and gives enemy 12 freeze", "60 MAG damage + INT and gives enemy 16 freeze",
                        "75 MAG damage + INT and gives enemy 20 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 50, 30);
    }

    public Freeze(JSONObject saved) {
        super(saved,
                new String[] { "15 MAG damage + INT and gives enemy 4 freeze", "30 MAG damage + INT and gives enemy 8 freeze",
                        "45 MAG damage + INT and gives enemy 12 freeze", "60 MAG damage + INT and gives enemy 16 freeze",
                        "75 MAG damage + INT and gives enemy 20 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 30);
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
            power = (15 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
            user.attack(0, defender, power, "snow");
            defender.freeze(level * 4);
            playerHandler.next();
        }
    }
}