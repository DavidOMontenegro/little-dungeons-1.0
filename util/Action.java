package util;

import classes.Player;
import spells.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Action {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Spell> spells = new ArrayList<>();
    ArrayList<Spell> skills = new ArrayList<>();

    int playerNumber;
    Scanner scanner;

    public static int next(int current, int active) {
        return current == active - 1 ? 0 : ++current;
    }

    public static void wizard(Player player) {
        if (player.getClassName().equals("Wizard")) {
            player.healMP(25);
        }
    }

    private boolean getReady(ArrayList<Player> fighters, boolean isDuel) {
        boolean quit = false;
        System.out.println("Final Preparations");
        for (Player player : fighters) {
            for (Spell spell : player.getSpells()) {
                if (spell != null) {
                    if (spell.getName().equals("Intimidating Presence")) {
                        for (Player enemy : fighters) {
                            if (enemy != player) {
                                enemy.effect("bruATK", -5);
                                enemy.effect("quiATK", -5);
                                enemy.effect("sacATK", -5);
                                enemy.effect("magATK", -5);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < fighters.size(); i++) {
            if (menu(fighters.get(i), i != 0 && isDuel)) {
                quit = true;
            }
        }
        return quit;
    }

    private void winner(ArrayList<Player> fighters, Player victor, double id) {
        System.out.println(
                "\n--------------------------\n" + victor.getName() + " won the game!\n--------------------------");
        victor.getMoney(50);
        victor.getItem(items, id);
        for (Player player : fighters) {
            player.revive();
            for (Spell spell : player.getSpells()) {
                if (spell != null) {
                    if (spell.getName().equals("Intimidating Presence")) {
                        for (Player enemy : fighters) {
                            if (enemy != player) {
                                enemy.effect("bruATK", 5);
                                enemy.effect("quiATK", 5);
                                enemy.effect("sacATK", 5);
                                enemy.effect("magATK", 5);
                            }
                        }
                    }
                }
            }
        }
    }

    private int endTurn(ArrayList<Player> active, int current) {
        ArrayList<Player> begin = new ArrayList<>(active);
        for (Player player : begin) {
            if (player.isDead()) {
                player.revive();
                active.remove(player);
                if (current > begin.indexOf(player)) {
                    current--;
                }
            }
        }

        return current == active.size() ? 0 : current;
    }

    private int basicAttack(ArrayList<Player> active, int current) {
        boolean selected1 = false;
        boolean selected2 = false;
        int activeNumber = active.size();
        Player user = active.get(current);
        Player defender;
        boolean counter;
        String type;
        int d20;
        while (!selected1) {
            System.out.println("1- Brute Attack\n2- Quick Attack\n3- Sacred Attack\n4- Magic Attack\n5- Exit");
            switch (scanner.nextLine()) {
                case "1":
                    while (!selected2) {
                        System.out.println("Which player will you attack?");
                        for (int i = 0, j = 0; i < activeNumber; i++) {
                            Player player = active.get(i);
                            if (player == user) {
                                continue;
                            }
                            j++;
                            System.out.println(j + "- " + player.getName());
                        }
                        System.out.println((activeNumber) + "- Exit");
                        String id = scanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId <= active.indexOf(user) && playerId > 0) {
                                type = user.atkType(0);
                                defender = active.get(playerId - 1);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.bruteAttack(defender, d20);
                                defender.postDefend(user, 0, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(0);
                                defender = active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.bruteAttack(defender, d20);
                                defender.postDefend(user, 0, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId == activeNumber) {
                                selected2 = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected2 = false;
                    break;
                case "2":
                    while (!selected2) {
                        System.out.println("Which player will you attack?");
                        for (int i = 0, j = 0; i < activeNumber; i++) {
                            Player player = active.get(i);
                            if (player == user) {
                                continue;
                            }
                            j++;
                            System.out.println(j + "- " + player.getName());
                        }
                        System.out.println((activeNumber) + "- Back");
                        String id = scanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId <= active.indexOf(user) && playerId > 0) {
                                type = user.atkType(1);
                                defender = active.get(playerId - 1);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.quickAttack(defender, d20);
                                defender.postDefend(user, 1, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(1);
                                defender = active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.quickAttack(defender, d20);
                                defender.postDefend(user, 1, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId == activeNumber) {
                                selected2 = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected2 = false;
                    break;
                case "3":
                    while (!selected2) {
                        System.out.println("Which player will you attack?");
                        for (int i = 0, j = 0; i < activeNumber; i++) {
                            Player player = active.get(i);
                            if (player == user) {
                                continue;
                            }
                            j++;
                            System.out.println(j + "- " + player.getName());
                        }
                        System.out.println((activeNumber) + "- Back");
                        String id = scanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId <= active.indexOf(user) && playerId > 0) {
                                type = user.atkType(2);
                                defender = active.get(playerId - 1);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.sacredAttack(defender, d20);
                                defender.postDefend(user, 2, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(2);
                                defender = active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.sacredAttack(defender, d20);
                                defender.postDefend(user, 2, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId == activeNumber) {
                                selected2 = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected2 = false;
                    break;
                case "4":
                    while (!selected2) {
                        System.out.println("Which player will you attack?");
                        for (int i = 0, j = 0; i < activeNumber; i++) {
                            Player player = active.get(i);
                            if (player == user) {
                                continue;
                            }
                            j++;
                            System.out.println(j + "- " + player.getName());
                        }
                        System.out.println((activeNumber) + "- Back");
                        String id = scanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId <= active.indexOf(user) && playerId > 0) {
                                type = user.atkType(3);
                                defender = active.get(playerId - 1);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.magicAttack(defender, d20);
                                defender.postDefend(user, 3, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(3);
                                defender = active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.magicAttack(defender, d20);
                                defender.postDefend(user, 3, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = true;
                                selected1 = true;
                            } else if (playerId == activeNumber) {
                                selected2 = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected2 = false;
                    break;
                case "5":
                    selected1 = true;
                default:
                    break;
            }
        }
        return current;
    }

    private boolean basicAttack(Player attacker, Player defender, boolean turn) {
        boolean selected = false;

        String type;
        int d20;
        boolean counter;
        while (!selected) {
            System.out.println("1- Brute Attack\n2- Quick Attack\n3- Sacred Attack\n4- Magic Attack\n5- Exit");
            switch (scanner.nextLine()) {
                case "1":
                    d20 = ((int) (Math.random() * 20) + 1);
                    type = attacker.atkType(0);
                    defender.preDefend(attacker, type);
                    counter = attacker.preAttack(d20, type, true);
                    attacker.bruteAttack(defender, d20);
                    defender.postDefend(attacker, 0, type, counter);
                    if (attacker.postAttack(defender, type, true, 1) == 1) {
                        turn = !turn;
                        wizard(defender);
                    }
                    selected = true;
                    break;
                case "2":
                    d20 = ((int) (Math.random() * 20) + 1);
                    type = attacker.atkType(1);
                    defender.preDefend(attacker, type);
                    counter = attacker.preAttack(d20, type, true);
                    attacker.quickAttack(defender, d20);
                    defender.postDefend(attacker, 1, type, counter);
                    if (attacker.postAttack(defender, type, true, 1) == 1) {
                        turn = !turn;
                        wizard(defender);
                    }
                    selected = true;
                    break;
                case "3":
                    d20 = ((int) (Math.random() * 20) + 1);
                    type = attacker.atkType(2);
                    defender.preDefend(attacker, type);
                    counter = attacker.preAttack(d20, type, true);
                    attacker.sacredAttack(defender, d20);
                    defender.postDefend(attacker, 2, type, counter);
                    if (attacker.postAttack(defender, type, true, 1) == 1) {
                        turn = !turn;
                        wizard(defender);
                    }
                    selected = true;
                    break;
                case "4":
                    d20 = ((int) (Math.random() * 20) + 1);
                    type = attacker.atkType(3);
                    defender.preDefend(attacker, type);
                    counter = attacker.preAttack(d20, type, true);
                    attacker.magicAttack(defender, d20);
                    defender.postDefend(attacker, 3, type, counter);
                    if (attacker.postAttack(defender, type, true, 1) == 1) {
                        turn = !turn;
                        wizard(defender);
                    }
                case "5":
                    selected = true;
                default:
                    break;
            }
        }
        return turn;
    }

    private int useSpell(ArrayList<Player> active, int current) {
        boolean selected = false;
        Player user = active.get(current);

        while (!selected) {
            Spell[] userSpells = user.getSpells();
            user.seeSpells();
            System.out.println("7- Back");
            switch (scanner.nextLine()) {
                case "1":
                    try {
                        Spell usedSpell = userSpells[0];
                        if (usedSpell.getType().contains("Active")) {
                            current = usedSpell.use(current, active);
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "2":
                    try {
                        Spell usedSpell = userSpells[1];
                        if (usedSpell.getType().contains("Active")) {
                            current = usedSpell.use(current, active);
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "3":
                    try {
                        Spell usedSpell = userSpells[2];
                        if (usedSpell.getType().contains("Active")) {
                            current = usedSpell.use(current, active);
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "4":
                    try {
                        Spell usedSpell = userSpells[3];
                        if (usedSpell.getType().contains("Active")) {
                            current = usedSpell.use(current, active);
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "5":
                    try {
                        Spell usedSpell = userSpells[4];
                        if (usedSpell.getType().contains("Active")) {
                            current = usedSpell.use(current, active);
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "6":
                    try {
                        Spell usedSpell = userSpells[5];
                        if (usedSpell.getType().contains("Active")) {
                            current = usedSpell.use(current, active);
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "7":
                    selected = true;
                default:
                    break;
            }
        }
        return current;
    }

    private boolean useSpell(Player attacker, Player defender, boolean turn) {
        boolean selected = false;

        ArrayList<Player> active = new ArrayList<Player>();
        active.add(attacker);
        active.add(defender);
        while (!selected) {
            Spell[] userSpells = attacker.getSpells();
            attacker.seeSpells();
            System.out.println("7- Back");
            switch (scanner.nextLine()) {
                case "1":
                    try {
                        Spell usedSpell = userSpells[0];
                        if (usedSpell.getType().contains("Active")) {
                            turn = usedSpell.use(0, active) == 0 ? turn : !turn;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "2":
                    try {
                        Spell usedSpell = userSpells[1];
                        if (usedSpell.getType().contains("Active")) {
                            turn = usedSpell.use(0, active) == 0 ? turn : !turn;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "3":
                    try {
                        Spell usedSpell = userSpells[2];
                        if (usedSpell.getType().contains("Active")) {
                            turn = usedSpell.use(0, active) == 0 ? turn : !turn;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "4":
                    try {
                        Spell usedSpell = userSpells[3];
                        if (usedSpell.getType().contains("Active")) {
                            turn = usedSpell.use(0, active) == 0 ? turn : !turn;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "5":
                    try {
                        Spell usedSpell = userSpells[4];
                        if (usedSpell.getType().contains("Active")) {
                            turn = usedSpell.use(0, active) == 0 ? turn : !turn;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "6":
                    try {
                        Spell usedSpell = userSpells[5];
                        if (usedSpell.getType().contains("Active")) {
                            turn = usedSpell.use(0, active) == 0 ? turn : !turn;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case "7":
                    selected = true;
                default:
                    break;
            }
        }
        return turn;
    }

    public boolean menu(Player user, boolean isDuel) {
        boolean selected = false;
        String id;

        user.seeStats();
        while (!selected) {
            System.out.println(
                    "\nTo open item menu, type 'i'.\nTo open spellbook, type 's'.\nTo give someone gold, type 'g'.\nTo exit character menu, type 'y'."
                            + (isDuel ? "\nTo concede this match, type 'q'." : ""));
            switch (scanner.nextLine()) {
                case "q":
                    if (isDuel) {
                        return true;
                    }
                case "g":
                    while (!selected) {
                        System.out.println("You have " + user.seeGold() + " gold.\nWho will you give gold to?");
                        for (int i = 0, j = 0; i < playerNumber; i++) {
                            Player player = players.get(i);
                            if (player == user) {
                                continue;
                            }
                            j++;
                            System.out.println(j + "- " + player.getName());
                        }
                        System.out.println(playerNumber + "- Exit");
                        id = scanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId <= players.indexOf(user) && playerId > 0) {
                                while (!selected) {
                                    System.out.println("How much gold will you give?");
                                    id = scanner.nextLine();
                                    try {
                                        int gold = Integer.parseInt(id);
                                        if (gold > 0) {
                                            user.giveMoney(gold, players.get(playerId - 1));
                                            selected = true;
                                        } else if (gold == 0) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                user.seeStats();
                            } else if (playerId < playerNumber && playerId > 0) {
                                while (!selected) {
                                    System.out.println("How much gold will you give?");
                                    id = scanner.nextLine();
                                    try {
                                        int gold = Integer.parseInt(id);
                                        if (gold > 0) {
                                            user.giveMoney(gold, players.get(playerId));
                                            selected = true;
                                        } else if (gold == 0) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                user.seeStats();
                            } else if (playerId == playerNumber) {
                                user.seeStats();
                                selected = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected = false;
                    break;
                case "y":
                    selected = true;
                    break;
                case "h":
                    while (!selected) {
                        user.seeItem(1);
                        System.out.println("1- Equip an item on head\n2- Unequip current item\n3- Deselect item");
                        switch (scanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < number && itemId > 0) {
                                            if (user.putOn(itemId - 1, 0)) {
                                                selected = true;
                                                user.seeStats();
                                            }
                                        } else if (itemId == number) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                break;
                            case "2":
                                user.takeOff(0);
                                selected = true;
                                user.seeStats();
                                break;
                            case "3":
                                selected = true;
                                user.seeStats();
                                break;
                            default:
                                break;
                        }
                    }
                    selected = false;
                    break;
                case "b":
                    while (!selected) {
                        user.seeItem(2);
                        System.out.println("1- Equip an item on body\n2- Unequip current item\n3- Deselect item");
                        switch (scanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < number && itemId > 0) {
                                            if (user.putOn(itemId - 1, 1)) {
                                                selected = true;
                                                user.seeStats();
                                            }
                                        } else if (itemId == number) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                break;
                            case "2":
                                user.takeOff(1);
                                selected = true;
                                user.seeStats();
                                break;
                            case "3":
                                selected = true;
                                user.seeStats();
                                break;
                            default:
                                break;
                        }
                    }
                    selected = false;
                    break;
                case "f":
                    while (!selected) {
                        user.seeItem(3);
                        System.out.println("1- Equip an item on feet\n2- Unequip current item\n3- Deselect item");
                        switch (scanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < number && itemId > 0) {
                                            if (user.putOn(itemId - 1, 2)) {
                                                selected = true;
                                                user.seeStats();
                                            }
                                        } else if (itemId == number) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                break;
                            case "2":
                                user.takeOff(2);
                                selected = true;
                                user.seeStats();
                                break;
                            case "3":
                                selected = true;
                                user.seeStats();
                                break;
                            default:
                                break;
                        }
                    }
                    selected = false;
                    break;
                case "m":
                    while (!selected) {
                        user.seeItem(4);
                        System.out.println("1- Equip an item on main hand\n2- Unequip current item\n3- Deselect item");
                        switch (scanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < number && itemId > 0) {
                                            if (user.putOn(itemId - 1, 3)) {
                                                selected = true;
                                                user.seeStats();
                                            }
                                        } else if (itemId == number) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                break;
                            case "2":
                                user.takeOff(3);
                                selected = true;
                                user.seeStats();
                                break;
                            case "3":
                                selected = true;
                                user.seeStats();
                                break;
                            default:
                                break;
                        }
                    }
                    selected = false;
                    break;
                case "o":
                    while (!selected) {
                        user.seeItem(5);
                        System.out.println("1- Equip an item on off hand\n2- Unequip current item\n3- Deselect item");
                        switch (scanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < number && itemId > 0) {
                                            if (user.putOn(itemId - 1, 4)) {
                                                selected = true;
                                                user.seeStats();
                                            }
                                        } else if (itemId == number) {
                                            selected = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                break;
                            case "2":
                                user.takeOff(4);
                                selected = true;
                                user.seeStats();
                                break;
                            case "3":
                                selected = true;
                                user.seeStats();
                                break;
                            default:
                                break;
                        }
                    }
                    selected = false;
                    break;
                case "i":
                    int number = user.seeBackpack() + 1;
                    System.out.println(number + "- Back");
                    while (!selected) {
                        id = scanner.nextLine();
                        try {
                            int itemId = Integer.parseInt(id);
                            if (itemId > 0 && itemId < number) {
                                user.seeItem(itemId + 5);
                                while (!selected) {
                                    System.out.println("1- Give Item to Another Player\n2- Deselect item");
                                    switch (scanner.nextLine()) {
                                        case "1":
                                            while (!selected) {
                                                for (int i = 0, j = 0; i < playerNumber; i++) {
                                                    Player player = players.get(i);
                                                    if (player == user) {
                                                        continue;
                                                    }
                                                    j++;
                                                    System.out.println(j + "- " + player.getName());
                                                }
                                                System.out.println(playerNumber + "- Exit");
                                                id = scanner.nextLine();
                                                try {
                                                    int playerId = Integer.parseInt(id);
                                                    if (playerId <= players.indexOf(user) && playerId > 0) {
                                                        user.giveItem(itemId, players.get(playerId - 1));
                                                        selected = true;
                                                        number = user.seeBackpack() + 1;
                                                        System.out.println(number + "- Back");
                                                    } else if (playerId < playerNumber && playerId > 0) {
                                                        user.giveItem(itemId, players.get(playerId));
                                                        selected = true;
                                                        number = user.seeBackpack() + 1;
                                                        System.out.println(number + "- Back");
                                                    } else if (playerId == playerNumber) {
                                                        selected = true;
                                                    }
                                                } catch (Exception ignored) {
                                                }
                                            }
                                            selected = false;
                                            break;
                                        case "2":
                                            selected = true;
                                            number = user.seeBackpack() + 1;
                                            System.out.println(number + "- Back");
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                selected = false;
                            } else if (itemId == number) {
                                selected = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected = false;
                    break;
                case "s":
                    user.seeSpells();
                    System.out.println("7- Back");
                    while (!selected) {
                        id = scanner.nextLine();
                        try {
                            int spellId = Integer.parseInt(id);
                            if (spellId > 0 && spellId < 7) {
                                user.seeSpell(spellId - 1, false);
                                System.out.println();
                                user.seeSpells();
                                System.out.println("7- Back");
                            } else if (spellId == 7) {
                                selected = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected = false;
                default:
            }
        }
        return false;
    }

    public void shop(Player player) {
        boolean finished = false;
        boolean selected1 = false;
        boolean selected2 = false;
        int j = 1;
        ArrayList<Spell> shopSpells = new ArrayList<>();
        ArrayList<Item> shopItems = new ArrayList<>();
        ArrayList<Item> backpack;
        int shopSize;

        while (!finished) {
            System.out.println(
                    "\nWhat would you like to do in the shop?\n1- Browse Items\n2- See Spells\n3- Sell Items\n4- Exit Shop");
            switch (scanner.nextLine()) {
                case "1":
                    while (!selected1) {
                        System.out.println("\n1- Head\n2- Body\n3- Feet\n4- Weapon\n5- Shield\n6- Amulet\n7- Exit");
                        shopItems.clear();
                        switch (scanner.nextLine()) {
                            case "1":
                                for (int i = 0; i < player.getRank(); i++) {
                                    Item item = items.get(i);
                                    if (item.getType().contains("Head")) {
                                        shopItems.add(item);
                                        System.out.println(j + "- " + item.getName() + item.getPrice());
                                        j++;
                                    }
                                }
                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buyItem(selectedItem);
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        if (itemId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "2":
                                for (int i = 0; i < player.getRank(); i++) {
                                    Item item = items.get(i);
                                    if (item.getType().contains("Body")) {
                                        shopItems.add(item);
                                        System.out.println(j + "- " + item.getName() + item.getPrice());
                                        j++;
                                    }
                                }
                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buyItem(selectedItem);
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        if (itemId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "3":
                                for (int i = 0; i < player.getRank(); i++) {
                                    Item item = items.get(i);
                                    if (item.getType().contains("Feet")) {
                                        shopItems.add(item);
                                        System.out.println(j + "- " + item.getName() + item.getPrice());
                                        j++;
                                    }
                                }
                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buyItem(selectedItem);
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        if (itemId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "4":
                                for (int i = 0; i < player.getRank(); i++) {
                                    Item item = items.get(i);
                                    if (item.getType().contains("Weapon")) {
                                        shopItems.add(item);
                                        System.out.println(j + "- " + item.getName() + item.getPrice());
                                        j++;
                                    }
                                }
                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buyItem(selectedItem);
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        if (itemId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "5":
                                for (int i = 0; i < player.getRank(); i++) {
                                    Item item = items.get(i);
                                    if (item.getType().contains("Shield")) {
                                        shopItems.add(item);
                                        System.out.println(j + "- " + item.getName() + item.getPrice());
                                        j++;
                                    }
                                }
                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buyItem(selectedItem);
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        if (itemId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "6":
                                for (int i = 0; i < player.getRank(); i++) {
                                    Item item = items.get(i);
                                    if (item.getType().contains("Amulet")) {
                                        shopItems.add(item);
                                        System.out.println(j + "- " + item.getName() + item.getPrice());
                                        j++;
                                    }
                                }
                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buyItem(selectedItem);
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        if (itemId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "7":
                                selected1 = true;
                                break;
                            default:
                                break;
                        }
                    }
                    selected1 = false;
                    break;
                case "2":
                    while (!selected1) {
                        shopSpells.clear();
                        System.out.println("\n1- Active\n2- Passive\n3- Exit");
                        switch (scanner.nextLine()) {
                            case "1":
                                for (int i = 0; i < spells.size(); i++) {
                                    Spell spell = spells.get(i);
                                    j = 0;
                                    for (Spell[] playerSpells = player.getSpells(); j < playerSpells.length; j++) {
                                        if (playerSpells[j] == null) {
                                            break;
                                        }
                                        spell = spell.getName().equals(playerSpells[j].getName()) ? playerSpells[j]
                                                : spell;
                                    }
                                    shopSpells.add(spell);
                                    System.out.println((i + 1) + "- " + spell.getName() + spell.getPrice());
                                }
                                j = shopSpells.size() + 1;

                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int spellId = Integer.parseInt(id);
                                        if (spellId < j && spellId > 0) {
                                            Spell selectedSpell = shopSpells.get(spellId - 1);
                                            int level = selectedSpell.getLevel();
                                            selectedSpell.seeSpell(true, player);
                                            switch (level) {
                                                case 0:
                                                    System.out.println("1- Buy\n2- Go Back");
                                                    break;
                                                case 5:
                                                    System.out.println(
                                                            "This spell is already max level.\n\nPress any key to go back.");
                                                    scanner.nextLine();
                                                    selected1 = true;
                                                    break;
                                                case 1:
                                                case 2:
                                                case 3:
                                                case 4:
                                                    System.out.println("1- Upgrade\n2- Go Back");
                                                    break;
                                            }
                                            while (!selected1) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buySpell(selectedSpell);
                                                        spells.clear();
                                                        addSpells(spells, scanner);
                                                        selected1 = true;
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected1 = true;
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            selected1 = false;
                                        }
                                        if (spellId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "2":
                                for (int i = 0; i < skills.size(); i++) {
                                    Spell spell = skills.get(i);
                                    j = 0;
                                    for (Spell[] playerSpells = player.getSpells(); j < playerSpells.length; j++) {
                                        if (playerSpells[j] == null) {
                                            break;
                                        }
                                        spell = spell.getName().equals(playerSpells[j].getName()) ? playerSpells[j]
                                                : spell;
                                    }
                                    shopSpells.add(spell);
                                    System.out.println((i + 1) + "- " + spell.getName() + spell.getPrice());
                                }
                                j = shopSpells.size() + 1;

                                System.out.println(j + "- Go Back");
                                while (!selected2) {
                                    String id = scanner.nextLine();
                                    try {
                                        int spellId = Integer.parseInt(id);
                                        if (spellId < j && spellId > 0) {
                                            Spell selectedSpell = shopSpells.get(spellId - 1);
                                            int level = selectedSpell.getLevel();
                                            selectedSpell.seeSpell(true, player);
                                            switch (level) {
                                                case 0:
                                                    System.out.println("1- Buy\n2- Go Back");
                                                    break;
                                                case 5:
                                                    System.out.println(
                                                            "This spell is already max level.\n\nPress any key to go back.");
                                                    scanner.nextLine();
                                                    selected1 = true;
                                                    break;
                                                case 1:
                                                case 2:
                                                case 3:
                                                case 4:
                                                    System.out.println("1- Upgrade\n2- Go Back");
                                                    break;
                                            }
                                            while (!selected1) {
                                                switch (scanner.nextLine()) {
                                                    case "1":
                                                        player.buySpell(selectedSpell);
                                                        skills.clear();
                                                        addSkills(skills);
                                                        selected1 = true;
                                                        selected2 = true;
                                                        break;
                                                    case "2":
                                                        selected1 = true;
                                                        selected2 = true;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            selected1 = false;
                                        }
                                        if (spellId == j) {
                                            selected2 = true;
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                selected2 = false;
                                j = 1;
                                break;
                            case "3":
                                selected1 = true;
                                break;
                            default:
                                break;
                        }
                    }
                    selected1 = false;
                    break;
                case "3":
                    while (!selected1) {
                        shopItems.clear();
                        backpack = player.getBackpack();
                        shopSize = backpack.size();
                        if (shopSize == 0) {
                            System.out.println("You have no items in your backpack.");
                            selected1 = true;
                            break;
                        }
                        for (int i = 0; i < shopSize; i++) {
                            Item item = backpack.get(i);
                            shopItems.add(item);
                            System.out.println((i + 1) + "- " + item.getName() + item.getPrice());
                        }
                        System.out.println((shopSize + 1) + "- Go Back");
                        while (!selected2) {
                            System.out.println(
                                    "Remember that when selling items, they go for half the above shown price.");
                            String id = scanner.nextLine();
                            try {
                                int itemId = Integer.parseInt(id);
                                if (itemId < shopSize + 1 && itemId > 0) {
                                    Item selectedItem = shopItems.get(itemId - 1);
                                    selectedItem.seeItem();
                                    System.out.println("1- Sell\n2- Go Back");
                                    while (!selected2) {
                                        switch (scanner.nextLine()) {
                                            case "1":
                                                player.sellItem(selectedItem);
                                                selected2 = true;
                                                break;
                                            case "2":
                                                selected2 = true;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                                if (itemId == shopSize + 1) {
                                    selected2 = true;
                                }
                            } catch (Exception ignored) {
                            }
                        }
                        selected2 = false;
                        break;
                    }
                    selected1 = false;
                    break;
                case "4":
                    finished = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void duel(Player first, Player second) {
        boolean gameOver = false;
        boolean turn = true;
        ArrayList<Player> active = new ArrayList<>();
        double id = Math.random();
        active.add(first);
        active.add(second);

        System.out.println("\n\n! The prize is a " + first.randomItem(items, id).getName() + " !\n\n");
        gameOver = getReady(active, true);

        if (gameOver) {
            winner(active, first, id);
            return;
        }

        while (!gameOver) {
            System.out.println("\n\n" + (turn ? first.getName() : second.getName()) + "'s turn");
            System.out.println("1- Character Menu\n2- Basic Attack\n3- Use Spell");
            switch (scanner.nextLine()) {
                case "1":
                    menu(turn ? first : second, false);
                    break;
                case "2":
                    turn = basicAttack(turn ? first : second, turn ? second : first, turn);
                    break;
                case "3":
                    turn = useSpell(turn ? first : second, turn ? second : first, turn);
                    break;
                default:
                    System.out.println("Please select an action.");
                    break;
            }

            if (first.isDead()) {
                winner(active, second, id);
                second.winTrophy(scanner);
                gameOver = true;
            }
            if (second.isDead()) {
                winner(active, first, id);
                first.winTrophy(scanner);
                gameOver = true;
            }
        }
    }

    public void arena(ArrayList<Player> players, int current) {
        boolean gameOver = false;
        Player user = players.get(current);
        int activeNumber;

        ArrayList<Player> active = new ArrayList<>(players);
        activeNumber = active.size();

        getReady(active, false);

        while (!gameOver) {
            if (activeNumber == 1) {
                winner(players, user, Math.random());
                user.winTrophy(scanner);
                gameOver = true;
                break;
            }

            System.out.println("\n\n" + user.getName() + "'s turn");
            System.out.println("1- Character Menu\n2- Basic Attack\n3- Use Spell");
            switch (scanner.nextLine()) {
                case "1":
                    menu(user, false);
                    break;
                case "2":
                    current = basicAttack(active, current);
                    current = endTurn(active, current);
                    break;
                case "3":
                    current = useSpell(active, current);
                    current = endTurn(active, current);
                    break;
                default:
                    System.out.println("Please select an action.");
                    break;
            }
            activeNumber = active.size();
            user = active.get(current);
        }
    }

    public Action(ArrayList<Player> gamePlayers, Scanner scn) {
        scanner = scn;
        playerNumber = gamePlayers.size();
        for (int i = 0; i < playerNumber; i++) {
            players.add(gamePlayers.get(i));
        }
        addItems(items);
        addSpells(spells, scn);
        addSkills(skills);
    }

    public static void addItems(ArrayList<Item> items) {
        items.add(new Item("Leather Hood", 22, new String[] { "Head" }, 15,
                new int[] { 0, 3, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0 },
                "Offers some protection, mostly from the rain"));

        items.add(new Item("Clay Pot", 15, new String[] { "Head" }, 15,
                new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0 },
                "You probably shouldn't put one of these on your head"));

        items.add(new Item("Wolf Head Hood", 18, new String[] { "Head" }, 20,
                new int[] { 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 3 },
                "Used by the guardians of the forest during their rituals"));

        items.add(new Item("Blindfold", 25, new String[] { "Head" }, 15,
                new int[] { 0, -1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "A black band to cover your eyes and help your inner focus"));

        items.add(new Item("Dirty Rags", 1, new String[] { "Body" }, 10,
                new int[] { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                "The homeless look; not a very exciting reward for an arena"));

        items.add(new Item("Yellow Sheet", 6, new String[] { "Body" }, 20,
                new int[] { 0, 0, 2, 2, 0, 0, 0, 0, 0, 1, 0, 0 },
                "Never feel cold again wrapped in these sheets"));

        items.add(new Item("Not-So-Maculine Cuirass", 20, new String[] { "Body" }, 15,
                new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0 },
                "If you don't mind the look, it's actually pretty decent armour"));

        items.add(new Item("Shrub", 17, new String[] { "Body" }, 25,
                new int[] { 0, 4, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                "Hide in plain sight with a shrub disguise"));

        items.add(new Item("Adventurer's Boots", 14, new String[] { "Feet" }, 20,
                new int[] { 3, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2 },
                "Sturdy boots for anyone making a long and difficult journey"));

        items.add(new Item("Webbed Feet", 23, new String[] { "Feet" }, 20,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                "Helps you swim faster and gives you 20 MP"));

        items.add(new Item("Pointy Shoes", 26, new String[] { "Feet" }, 10,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 },
                "If you put on a funny hat, you would look like a great fool"));

        items.add(new Item("Fluffy Slippers", 7, new String[] { "Feet" }, 20,
                new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1 },
                "Feels like stepping on clouds"));

        items.add(new Item("Wooden Leg", 21, new String[] { "Feet" }, 25,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "If you still have both legs, there's no real reason why you would need this, aside from the fact it gives you +5% gold for every victory"));

        items.add(new Item("Snow Rackets", 29, new String[] { "Feet", "Snow" }, 10,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Rackets to help you walk in the snow"));

        items.add(new Item("Improvised Bow", 28, new String[] { "Weapon", "Quick", "Bow", "Double-Handed" }, 15,
                new int[] { 0, 2, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0 },
                "A makeshift bow that actually shoots straight (most of the time)"));

        items.add(new Item("Magic Wand", 12, new String[] { "Weapon", "Magic" }, 20,
                new int[] { 0, 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, 0 },
                "From the fairy godmother's factory, very good for beginner magicians"));

        items.add(new Item("Gladius", 13, new String[] { "Weapon", "Brute" }, 20,
                new int[] { 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0 },
                "A simple but reliable sword; you'll never be disappointed"));

        items.add(new Item("Snow Balls", 32, new String[] { "Weapon", "Brute", "Magic", "Snow" }, 10,
                new int[] { 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0 },
                "Throw them at your friends, throw them at your enemies; the cold will numb them a little bit"));

        items.add(new Item("Priestly Scepter", 9, new String[] { "Weapon", "Sacred", "Double-Handed" }, 20,
                new int[] { 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, 0, 0 },
                "Used by priests for religious activities or to channel the power of a deity against their enemies"));

        items.add(new Item("Bronze Dagger", 16, new String[] { "Weapon", "Quick" }, 15,
                new int[] { 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0 },
                "They say one of these was used to kill the emperor"));

        items.add(new Item("Incense", 31, new String[] { "Weapon", "Sacred" }, 10,
                new int[] { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 },
                "If you burn enough of it, some being might help you out"));

        items.add(new Item("Chopsticks", 27, new String[] { "Weapon", "Quick" }, 20,
                new int[] { 0, 0, 0, 4, 0, 1, 0, 0, 0, 0, 0, 0 },
                "Usually used for eating, but they can hurt quite a bit when used for stabbing"));

        items.add(new Item("Whip with Nails", 8, new String[] { "Weapon", "Quick" }, 20,
                new int[] { 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0 },
                "Not the most powerful weapon, and definitely not the most humane"));

        items.add(new Item("Very Thin Sword", 5, new String[] { "Weapon", "Quick" }, 15,
                new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                "This majestic sword also makes your healing abilities heal 4 more HP"));

        items.add(new Item("Spear", 34, new String[] { "Weapon", "Brute" }, 10,
                new int[] { 3, 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
                "A combat spear"));

        items.add(new Item("Silver Shield", 19, new String[] { "Shield" }, 25,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4 },
                "Strong, resistent, durable; the best shield in the market"));

        items.add(new Item("Kite Shield", 33, new String[] { "Shield" }, 10,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 2 },
                "Classic shield to cover your whole body"));

        items.add(new Item("Paper Shield", 3, new String[] { "Shield" }, 15,
                new int[] { 0, 0, 0, 0, 0, 3, 0, 0, 4, 0, 0, 0 },
                "Believe it or not, paper is really strong"));

        items.add(new Item("Round Target", 11, new String[] { "Shield" }, 15,
                new int[] { 0, 0, 2, 0, 0, 0, 0, 0, 0, 5, 0, 0 },
                "Test your enemy's aim"));

        items.add(new Item("Viper Fang", 4, new String[] { "Amulet" }, 15,
                new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Poisonous enough that every one of your basic attacks will do 1 poison damage"));

        items.add(new Item("Potato", 10, new String[] { "Amulet" }, 20,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Holding this potato that gives 20 HP, you'll be unstoppble"));

        items.add(new Item("Shiny Rock", 2, new String[] { "Amulet" }, 50,
                new int[] { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "It glows pretty"));

        items.add(new Item("Bear Trap", 24, new String[] { "Amulet" }, 50,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Anyone making a quick attack on you will have a nasty surprise: 2 damage"));

        items.add(new Item("Cracked Gladiator Helm", 57, new String[] { "Head" }, 30,
                new int[] { 3, 0, 1, 0, 0, 0, 0, 0, 5, 0, 0, 0 },
                "The last person who wore this died to the sound of applause"));

        items.add(new Item("Straw Hat", 73, new String[] { "Head" }, 30,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7 },
                "Keeps you safe from the sun and from MAG attacks"));

        items.add(new Item("Samurai Bun", 64, new String[] { "Head" }, 45,
                new int[] { 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "You don't have to be a samurai to wear one of these"));

        items.add(new Item("Monacle", 69, new String[] { "Head" }, 45,
                new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Typically worn by physicians when doing surgery"));

        items.add(new Item("Quiver of Poisonous Arrows", 68, new String[] { "Body" }, 50,
                new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Will do 8 poison damage if you're using a bow"));

        items.add(new Item("Robust Plates", 53, new String[] { "Body" }, 30,
                new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0 },
                "The king had these made for his most important knights"));

        items.add(new Item("Grandmaster's Cape", 59, new String[] { "Body" }, 30,
                new int[] { 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 4 },
                "Gives off a majestic and royal image"));

        items.add(new Item("Dark Cloak", 49, new String[] { "Body" }, 30,
                new int[] { 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0 },
                "Helps you hide in the darkness and look mysterious"));

        items.add(new Item("Mantle Covered in Weeds", 71, new String[] { "Body" }, 30,
                new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 0, 4, 0, 0 },
                "It's a little itchy, but it will make you immune to poison"));

        items.add(new Item("Light Sandals", 51, new String[] { "Feet" }, 30,
                new int[] { 0, 3, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
                "Doesn't restrict your movement"));

        items.add(new Item("Toe Ring", 52, new String[] { "Feet" }, 25,
                new int[] { 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Please don't wear this and the cuirass at the same time"));

        items.add(new Item("Ball and Chain", 65, new String[] { "Feet" }, 45,
                new int[] { 10, 0, 0, 0, -8, -8, -8, -8, 0, 0, 0, 0 },
                "You probably shouldn't have stolen that chicken"));

        items.add(new Item("Clasped Sandals", 74, new String[] { "Feet" }, 30,
                new int[] { 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Doesn't slide off your feet easily"));

        items.add(new Item("Boots with Hobnails", 60, new String[] { "Feet" }, 50,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 4, 4 },
                "A hobnail is a little nail at the sole of the boot that helps you not lose your footing on difficult terrain"));

        items.add(new Item("Flasks of Poison Gas", 66, new String[] { "Weapon", "Quick", "Magic" }, 50,
                new int[] { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Throw one of these at an enemy and it will do 8 poison damage"));

        items.add(new Item("Arena Sword", 62, new String[] { "Weapon", "Brute" }, 30,
                new int[] { 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0 },
                "This sword has seen a lot of blood"));

        items.add(new Item("Pilgrim's Staff", 42, new String[] { "Weapon", "Sacred" }, 45,
                new int[] { 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Travelers from the whole world would make the trip to the top of the mountain, it's always been a bit of a mystery what it is they were after"));

        items.add(new Item("Bouquet of Tulips", 45, new String[] { "Weapon", "Sacred", "Magic" }, 50,
                new int[] { 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Not the most threatening weapon, but it will give you 10 HP"));

        items.add(new Item("Flail", 75, new String[] { "Weapon", "Brute" }, 25,
                new int[] { 1, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0 },
                "A mace on a chain"));

        items.add(new Item("Cleaver", 44, new String[] { "Weapon", "Quick" }, 50,
                new int[] { 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0 },
                "Chop chop"));

        items.add(new Item("Ice Bow", 58, new String[] { "Weapon", "Quick", "Magic", "Bow", "Double-Handed", "Snow" }, 45,
                new int[] { 0, 0, 0, 0, 0, 10, 0, 10, 0, 0, 0, 0 },
                "Completely made of ice, pretty cool"));

        items.add(new Item("Fishing Bow", 67, new String[] { "Weapon", "Quick", "Bow", "Double-Handed" }, 50,
                new int[] { 0, 0, 8, 0, 0, 6, 0, 0, 0, 0, 0, 0 },
                "More hardcore than traditional fishing with a fishing pole"));

        items.add(new Item("Two-Handed Sword", 61, new String[] { "Weapon", "Brute", "Double-Handed" }, 70,
                new int[] { 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0 },
                "A long and heavy sword, one of the favorites in the arena"));

        items.add(new Item("Native Shield", 56, new String[] { "Shield" }, 50,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 6, 8, 0, 0 },
                "Carefully crafted from the toughest woods in the magic forest"));

        items.add(new Item("Bark Shield", 48, new String[] { "Shield" }, 50,
                new int[] { 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5 },
                "Tree bark might not be the best material for a shield"));

        items.add(new Item("Gate", 41, new String[] { "Shield" }, 50,
                new int[] { 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0 },
                "In a moment of desperation, anything can be used as a shield"));

        items.add(new Item("Tablet with Ancient Symbols", 72, new String[] { "Shield" }, 45,
                new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 3, 3, 0, 3 },
                "No one knows who wrote these runes or why they give you protection"));

        items.add(new Item("Viking Skull", 43, new String[] { "Amulet" }, 50,
                new int[] { 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "The spiky hat suggests this used to belong to a viking, but some suspect it might be one of you in a previous life"));

        items.add(new Item("Sacred Relic", 50, new String[] { "Amulet" }, 40,
                new int[] { 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                "The church tried it's hardest to protect it, yet somehow it appeared in the arena one day"));

        items.add(new Item("Magma Orb", 46, new String[] { "Amulet" }, 50,
                new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
                "Like a round lava lamp that gives you +5 fire damage"));

        items.add(new Item("Holy Water", 47, new String[] { "Amulet" }, 40,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4 },
                "Blessed"));

        items.add(new Item("Ice Shank", 63, new String[] { "Amulet" }, 50,
                new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
                "It might look like any other icicle, but this one gives you +6 snow damage"));
    }

    public void addSpells(ArrayList<Spell> spells, Scanner scn) {
        spells.add(new Fireball(scn));
        spells.add(new Freeze(scn));
        spells.add(new Healing(scn));
        spells.add(new Tackle(scn));
        spells.add(new Supernova(scn));
        spells.add(new Armageddon(scn));
        spells.add(new Icicles(scn));
        spells.add(new Wrath(scn));
    }

    public void addSkills(ArrayList<Spell> spells) {
        skills.add(new Spell("Thorns",
                new String[] { "When an enemy hits you with a BRU or QUI attack, they take 6 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 12 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 18 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 24 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 30 damage" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Reflective Skin",
                new String[] { "When an enemy hits you with a SAC or MAG attack, they take 6 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 12 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 18 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 24 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 30 damage" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Vampiric Aura",
                new String[] { "When you attack, heal 6 HP",
                        "When you attack, heal 12 HP",
                        "When you attack, heal 18 HP",
                        "When you attack, heal 24 HP",
                        "When you attack, heal 30 HP" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Sorcerer's Blessing",
                new String[] { "When you attack, recover 6 MP",
                        "When you attack, recover 12 MP",
                        "When you attack, recover 18 MP",
                        "When you attack, recover 24 MP",
                        "When you attack, recover 30 MP" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Heart of Stone",
                new String[] { "Receive 6 less damage",
                        "Receive 12 less damage",
                        "Receive 18 less damage",
                        "Receive 24 less damage",
                        "Receive 30 less damage" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Intimidating Presence",
                new String[] { "Other players have -5 attack",
                        "Other players have -10 attack",
                        "Other players have -15 attack",
                        "Other players have -20 attack",
                        "Other players have -25 attack" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Plunder",
                new String[] { "Get 10 times your level in gold for every player you kill",
                        "Get 20 times your level in gold for every player you kill",
                        "Get 30 times your level in gold for every player you kill",
                        "Get 40 times your level in gold for every player you kill",
                        "Get 50 times your level in gold for every player you kill" },
                new String[] { "Passive" }, 50, 0));

        skills.add(new Spell("Freezing Attacks",
                new String[] { "When you use a basic attack, give your enemy 6 freeze",
                        "When you use a basic attack, give your enemy 12 freeze",
                        "When you use a basic attack, give your enemy 18 freeze",
                        "When you use a basic attack, give your enemy 24 freeze",
                        "When you use a basic attack, give your enemy 30 freeze" },
                new String[] { "Passive" }, 50, 0));
    }
}