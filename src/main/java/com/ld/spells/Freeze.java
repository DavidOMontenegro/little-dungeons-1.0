package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalScanner;
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
                    power = (15 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
                    user.attack(0, defender, power, "snow");
                    defender.freeze(level * 4);
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