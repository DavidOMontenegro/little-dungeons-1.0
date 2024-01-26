package com.ld.spells;

import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;

public class Healing extends Spell {
    public Healing() {
        super("Divine Healing",
                new String[] { "Heal someone 25 HP + WIS", "Heal someone 40 HP + WIS", "Heal someone 55 HP + WIS",
                        "Heal someone 70 HP + WIS", "Heal someone 85 HP + WIS" },
                new String[] { "Active", "Heal" }, 50, 25);
    }
    public Healing(JSONObject saved) {
        super(saved,
                new String[] { "Heal someone 25 HP + WIS", "Heal someone 40 HP + WIS", "Heal someone 55 HP + WIS",
                        "Heal someone 70 HP + WIS", "Heal someone 85 HP + WIS" },
                new String[] { "Active", "Heal" }, 25);
    }

    @Override
    public void use() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        Player healed;
        String id;

        if (!mpCheck(user)) {
            return;
        }

        healed = PlayerChooser.choosePlayer("Which player will you heal?");

        if (!healed.equals(null)) {
            super.use();
            user.healHP(healed, (level * 15) + user.getStat("basic", 'w'));
            playerHandler.next();
        }
    }
}