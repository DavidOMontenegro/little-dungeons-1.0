package com.ld.classes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.ld.global.GlobalItems;
import com.ld.global.GlobalScanner;
import com.ld.global.GlobalSkills;
import com.ld.global.GlobalSpells;
import com.ld.global.GlobalStats;
import com.ld.spells.*;
import com.ld.util.*;
import com.ld.util.tools.Articles;

public abstract class Player {
    List<Item> items = GlobalItems.addItems();
    String name;
    int hp;
    int totalhp;
    int mp;
    int totalmp;
    GlobalStats baseStats = new GlobalStats(0, 0, 0, 0);
    GlobalStats boostStats = new GlobalStats(0, 0, 0, 0);
    GlobalStats basicStats = new GlobalStats(0, 0, 0, 0);
    GlobalStats atkStats = new GlobalStats(0, 0, 0, 0);
    GlobalStats defStats = new GlobalStats(0, 0, 0, 0);
    int healBoost = 0;
    int freeze = 0;
    int level = 1;
    int trophies = 0;
    int minRank = 0;
    int rank = 33;
    int upgrades = 0;
    int money = 50;
    boolean dead = false;
    Item head = null;
    Item body = null;
    Item feet = null;
    Item left = null;
    Item right = null;
    Spell[] spells = new Spell[] { null, null, null, null, null, null };
    List<Item> backpack = new ArrayList<>();

    public String getName() {
        return name;
    }

    public abstract String getClassName();

    public int getRank() {
        return rank;
    }

    public int getTrophies() {
        return trophies;
    }

    public String seeGold() {
        return Integer.toString(money);
    }

    public int getStat(String type, char stat) {
        GlobalStats stats = basicStats;

        switch (type) {
            case "basic":
                stats = baseStats;
                break;
            case "attack":
                stats = new GlobalStats(
                        atkStats.getSTR() - stats.getSTR(),
                        atkStats.getDEX() - stats.getDEX(),
                        atkStats.getWIS() - stats.getWIS(),
                        atkStats.getINT() - stats.getINT());
                break;
            case "defense":
                stats = defStats;
                break;
        }

        switch (stat) {
            case 's':
                return stats.getSTR();
            case 'd':
                return stats.getDEX();
            case 'w':
                return stats.getWIS();
            case 'i':
                return stats.getINT();
        }

        return 0;
    }

    public int getMP() {
        return mp;
    }

    public void healHP(int newhp) {
        hp += newhp;
        if (hp > totalhp) {
            hp = totalhp;
        }
    }

    public void healMP(int newmp) {
        mp += newmp;
        if (mp > totalmp) {
            mp = totalmp;
        }

    }

    public void healHP(Player player, int newhp) {
        player.healHP(newhp + healBoost);
    }

    public int getFreeze() {
        return freeze;
    }

    public void freeze(int frozen) {
        if (!getClassName().equals("Inquisitor")) {
            freeze += frozen;
        }

    }

    public void damage(Player defender, int power) {
        for (Spell spell : defender.spells) {
            if (spell != null) {
                if (spell.getName().equals("Heart")) {
                    power -= spell.getLevel() * 6;
                }
            }
        }
        power = Math.max(power, 0);
        defender.hp -= power;
        if (defender.hp > 8000) {
            power = 0;
        }
        if (defender.hp > 0) {
            System.out.println(defender.name + " has lost " + power + "HP.");
        } else {
            defender.hp = 0;
            System.out.println(defender.name + " has lost " + power + "HP and died.");
            defender.dead = true;
        }
        for (Spell spell : spells) {
            if (spell != null) {
                if (spell.getName().equals("Plunder")) {
                    if (defender.dead) {
                        getMoney(10 * spell.getLevel() * level);
                    }
                }
            }
        }
    }

    public void useMP(int cost) {
        mp -= cost;
    }

