package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalScanner;
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
        int active = playerHandler.getActive();
        Player user = playerHandler.current();
        Player defender;
        int power;
        String id = "1";

        if (!mpCheck(user)) {
            return;
        }

        while (true) {
            if (active != 2) {
                System.out.println("Which player will you attack?");
                for (int i = 0, j = 0; i < active; i++) {
                    Player player = playerHandler.getPlayer(i);
                    if (player == user) {
                        continue;
                    }
                    j++;
                    System.out.println(j + "- " + player.getName());
                }
                System.out.println((active) + "- Exit");
                id = GlobalScanner.nextLine();
            }
            try {
                int playerId = Integer.parseInt(id);
                if (playerId < active && playerId > 0) {
                    super.use();
                    defender = playerId <= playerHandler.indexOf(user) ? playerHandler.getPlayer(playerId - 1) : playerHandler.getPlayer(playerId);
                    power = (20 * level) + user.getStat("basic", 'w') - defender.getStat("defense", 'w') + user.getStat("attack", 'w');
                    user.attack(0, defender, power, "spell");
                    playerHandler.next();
                    return;
                } else if (playerId == active) {
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }
}