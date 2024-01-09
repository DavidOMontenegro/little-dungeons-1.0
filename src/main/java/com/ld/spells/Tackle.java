package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

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
        int activeNumber = playerHandler.getActive();
        Player user = playerHandler.current();
        Player defender;
        String id = "1";
        int power = 0;

        if (!mpCheck(user)) {
            return;
        }
        while (true) {
            if (activeNumber != 2) {
                System.out.println("Which player will you attack?");
                for (int i = 0, j = 0; i < activeNumber; i++) {
                    Player player = playerHandler.getPlayer(i);
                    if (player == user) {
                        continue;
                    }
                    j++;
                    System.out.println(j + "- " + player.getName());
                }
                System.out.println((activeNumber) + "- Exit");
                id = GlobalScanner.nextLine();
            }
            try {
                int playerId = Integer.parseInt(id);
                if (playerId < activeNumber && playerId > 0) {
                    super.use();
                    defender = playerId <= playerHandler.indexOf(user) ? playerHandler.getPlayer(playerId - 1) : playerHandler.getPlayer(playerId);
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
                    return;
                } else if (playerId == activeNumber) {
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }
}