    public void revive() {
        hp = totalhp;
        mp = totalmp;
        freeze = 0;
        dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public void effect(String stat, int boost) {
        switch (stat) {
            case "hp":
                totalhp += boost;
                hp += boost;
                break;
            case "mp":
                totalmp += boost;
                mp += boost;
                break;
            case "str":
                baseStats.boostSTR(boost);
                break;
            case "dex":
                baseStats.boostDEX(boost);
                break;
            case "wis":
                baseStats.boostWIS(boost);
                break;
            case "int":
                baseStats.boostINT(boost);
                break;
            case "bruATK":
                basicStats.boostSTR(boost);
                atkStats.boostSTR(boost);
                break;
            case "quiATK":
                basicStats.boostDEX(boost);
                atkStats.boostDEX(boost);
                break;
            case "sacATK":
                basicStats.boostWIS(boost);
                atkStats.boostWIS(boost);
                break;
            case "magATK":
                basicStats.boostINT(boost);
                atkStats.boostINT(boost);
                break;
            case "bruDEF":
                defStats.boostSTR(boost);
                break;
            case "quiDEF":
                defStats.boostDEX(boost);
                break;
            case "sacDEF":
                defStats.boostWIS(boost);
                break;
            case "magDEF":
                defStats.boostINT(boost);
            case "heal":
                healBoost += boost;
        }

    }

    public void winTrophy() {
        trophies++;
        if (trophies % 3 == 0) {
            levelUp();
        }
    }

    private void levelUp() {
        boolean selected = false;
        level++;
        upgrades++;
        baseStats.boostAll(boostStats);
        hp = totalhp += 5;
        mp = totalmp += 5;

        switch (level) {
            case 3:
                minRank = rank;
                rank = 65;
                break;
            case 5:
                minRank = rank;
                rank = 97;
                break;
            case 7:
                minRank = rank;
                rank = 126;
                break;
        }

        System.out.println("You just leveled up!\n");
        while (!selected) {
            System.out.println(
                    "Which stat would you like to boost?\n1- Strength\n2- Dexterity\n3- Wisdom\n4- Intelligence");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    baseStats.boostSTR(1);
                    selected = true;
                    break;
                case "2":
                    baseStats.boostDEX(1);
                    selected = true;
                    break;
                case "3":
                    baseStats.boostWIS(1);
                    selected = true;
                    break;
                case "4":
                    baseStats.boostINT(1);
                    selected = true;
                    break;
                default:
                    break;
            }
        }
        selected = false;
        while (!selected) {
            for (Spell spell : spells) {
                if (spell == null) {
                    break;
                } else if (spell.getLevel() != 5) {
                    break;
                }
            }
            if (spells[0] != null) {
                for (; upgrades > 0; upgrades--) {
                    System.out.println("Which spell would you like to upgrade?");
                    switch (GlobalScanner.nextLine()) {
                        case "1":
                            selected = spells[0].levelUp(false, this);
                            break;
                        case "2":
                            selected = spells[1].levelUp(false, this);
                            break;
                        case "3":
                            selected = spells[2].levelUp(false, this);
                            break;
                        case "4":
                            selected = spells[3].levelUp(false, this);
                            break;
                        case "5":
                            selected = spells[4].levelUp(false, this);
                            break;
                        case "6":
                            selected = spells[5].levelUp(false, this);
                            break;
                        default:
                            break;
                    }
                }
            } else {
                System.out.println("Next time you level up, you can upgrade a spell.");
            }
        }
    }

    public void giveMoney(int gold, Player player) {
        if (gold > money) {
            System.out.println("You don't have enough gold.");
        } else {
            System.out.println("You gave " + player.getName() + gold + " gold.");
            money -= gold;
            player.money += gold;
        }
    }

    public void getMoney(int gold) {
        int newGold = 0;
        newGold += isItem(feet, 21) ? (gold + 0.5) / 20 : gold;
        money += getClassName().equals("Thief") ? (gold + 0.5) / 4 + newGold : newGold;
    }

