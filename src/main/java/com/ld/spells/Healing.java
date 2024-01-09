package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;

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
        int activeNumber = playerHandler.getActive();
        Player user = playerHandler.current();
        Player healed;
        String id;
        if (!mpCheck(user)) {
            return;
        }
        while (true) {
            System.out.println("Which player will you heal?");
            for (int i = 0; i < activeNumber; i++) {
                Player player = playerHandler.getPlayer(i);
                System.out.println((i + 1) + "- " + player.getName());
            }
            System.out.println((activeNumber) + "- Exit");
            id = GlobalScanner.nextLine();
            try {
                int playerId = Integer.parseInt(id);
                if (playerId < activeNumber && playerId > 0) {
                    super.use();
                    healed = playerHandler.getPlayer(playerId - 1);
                    user.healHP(healed, (level * 15) + user.getStat("basic", 'w'));
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