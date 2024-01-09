package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

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
    public void use() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        int power;
        if (!mpCheck(user)) {
            return;
        }
        super.use();
        for (Player defender : playerHandler.getPlayers()) {
            if (user == defender) {
                continue;
            }
            power = (30 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
            user.attack(0, defender, power, "fire");
        }
        playerHandler.next();
    }
}