package classes;

import items.Item;
import java.util.ArrayList;
import java.util.Scanner;

import spells.Spell;

public abstract class Player {
    String name;
    int hp = 0;
    int totalhp = 0;
    int mp = 0;
    int totalmp = 0;
    int str = 0;
    int dex = 0;
    int wis = 0;
    int it = 0;
    int boostS = 0;
    int boostD = 0;
    int boostW = 0;
    int boostI = 0;
    int basicB = 0;
    int basicQ = 0;
    int basicS = 0;
    int basicM = 0;
    int bruATK = 0;
    int quiATK = 0;
    int sacATK = 0;
    int magATK = 0;
    int bruDEF = 0;
    int quiDEF = 0;
    int sacDEF = 0;
    int magDEF = 0;
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
    ArrayList<Item> backpack = new ArrayList<Item>();
    final String VOWELS = "AEIOU";

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

    public int getSTR() {
        return str;
    }

    public int getDEX() {
        return dex;
    }

    public int getWIS() {
        return wis;
    }

    public int getINT() {
        return it;
    }

    public int getBruDef() {
        return bruDEF;
    }

    public int getQuiDef() {
        return quiDEF;
    }

    public int getSacDef() {
        return sacDEF;
    }

    public int getMagDef() {
        return magDEF;
    }

    public int getBruAtk() {
        return bruATK - basicB;
    }

    public int getQuiAtk() {
        return quiATK - basicQ;
    }

    public int getSacAtk() {
        return sacATK - basicS;
    }

