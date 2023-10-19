package com.ld.classes.factory;

import com.ld.classes.Archer;
import com.ld.classes.Assassin;
import com.ld.classes.Barbarian;
import com.ld.classes.Captain;
import com.ld.classes.Inquisitor;
import com.ld.classes.Knight;
import com.ld.classes.Monk;
import com.ld.classes.Ninja;
import com.ld.classes.Paladin;
import com.ld.classes.Player;
import com.ld.classes.Priest;
import com.ld.classes.Pyromancer;
import com.ld.classes.Thief;
import com.ld.classes.Warlock;
import com.ld.classes.Wizard;

import org.json.JSONObject;

public class PlayerFactory {
    public Player newPlayer(int classId, String name) {
        switch (classId) {
            case 1:
                return new Barbarian(name);
            case 2:
                return new Assassin(name);
            case 3:
                return new Priest(name);
            case 4:
                return new Wizard(name);
            case 5:
                return new Archer(name);
            case 6:
                return new Monk(name);
            case 7:
                return new Knight(name);
            case 8:
                return new Paladin(name);
            case 9:
                return new Warlock(name);
            case 10:
                return new Captain(name);
            case 11:
                return new Inquisitor(name);
            case 12:
                return new Ninja(name);
            case 13:
                return new Pyromancer(name);
            case 14:
                return new Thief(name);
            default:
                throw new RuntimeException();
        }
    }

    public Player JSONPlayer(JSONObject player) {
        String classId = (String) player.get("class");
        switch (classId) {
            case "Barbarian":
                return new Barbarian(player);
            case "Assassin":
                return new Assassin(player);
            case "Priest":
                return new Priest(player);
            case "Wizard":
                return new Wizard(player);
            case "Archer":
                return new Archer(player);
            case "Monk":
                return new Monk(player);
            case "Dark Knight":
                return new Knight(player);
            case "Paladin":
                return new Paladin(player);
            case "Warlock":
                return new Warlock(player);
            case "Captain":
                return new Captain(player);
            case "Inquisitor":
                return new Inquisitor(player);
            case "Ninja":
                return new Ninja(player);
            case "Pyromancer":
                return new Pyromancer(player);
            case "Thief":
                return new Thief(player);
            default:
                throw new RuntimeException();
        }
    }
}
