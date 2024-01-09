package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.global.GlobalScanner;
import com.ld.classes.Player;
import com.ld.util.Action;

import java.util.List;

public class Icicles extends Spell {
    public Icicles() {
        super("Icicles",
                new String[] { "Shoots an icicle doing 10 MAG damage + INT and giving enemy 3 freeze",
                        "Shoots two icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots three icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots four icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots five icicles doing 10 MAG damage + INT and giving enemy 3 freeze each" },
                new String[] { "Active", "Magic", "Snow" }, 50, 30);
    }
    public Icicles(JSONObject saved) {
        super(saved,
                new String[] { "Shoots an icicle doing 10 MAG damage + INT and giving enemy 3 freeze",
                        "Shoots two icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots three icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots four icicles doing 10 MAG damage + INT and giving enemy 3 freeze each",
                        "Shoots five icicles doing 10 MAG damage + INT and giving enemy 3 freeze each" },
                new String[] { "Active", "Magic", "Snow" }, 30);
    }

    @Override
    public void use() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        int active = playerHandler.getActive();
        Player user = playerHandler.current();
        Player defender;
        String id = "1";
        int lvl = level;
        int power;
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
                    power = 15 + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
                    while (!user.isDead() && !defender.isDead() && lvl != 0) {
                        user.attack(0, defender, power, "snow");
                        defender.freeze(3);
                        lvl--;
                    }
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