    public int getMagAtk() {
        return magATK - basicM;
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
        power = power < 0 ? 0 : power;
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
                str += boost;
                break;
            case "dex":
                dex += boost;
                break;
            case "wis":
                wis += boost;
                break;
            case "int":
                it += boost;
                break;
            case "bruATK":
                basicB += boost;
                bruATK += boost;
                break;
            case "quiATK":
                basicQ += boost;
                quiATK += boost;
                break;
            case "sacATK":
                basicS += boost;
                sacATK += boost;
                break;
            case "magATK":
                basicM += boost;
                magATK += boost;
                break;
            case "bruDEF":
                bruDEF += boost;
                break;
            case "quiDEF":
                quiDEF += boost;
                break;
            case "sacDEF":
                sacDEF += boost;
                break;
            case "magDEF":
                magDEF += boost;
            case "heal":
                healBoost += boost;
        }

    }

    public void winTrophy(Scanner scan) {
        trophies++;
        if (trophies % 3 == 0) {
            levelUp(scan);
        }
    }

    private void levelUp(Scanner scan) {
        boolean selected = false;
        level++;
        upgrades++;
        str += boostS;
        dex += boostD;
        wis += boostW;
        it += boostI;
        hp = totalhp += 5;
        mp = totalmp += 5;

        System.out.println("You just leveled up!\n");
        while (!selected) {
            System.out.println(
                    "Which stat would you like to boost?\n1- Strength\n2- Dexterity\n3- Wisdom\n4- Intelligence");
            switch (scan.nextLine()) {
                case "1":
                    str++;
                    selected = true;
                    break;
                case "2":
                    dex++;
                    selected = true;
                    break;
                case "3":
                    wis++;
                    selected = true;
                    break;
                case "4":
                    it++;
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
                } else if (spell == spells[5]) {
                    return;
                }
            }
            if (spells[0] != null) {
                for (; upgrades > 0; upgrades--) {
                    System.out.println("Which spell would you like to upgrade?");
                    switch (scan.nextLine()) {
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
        if (feet != null) {
            newGold += feet.getName().equals("WoodLeg") ? (gold + 0.5) / 20 : gold;
        }
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
            System.out.println("You bought a" + (VOWELS.indexOf(item.getName().charAt(0)) != -1 ? "n " : " ")
                    + item.getName() + " for " + price + " gold.");
        } else {
            System.out.println("Too expensive.");
        }
    }

    public void sellItem(Item item) {
        String priceText = item.getPrice();
        int price = (int) ((Integer.parseInt(priceText.substring(3, priceText.length() - 6)) + 0.5) / 2);
        money += price;
        backpack.remove(item);
        System.out.println("You sold a" + (VOWELS.indexOf(item.getName().charAt(0)) != -1 ? "n " : " ") + item.getName()
                + " for " + price + " gold.");
    }

    public Item randomItem(ArrayList<Item> items, double id) {
        return (Item) items.get((int) (id * (double) (rank - minRank)) + minRank);
    }

    public void getItem(ArrayList<Item> items, double id) {
        Item item = randomItem(items, id);
        getItem(item);
        System.out.println(
                "You got a" + (VOWELS.indexOf(item.getName().charAt(0)) != -1 ? "n " : " ") + item.getName() + "!");
    }

    private void getItem(Item item) {
        backpack.add(item);
    }

    public void giveItem(int id, Player player) {
        id -= 1;
        player.getItem((Item) backpack.get(id));
        backpack.remove(id);
    }

    public boolean putOn(int id, int place) {
        Item item = (Item) backpack.get(id);
        ArrayList<String> itemType = item.getType();
        String type = (String) itemType.get(0);
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
                    bruATK--;
                    quiATK--;
                    sacATK--;
                    magATK--;
                }
        }

        backpack.remove(id);
        item.putOn(this);
        if (getClassName().equals("Monk")) {
            bruATK--;
            quiATK--;
            sacATK--;
            magATK--;
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
            bruATK++;
            quiATK++;
            sacATK++;
            magATK++;
        }
        backpack.add(item);
    }

    public ArrayList<Item> getBackpack() {
        return backpack;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public int seeBackpack() {
        int inventory = backpack.size();
        System.out.println(inventory == 0 ? "You have no items in your inventory." : "");

        for (int i = 0; i < inventory; ++i) {
            Item item = (Item) backpack.get(i);
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
            bruATK += 8;
            quiATK += 8;
            sacATK += 8;
            magATK += 8;
        }

        String s = str > 9 ? str + " " : " " + str + " ";
        String d = dex > 9 ? dex + " " : " " + dex + " ";
        String w = wis > 9 ? wis + " " : " " + wis + " ";
        String i = it > 9 ? it + " " : " " + it;
        String ba = bruATK > 9 ? bruATK + " " : " " + bruATK + " ";
        String qa = quiATK > 9 ? quiATK + " " : " " + quiATK + " ";
        String sa = sacATK > 9 ? sacATK + " " : " " + sacATK + " ";
        String ma = magATK > 9 ? magATK + " " : " " + magATK;
        String bd = bruDEF > 9 ? bruDEF + " " : " " + bruDEF + " ";
        String qd = quiDEF > 9 ? quiDEF + " " : " " + quiDEF + " ";
        String sd = sacDEF > 9 ? sacDEF + " " : " " + sacDEF + " ";
        String md = magDEF > 9 ? magDEF + " " : " " + magDEF;
        System.out.printf(
                "\n%s (%s) - %d/%dHP  %d/%dMP\nLVL: %d   Trophies: %d\n STR | DEX | WIS | INT  Base Stats\n %s | %s | %s | %s\n\n BRU | QUI | SAC | MAG  Attack Stats\n %s | %s | %s | %s\n\n BRU | QUI | SAC | MAG  Defense Stats\n %s | %s | %s | %s\n\nGold: %d\n",
                name, getClassName(), hp, totalhp, mp, totalmp, level, trophies, s, d, w, i, ba, qa, sa, ma, bd, qd, sd,
                md, money);
        if (getClassName().equals("Barbarian") && hp < totalhp / 2) {
            bruATK -= 8;
            quiATK -= 8;
            sacATK -= 8;
            magATK -= 8;
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
                ((Item) backpack.get(place - 6)).seeItem();
        }
    }

    public void seeSpell(int id, boolean shop) {
        Spell spell = spells[id];
        if (spell != null) {
            spell.seeSpell(shop, this);
        }
    }

    public String atkType(int basic) {
        if (left == null) {
            return "basic";
        } else {
            ArrayList<String> itemType = left.getType();
            String type = "error";
            if (itemType.contains("Weapon")) {
                switch (basic) {
                    case 0:
                        type = "Brute";
                    case 1:
                        type = "Quick";
                    case 2:
                        type = "Sacred";
                    case 3:
                        type = "Magic";
                    default:
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
    }

    public void bruteAttack(Player defender, int power) {
        damage(defender, str + power + bruATK - defender.bruDEF);
    }

    public void quickAttack(Player defender, int power) {
        damage(defender, dex + power + quiATK - defender.quiDEF);
    }

    public void sacredAttack(Player defender, int power) {
        damage(defender, wis + power + sacATK - defender.sacDEF);
    }

    public void magicAttack(Player defender, int power) {
        damage(defender, it + power + magATK - defender.magDEF);
    }

    public boolean preAttack(int d20, String type, boolean basic) {
        switch (type) {
            case "snow":
                if (feet != null) {
                    if (feet.getName().equals("Snow Rackets")) {
                        bruATK += 2;
                        quiATK += 2;
                        sacATK += 2;
                        magATK += 2;
                    }
                }
            case "fire":
            default:
        }
        for (Spell spell : spells) {
            if (spell != null) {
                if (spell.getName().equals("Aura")) {
                    healHP(spell.getLevel() * 6);
                } else if (spell.getName().equals("Blessing")) {
                    healMP(spell.getLevel() * 6);
                }
            }
        }
        if (left != null) {
            if (left.getName().equals("Crossbow")) {
                return false;
            }
        }
        bruATK -= freeze;
        quiATK -= freeze;
        sacATK -= freeze;
        magATK -= freeze;
        return true;
    }

    public int postAttack(Player defender, String type, boolean basic, int current) {
        switch (type) {
            case "snow":
                if (feet != null) {
                    if (feet.getName().equals("Snow Rackets")) {
                        bruATK -= 2;
                        quiATK -= 2;
                        sacATK -= 2;
                        magATK -= 2;
                    }
                }
                break;
            case "fire":
                break;
        }
        for (Spell spell : spells) {
            if (spell != null) {
                if (spell.getName().equals("Freezing Attacks")) {
                    defender.freeze(spell.getLevel() * 6);
                }
            }
        }
        if (left != null) {
            if (left.getName().equals("Viper Fang")) {
                damage(defender, 1);
            }
        }
        if (right != null) {
            if (right.getName().equals("Viper Fang")) {
                damage(defender, 1);
            }
        }
        bruATK += freeze;
        quiATK += freeze;
        sacATK += freeze;
        magATK += freeze;
        freeze = 0;
        return current;
    }

    public void preDefend(Player attacker, String type) {
        switch (type) {
            case "snow":
                freeze(4);
                break;
            case "fire":
            default:
        }
    }

    public void postDefend(Player attacker, int basic, String type, boolean counter) {
        switch (type) {
            case "snow":
            case "fire":
            default:
        }
        for (Spell spell : spells) {
            if (spell != null) {
                if (spell.getName().equals("Thorns") && (basic == 0 || basic == 1)) {
                    damage(attacker, spell.getLevel() * 6);
                } else if (spell.getName().equals("Reflective Skin") && (basic == 2 || basic == 3)) {
                    damage(attacker, spell.getLevel() * 6);
                }
            }
            if (attacker.dead) {
                return;
            }
        }
        if (left != null && basic == 1 && counter) {
            if (left.getName().equals("Bear Trap")) {
                damage(attacker, 2);
            }
        }
        if (right != null && basic == 1 && counter) {
            if (right.getName().equals("Bear Trap")) {
                damage(attacker, 2);
            }
        }
    }

    public Player(String playerName, int playerHP, int playerMP, int playerSTR, int playerDEX, int playerWIS,
            int playerINT) {
        name = playerName;
        hp = totalhp = playerHP;
        mp = totalmp = playerMP;
        boostS = playerSTR;
        boostD = playerDEX;
        boostW = playerWIS;
        boostI = playerINT;
        str = boostS == 0 ? (int) (Math.random() * 4.0) + 1 : boostS * 5;
        dex = boostD == 0 ? (int) (Math.random() * 4.0) + 1 : boostD * 5;
        wis = boostW == 0 ? (int) (Math.random() * 4.0) + 1 : boostW * 5;
        it = boostI == 0 ? (int) (Math.random() * 4.0) + 1 : boostI * 5;
        if (getClassName().equals("Paladin")) {
            sacDEF = magDEF = 8;
        } else if (getClassName().equals("Monk")) {
            bruATK = quiATK = sacATK = magATK = 5;
        }

    }
}