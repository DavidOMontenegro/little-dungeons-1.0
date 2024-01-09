package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

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
                    power = (20 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
                    user.attack(0, defender, power, "fire");
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