    public void buySpell(Spell spell) {
        String priceText = spell.getPrice();
        int price = Integer.parseInt(priceText.substring(3, priceText.length() - 6));
        if (money >= price) {
            if (spell.getLevel() == 0) {
                if (spells[5] != null) {
                    System.out.println("You already have six spells.");
                    return;
                }
                for (int i = 0; i < 6; i++) {
                    if (spells[i] == null) {
                        spells[i] = spell;
                        break;
                    }
                }
                System.out.println("You bought " + spell.getName() + " for " + price + " gold.");
            } else {
                System.out.println("You upgraded " + spell.getName() + " for " + price + " gold.");
            }
            money -= price;
            spell.levelUp(true, this);
        } else {
            System.out.println("Too expensive.");
        }
    }

    public void buyItem(Item item) {
        String priceText = item.getPrice();
        int price = Integer.parseInt(priceText.substring(3, priceText.length() - 6));
        if (money >= price) {
            money -= price;
            getItem(item);
            System.out.println("You bought a" + Articles.plural(item) + item.getName() + " for " + price + " gold.");
        } else {
            System.out.println("Too expensive.");
        }
    }

    public void sellItem(Item item) {
        String priceText = item.getPrice();
        int price = (int) (Integer.parseInt(priceText.substring(3, priceText.length() - 6)) + 0.5) / 2;
        money += price;
        backpack.remove(item);
        System.out.println("You sold a" + Articles.plural(item) + " for " + price + " gold.");
    }

    public Item randomItem() {
        int id = (int) (Math.random() * (double) (rank - minRank)) + minRank;
        return items.get(id);
    }

    public int getItem() {
        Item item = randomItem();
        getItem(item);
        return item.getId();
    }

    public void getItem(Item item) {
        backpack.add(item);
    }

    public void giveItem(int id, Player player) {
        id -= 1;
        player.getItem(backpack.get(id));
        backpack.remove(id);
    }

    public boolean putOn(int id, int place) {
        Item item = backpack.get(id);
        List<String> itemType = item.getType();
        String type = itemType.get(0);
        String doubleMessage = "You cannot hold two " + type.substring(0, 1).toLowerCase() + type.substring(1) + "s.";
        boolean doubleAssassin = !getClassName().equals("Assassin") || !itemType.contains("Weapon");
        place = itemType.contains("Double-Handed") ? 5 : place;
        switch (place) {
            case 0:
                if (!itemType.contains("Head")) {
                    System.out.println("You must choose an item for your head.");
                    return false;
                }

                if (head != null) {
                    takeOff(0);
                }

                head = item;
                break;
            case 1:
                if (!itemType.contains("Body")) {
                    System.out.println("You must choose an item for your body.");
                    return false;
                }

                if (body != null) {
                    takeOff(1);
                }

                body = item;
                break;
            case 2:
                if (!itemType.contains("Feet")) {
                    System.out.println("You must choose an item for your feet.");
                    return false;
                }

                if (feet != null) {
                    takeOff(2);
                }

                feet = item;
                break;
            case 3:
                if (!itemType.contains("Weapon") && !itemType.contains("Amulet")) {
                    System.out.println("You must choose a weapon or an amulet.");
                    return false;
                }

                if (right != null && right.getType().contains(type) && doubleAssassin) {
                    System.out.println(doubleMessage);
                    return false;
                }

                if (left != null) {
                    takeOff(3);
                }

                left = item;
                break;
            case 4:
                if (!itemType.contains("Shield") && !itemType.contains("Amulet") && doubleAssassin) {
                    System.out.println("You must choose a shield or an amulet.");
                    return false;
                }

                if (left != null && left.getType().contains(type) && doubleAssassin) {
                    System.out.println(doubleMessage);
                    return false;
                }

                if (right != null) {
                    takeOff(4);
                }

                right = item;
                break;
            case 5:
                if (left != null) {
                    takeOff(3);
                }

                if (right != null) {
                    takeOff(4);
                }

                left = item;
                right = item;
                if (getClassName().equals("Monk")) {
                    atkStats.boostAll(-1);
                }
        }

        backpack.remove(id);
        item.putOn(this);
        if (getClassName().equals("Monk")) {
            atkStats.boostAll(-1);
        }
        return true;
    }

