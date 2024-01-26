package com.ld.spells;

import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;

public class Wrath extends Spell {
    public Wrath() {
        super("Holy Wrath",
                new String[] { "20 SAC damage + WIS", "40 SAC damage + WIS", "60 SAC damage + WIS",
                        "80 SAC damage + WIS", "100 SAC damage + WIS" },
                new String[] { "Active", "Sacred" }, 50, 20);
    }

    public Wrath(JSONObject saved) {
        super(saved,
                new String[] { "20 SAC damage + WIS", "40 SAC damage + WIS", "60 SAC damage + WIS",
                        "80 SAC damage + WIS", "100 SAC damage + WIS" },
                new String[] { "Active", "Sacred" }, 20);
    }

    @Override
    public void use() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        Player defender;
        int power;

        if (!mpCheck(user)) {
            return;
        }

        defender = PlayerChooser.choosePlayer("Which player will you attack?");

        if (!defender.equals(null)) {
            super.use();
            power = (20 * level) + user.getStat("basic", 'w') - defender.getStat("defense", 'w') + user.getStat("attack", 'w');
            user.attack(0, defender, power, "spell");
            playerHandler.next();
        }
    }
}