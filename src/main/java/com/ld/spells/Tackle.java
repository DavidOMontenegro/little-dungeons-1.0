package com.ld.spells;

import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;

public class Tackle extends Spell {
    public Tackle() {
        super("Tackle",
                new String[] { "8 BRU damage + BRU, SAC, or MAG DEF", "16 BRU damage + BRU, SAC, or MAG DEF",
                        "24 BRU damage + BRU, SAC, or MAG DEF", "32 BRU damage + BRU, SAC, or MAG DEF",
                        "40 BRU damage + BRU, SAC, or MAG DEF" },
                new String[] { "Active", "Brute" }, 50, 25);
    }
    public Tackle(JSONObject saved) {
        super(saved,
                new String[] { "8 BRU damage + BRU, SAC, or MAG DEF", "16 BRU damage + BRU, SAC, or MAG DEF",
                        "24 BRU damage + BRU, SAC, or MAG DEF", "32 BRU damage + BRU, SAC, or MAG DEF",
                        "40 BRU damage + BRU, SAC, or MAG DEF" },
                new String[] { "Active", "Brute" }, 25);
    }

    @Override
    public void use() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        Player defender;
        int power = 0;

        if (!mpCheck(user)) {
            return;
        }

        defender = PlayerChooser.choosePlayer("Which player will you attack?");

        if (!defender.equals(null)) {
            super.use();
            while (true) {
                System.out.println(
                        "Which armour type will you use?\n1- Brute (" + user.getStat("defense", 's') + " DEF)\n2- Sacred ("
                                + user.getStat("defense", 'w') + " DEF)\n3- Magic (" + user.getStat("defense", 'i') + " DEF)");
                switch (GlobalScanner.nextLine()) {
                    case "1":
                        power = (8 * level) - defender.getStat("defense", 's') + user.getStat("defense", 's');
                        break;
                    case "2":
                        power = (8 * level) - defender.getStat("defense", 's') + user.getStat("defense", 'w');
                        break;
                    case "3":
                        power = (8 * level) - defender.getStat("defense", 's') + user.getStat("defense", 'i');
                        break;
                }
                if (power != 0) break;
            }
            user.attack(0, defender, power, "spell");
            playerHandler.next();
        }
    }
}