    public void takeOff(int place) {
        Item item;
        switch (place) {
            case 0:
                item = head;
                item.takeOff(this);
                head = null;
                break;
            case 1:
                item = body;
                item.takeOff(this);
                body = null;
                break;
            case 2:
                item = feet;
                item.takeOff(this);
                feet = null;
                break;
            case 3:
                item = left;
                if (item.getType().contains("Double-Handed")) {
                    right = null;
                }

                item.takeOff(this);
                left = null;
                break;
            case 4:
                item = right;
                if (item.getType().contains("Double-Handed")) {
                    left = null;
                }

                item.takeOff(this);
                right = null;
                break;
            default:
                item = null;
        }

        if (getClassName().equals("Monk")) {
            atkStats.boostAll(1);
        }
        backpack.add(item);
    }

    public List<Item> getBackpack() {
        return backpack;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public int seeBackpack() {
        int inventory = backpack.size();
        System.out.println(inventory == 0 ? "You have no items in your inventory." : "");

        for (int i = 0; i < inventory; ++i) {
            Item item = backpack.get(i);
            System.out.println((i + 1) + "- " + item.getName() + " (" + String.join(", ", item.getType()) + ")");
        }
        return backpack.size();
    }

    public void seeSpells() {
        String spellType = "error";
        for (int i = 0; i < 6; i++) {
            Spell spell = spells[i];
            if (spell != null) {
                spellType = spell.getType().get(0);
            }
            System.out.println((i + 1) + "- "
                    + (spell == null ? "Empty spell slot"
                            : spell.getName() + " (" + spellType + ")  "
                                    + (spellType.equals("Active") ? spell.getCost() + " MP" : "")));
        }
    }

    public void seeStats() {
        if (getClassName().equals("Barbarian") && hp < totalhp / 2) {
            atkStats.boostAll(8);
        }

        // Looks scary but all it does is add spaces around the stats on the character
        // menu
        String s = baseStats.getSTR() > 9 || baseStats.getSTR() < 0
                ? baseStats.getSTR() + (baseStats.getSTR() < -9 ? "" : " ")
                : " " + baseStats.getSTR() + " ";
        String d = baseStats.getDEX() > 9 || baseStats.getDEX() < 0
                ? baseStats.getDEX() + (baseStats.getDEX() < -9 ? "" : " ")
                : " " + baseStats.getDEX() + " ";
        String w = baseStats.getWIS() > 9 || baseStats.getWIS() < 0
                ? baseStats.getWIS() + (baseStats.getWIS() < -9 ? "" : " ")
                : " " + baseStats.getWIS() + " ";
        String i = baseStats.getINT() > 9 || baseStats.getINT() < 0
                ? baseStats.getINT() + (baseStats.getINT() < -9 ? "" : " ")
                : " " + baseStats.getINT();
        String ba = atkStats.getSTR() > 9 || atkStats.getSTR() < 0
                ? atkStats.getSTR() + (atkStats.getSTR() < -9 ? "" : " ")
                : " " + atkStats.getSTR() + " ";
        String qa = atkStats.getDEX() > 9 || atkStats.getDEX() < 0
                ? atkStats.getDEX() + (atkStats.getDEX() < -9 ? "" : " ")
                : " " + atkStats.getDEX() + " ";
        String sa = atkStats.getWIS() > 9 || atkStats.getWIS() < 0
                ? atkStats.getWIS() + (atkStats.getWIS() < -9 ? "" : " ")
                : " " + atkStats.getWIS() + " ";
        String ma = atkStats.getINT() > 9 || atkStats.getINT() < 0
                ? atkStats.getINT() + (atkStats.getINT() < -9 ? "" : " ")
                : " " + atkStats.getINT();
        String bd = defStats.getSTR() > 9 || defStats.getSTR() < 0
                ? defStats.getSTR() + (defStats.getSTR() < -9 ? "" : " ")
                : " " + defStats.getSTR() + " ";
        String qd = defStats.getDEX() > 9 || defStats.getDEX() < 0
                ? defStats.getDEX() + (defStats.getDEX() < -9 ? "" : " ")
                : " " + defStats.getDEX() + " ";
        String sd = defStats.getWIS() > 9 || defStats.getWIS() < 0
                ? defStats.getWIS() + (defStats.getWIS() < -9 ? "" : " ")
                : " " + defStats.getWIS() + " ";
        String md = defStats.getINT() > 9 || defStats.getINT() < 0
                ? defStats.getINT() + (defStats.getINT() < -9 ? "" : " ")
                : " " + defStats.getINT();

        System.out.printf(
                "\n%s (%s) - %d/%dHP  %d/%dMP\nLVL: %d   Trophies: %d\n STR | DEX | WIS | INT  Base Stats\n %s | %s | %s | %s\n\n BRU | QUI | SAC | MAG  Attack Stats\n %s | %s | %s | %s\n\n BRU | QUI | SAC | MAG  Defense Stats\n %s | %s | %s | %s\n\nGold: %d\n",
                name, getClassName(), hp, totalhp, mp, totalmp, level, trophies, s, d, w, i, ba, qa, sa, ma, bd, qd, sd,
                md, money);
        if (getClassName().equals("Barbarian") && hp < totalhp / 2) {
            atkStats.boostAll(-8);
        }

        System.out.println("Head:  " + (head == null ? "Nothing" : head.getName()) + " (h)");
        System.out.println("Body:  " + (body == null ? "Nothing" : body.getName()) + " (b)");
        System.out.println("Feet:  " + (feet == null ? "Nothing" : feet.getName()) + " (f)");
        System.out.println("Main Hand:  " + (left == null ? "Nothing" : left.getName()) + " (m)");
        System.out.println("Off Hand:  " + (right == null ? "Nothing" : right.getName()) + " (o)\n");
    }

    public void seeItem(int place) {
        switch (place) {
            case 1:
                if (head != null) {
                    head.seeItem();
                } else {
                    System.out.println("You have nothing equipped on your head.");
                }
                break;
            case 2:
                if (body != null) {
                    body.seeItem();
                } else {
                    System.out.println("You have nothing equipped on your body.");
                }
                break;
            case 3:
                if (feet != null) {
                    feet.seeItem();
                } else {
                    System.out.println("You have nothing equipped on your feet.");
                }
                break;
            case 4:
                if (left != null) {
                    left.seeItem();
                } else {
                    System.out.println("You have nothing equipped on your main hand.");
                }
                break;
            case 5:
                if (right != null) {
                    right.seeItem();
                } else {
                    System.out.println("You have nothing equipped on your off hand.");
                }
                break;
            default:
                (backpack.get(place - 6)).seeItem();
        }
    }

    public void seeSpell(int id, boolean shop) {
        Spell spell = spells[id];
        if (spell != null) {
            spell.seeSpell(shop, this);
        }
    }

    private String atkType(int basic) {
        if (left != null) {
            List<String> itemType = left.getType();
            String type = "error";
            if (itemType.contains("Weapon")) {
                switch (basic) {
                    case 1:
                        type = "Brute";
                        break;
                    case 2:
                        type = "Quick";
                        break;
                    case 3:
                        type = "Sacred";
                        break;
                    case 4:
                        type = "Magic";
                        break;
                }
                if (itemType.contains(type)) {
                    if (itemType.contains("Fire")) {
                        return "fire";
                    }

                    if (itemType.contains("Snow")) {
                        return "snow";
                    }
                }
            }
        }
        return "basic";
    }

    public void clover() {
        if (!dead) {
            if (isItem(left, 110) || isItem(right, 110)) {
                money += 5;
            }
        }
    }

    public void alchemist(List<Player> fighters, boolean start) {
        int boost = start ? -2 : 2;
        if (isItem(left, 109) || isItem(right, 109)) {
            for (Player enemy : fighters) {
                if (enemy != this) {
                    atkStats.boostAll(2 * boost);
                    defStats.boostAll(boost);
                }
            }
        }
    }

    public int attack(int nature, Player defender, int current) {
        return attack(nature, defender, (int) (Math.random() * 20) + 1, current, atkType(nature));
    }

    public int attack(int nature, Player defender, int power, int current, String type) {
        boolean basic = nature != 0;
        defender.preDefend(this, type);
        boolean counter = preAttack(basic ? power : 0, type, basic);

        switch (nature) {
            case 0:
                damage(defender, power);
                break;

            case 1:
                damage(defender, baseStats.getSTR() + power + atkStats.getSTR() - defender.defStats.getSTR());
                break;

            case 2:
                if (isItem(left, 148) || isItem(right, 148)) {
                    healHP((int) (Math.random() * 6) + 1);
                }
                damage(defender, baseStats.getDEX() + power + atkStats.getDEX() - defender.defStats.getDEX());
                if (isItem(feet, 111) && !defender.dead) {
                    damage(defender, (int) (Math.random() * 8) + 1);
                }
                break;

            case 3:
                damage(defender, baseStats.getWIS() + power + atkStats.getWIS() - defender.defStats.getWIS());
                break;

            case 4:
                damage(defender, baseStats.getINT() + power + atkStats.getINT() - defender.defStats.getINT());
                if ((isItem(left, 147) || isItem(right, 147)) && !defender.isItem(body, 71) && !defender.dead) {
                    damage(defender, 6);
                }
                break;
        }

        defender.postDefend(this, nature, type, counter);
        return postAttack(defender, type, basic, current);
    }

    public boolean preAttack(int d20, String type, boolean basic) {
        switch (type) {
            case "snow":
                if (isItem(feet, 29)) {
                    atkStats.boostAll(2);
                }
                if (isItem(left, 63) || isItem(right, 63)) {
                    atkStats.boostAll(6);
                }
                break;
            case "fire":
                if (isItem(head, 105)) {
                    atkStats.boostAll(2);
                }
                if (isItem(left, 46) || isItem(right, 46)) {
                    atkStats.boostAll(5);
                }
                break;
        }
        for (Spell spell : spells) {
            if (isSpell(spell, "Aura")) {
                healHP(spell.getLevel() * 6);
            } else if (isSpell(spell, "Sorcerer's Blessing")) {
                healMP(spell.getLevel() * 6);
            }
        }
        atkStats.boostAll(-freeze);
        if (isItem(left, 143)) {
            return false;
        }
        return true;
    }

    public int postAttack(Player defender, String type, boolean basic, int current) {
        switch (type) {
            case "snow":
                if (isItem(feet, 29)) {
                    atkStats.boostAll(-2);
                }
                if (isItem(left, 63) || isItem(right, 63)) {
                    atkStats.boostAll(-6);
                }
                break;
            case "fire":
                if (isItem(head, 105)) {
                    atkStats.boostAll(-2);
                }
                if (isItem(left, 46) || isItem(right, 46)) {
                    atkStats.boostAll(-5);
                }
                break;
        }
        for (Spell spell : spells) {
            if (isSpell(spell, "Freezing Attacks")) {
                defender.freeze(spell.getLevel() * 6);
            }
        }
        if (!defender.isItem(body, 71) && !defender.dead) {
            if (isItem(left, 4) || isItem(right, 4)) {
                damage(defender, 1);
            }
            if (isItem(right, 66)) {
                damage(defender, 8);
            }
            if (isItem(body, 68) && right.getType().contains("Bow")) {
                damage(defender, 8);
            }
        }
        atkStats.boostAll(freeze);
        freeze = 0;
        return current;
    }

    public void preDefend(Player attacker, String type) {
        switch (type) {
            case "snow":
                freeze(2);
                if (isItem(body, 97)) {
                    defStats.boostAll(4);
                }
                if (isItem(right, 99)) {
                    defStats.boostAll(10);
                }
                break;
            case "fire":
                if (isItem(body, 96)) {
                    defStats.boostAll(4);
                }
                if (isItem(right, 99)) {
                    defStats.boostAll(10);
                }
                break;
        }
    }

    public void postDefend(Player attacker, int basic, String type, boolean counter) {
        switch (type) {
            case "snow":
                if (isItem(body, 97)) {
                    defStats.boostAll(-4);
                }
                if (isItem(right, 99)) {
                    defStats.boostAll(10);
                }
                break;
            case "fire":
                if (isItem(body, 96)) {
                    defStats.boostAll(-4);
                }
                if (isItem(right, 99)) {
                    defStats.boostAll(10);
                }
                break;
        }
        for (Spell spell : spells) {
            if (isSpell(spell, "Thorns") && (basic == 1 || basic == 2)) {
                damage(attacker, spell.getLevel() * 6);
            } else if (isSpell(spell, "Reflective Skin") && (basic == 3 || basic == 4)) {
                damage(attacker, spell.getLevel() * 6);
            }
            if (attacker.dead) {
                return;
            }
        }
        if ((isItem(left, 24) || isItem(right, 24)) && basic == 2 && counter) {
            damage(attacker, 2);
        }
        if (isItem(right, 104) && counter && !attacker.isItem(body, 71) && !attacker.dead) {
            damage(attacker, 6);
        }
    }

    public Player(String playerName, int playerHP, int playerMP, GlobalStats playerStats) {
        items = GlobalItems.addItems();
        name = playerName;
        hp = totalhp = playerHP;
        mp = totalmp = playerMP;
        boostStats.boostAll(playerStats);
        baseStats.setSTR(boostStats.getSTR() == 0 ? (int) (Math.random() * 4.0) + 1 : boostStats.getSTR() * 5);
        baseStats.setDEX(boostStats.getDEX() == 0 ? (int) (Math.random() * 4.0) + 1 : boostStats.getDEX() * 5);
        baseStats.setWIS(boostStats.getWIS() == 0 ? (int) (Math.random() * 4.0) + 1 : boostStats.getWIS() * 5);
        baseStats.setINT(boostStats.getINT() == 0 ? (int) (Math.random() * 4.0) + 1 : boostStats.getINT() * 5);
        if (getClassName().equals("Paladin")) {
            defStats.setWIS(8);
            defStats.setINT(8);
        } else if (getClassName().equals("Monk")) {
            atkStats.setSTR(5);
            atkStats.setDEX(5);
            atkStats.setWIS(5);
            atkStats.setINT(5);

        }

    }

    public Player(JSONObject player) {
        List<Spell> allSpells = GlobalSpells.addSpells();
        allSpells.addAll(GlobalSkills.addSkills());
        items = GlobalItems.addItems();

        name = (String) player.get("name");
        hp = totalhp = (int) player.get("hp");
        mp = totalmp = (int) player.get("mp");
        baseStats.setSTR((int) player.get("str"));
        baseStats.setDEX((int) player.get("dex"));
        baseStats.setWIS((int) player.get("wis"));
        baseStats.setINT((int) player.get("int"));
        boostStats.setSTR((int) player.get("boostS"));
        boostStats.setDEX((int) player.get("boostD"));
        boostStats.setWIS((int) player.get("boostW"));
        boostStats.setINT((int) player.get("boostI"));
        basicStats.setSTR((int) player.get("basicB"));
        basicStats.setDEX((int) player.get("basicQ"));
        basicStats.setWIS((int) player.get("basicS"));
        basicStats.setINT((int) player.get("basicM"));
        atkStats.setSTR((int) player.get("bruATK"));
        atkStats.setDEX((int) player.get("quiATK"));
        atkStats.setWIS((int) player.get("sacATK"));
        atkStats.setINT((int) player.get("magATK"));
        defStats.setSTR((int) player.get("bruDEF"));
        defStats.setDEX((int) player.get("quiDEF"));
        defStats.setWIS((int) player.get("sacDEF"));
        defStats.setINT((int) player.get("magDEF"));
        healBoost = (int) player.get("heal");
        level = (int) player.get("level");
        trophies = (int) player.get("trophies");
        upgrades = (int) player.get("upgrades");
        money = (int) player.get("money");

        switch (level) {
            case 6:
            case 5:
                minRank = 65;
                rank = 97;
                break;
            case 4:
            case 3:
                minRank = 33;
                rank = 65;
            case 2:
            case 1:
                break;
            default:
                minRank = 97;
                rank = 126;
                break;
        }

        JSONArray bp = (JSONArray) player.get("backpack");
        for (Object itemString : bp) {
            int itemId = (int) itemString;
            for (Item item : items) {
                if (item.isItem(itemId)) {
                    backpack.add(item);
                    break;
                }
            }
        }

        JSONArray sb = (JSONArray) player.get("spells");
        int i = 0;
        for (Object spellString : sb) {
            JSONObject spell = new JSONObject(spellString.toString());
            if ((boolean) spell.get("active")) {
                switch ((String) spell.get("name")) {
                    case "Armageddon":
                        spells[i] = new Armageddon(spell);
                        break;
                    case "Fireball":
                        spells[i] = new Fireball(spell);
                        break;
                    case "Freeze":
                        spells[i] = new Freeze(spell);
                        break;
                    case "Healing":
                        spells[i] = new Healing(spell);
                        break;
                    case "Icicles":
                        spells[i] = new Icicles(spell);
                        break;
                    case "Supernova":
                        spells[i] = new Supernova(spell);
                        break;
                    case "Tackle":
                        spells[i] = new Tackle(spell);
                        break;
                    case "Wrath":
                        spells[i] = new Wrath(spell);
                        break;
                }
            } else {
                for (Spell skill : allSpells) {
                    if (isSpell(skill, (String) spell.get("name"))) {
                        List<String> spellType = skill.getType();
                        int x = spellType.size();
                        String[] types = new String[x];
                        for (int j = 0; j < x; j++) {
                            types[j] = spellType.get(j);
                        }
                        skill.levelUp(false, new Barbarian("Buffer"));
                        spells[i] = new Spell(spell, skill.getDesc(), types,
                                skill.getCost());
                        break;
                    }
                }
            }
            i++;
        }
    }

    public JSONObject save() {
        JSONObject save = new JSONObject();
        JSONArray bp = new JSONArray();
        JSONArray spellbook = new JSONArray();

        save.put("name", name);
        save.put("class", getClassName());
        save.put("hp", totalhp);
        save.put("mp", totalmp);
        save.put("str", baseStats.getSTR());
        save.put("dex", baseStats.getDEX());
        save.put("wis", baseStats.getWIS());
        save.put("int", baseStats.getINT());
        save.put("boostS", boostStats.getSTR());
        save.put("boostD", boostStats.getDEX());
        save.put("boostW", boostStats.getWIS());
        save.put("boostI", boostStats.getINT());
        save.put("basicB", basicStats.getSTR());
        save.put("basicQ", basicStats.getDEX());
        save.put("basicS", basicStats.getWIS());
        save.put("basicM", basicStats.getINT());
        save.put("bruATK", atkStats.getSTR());
        save.put("quiATK", atkStats.getDEX());
        save.put("sacATK", atkStats.getWIS());
        save.put("magATK", atkStats.getINT());
        save.put("bruDEF", defStats.getSTR());
        save.put("quiDEF", defStats.getDEX());
        save.put("sacDEF", defStats.getWIS());
        save.put("magDEF", defStats.getINT());
        save.put("heal", healBoost);
        save.put("level", level);
        save.put("trophies", trophies);
        save.put("upgrades", upgrades);
        save.put("money", money);
        save.put("head", head == null ? null : head.getId());
        save.put("body", body == null ? null : body.getId());
        save.put("feet", feet == null ? null : feet.getId());
        save.put("left", left == null ? null : left.getId());
        save.put("right", right == null ? null : right.getId());
        for (Spell spell : spells) {
            if (spell == null) {
                break;
            }
            spellbook.put(spell.save());
        }
        save.put("spells", spellbook);
        for (Item item : backpack) {
            bp.put(item.getId());
        }
        save.put("backpack", bp);
        return save;
    }

    private boolean isItem(Item item, int id) {
        if (item == null) {
            return false;
        }
        return item.isItem(id);
    }

    public boolean isSpell(Spell spell, String id) {
        if (spell == null) {
            return false;
        }
        return spell.getName().equals(id);
    }
}