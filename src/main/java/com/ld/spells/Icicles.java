package com.ld.spells;

import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import org.json.JSONObject;

import com.ld.classes.Player;

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
        Player user = playerHandler.current();
        Player defender;
        int spikes = level;
        int power;

        if (!mpCheck(user)) {
            return;
        }

        defender = PlayerChooser.choosePlayer("Which player will you attack?");

        if (!defender.equals(null)) {
            super.use();
            power = 15 + user.getStat("basic", 'i') - defender.getStat("defense", 'i') + user.getStat("attack", 'i');
            while (!user.isDead() && !defender.isDead() && spikes != 0) {
                user.attack(0, defender, power, "snow");
                defender.freeze(3);
                spikes--;
            }
            playerHandler.next();
        }
    }
}