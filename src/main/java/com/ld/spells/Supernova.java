package com.ld.spells;

import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;

public class Supernova extends Spell {
    public Supernova() {
        super("Supernova",
                new String[] { "15 MAG damage + INT and gives all enemies 2 freeze",
                        "30 MAG damage + INT and gives all enemies 4 freeze",
                        "45 MAG damage + INT and gives all enemies 6 freeze",
                        "60 MAG damage + INT and gives all enemies 8 freeze",
                        "75 MAG damage + INT and gives all enemies 10 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 80, 55);
    }
    public Supernova(JSONObject saved) {
        super(saved,
                new String[] { "15 MAG damage + INT and gives all enemies 2 freeze",
                        "30 MAG damage + INT and gives all enemies 4 freeze",
                        "45 MAG damage + INT and gives all enemies 6 freeze",
                        "60 MAG damage + INT and gives all enemies 8 freeze",
                        "75 MAG damage + INT and gives all enemies 10 freeze" },
                new String[] { "Active", "Magic", "Snow" }, 55);
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
            power = (15 * level) + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
            user.attack(0, defender, power, "snow");
        }
        playerHandler.next();
    }
}