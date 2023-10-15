package util;

import global.GlobalItems;
import global.GlobalScanner;
import global.GlobalSkills;
import global.GlobalSpells;
import classes.Player;
import spells.*;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private static Action action = null;

    List<Player> players = new ArrayList<>();
    List<Item> items;
    List<Spell> spells = new ArrayList<>();
    List<Spell> skills = new ArrayList<>();

    int playerNumber;

    public static int next(int current, int active) {
        return current == active - 1 ? 0 : ++current;
    }

    public static void wizard(Player player) {
        if (player.getClassName().equals("Wizard")) {
            player.healMP(25);
        }

        player.clover();
    }

    private boolean getReady(List<Player> fighters, boolean isDuel) {
        boolean quit = false;

        System.out.println("Final Preparations");
        for (Player player : fighters) {
            for (Spell spell : player.getSpells()) {
                if (player.isSpell(spell, "Intimidating Presence")) {
                    for (Player enemy : fighters) {
                        if (enemy != player) {
                            enemy.effect("bruATK", -5 * spell.getLevel());
                            enemy.effect("quiATK", -5 * spell.getLevel());
                            enemy.effect("sacATK", -5 * spell.getLevel());
                            enemy.effect("magATK", -5 * spell.getLevel());
                        }
                    }
                }
            }
            player.alchemist(fighters, true);
        }
        for (int i = 0; i < fighters.size(); i++) {
            if (menu(fighters.get(i), i != 0 && isDuel)) {
                quit = true;
            }
        }
        return quit;
    }

    private void winner(List<Player> fighters, Player victor, Item item) {
        System.out.println(
                "\n--------------------------\n" + victor.getName() + " won the game!\n--------------------------");
        victor.getMoney(50);
        victor.getItem(item);
        for (Player player : fighters) {
            player.revive();
            for (Spell spell : player.getSpells()) {
                if (player.isSpell(spell, "Intimidating Presence")) {
                    for (Player enemy : fighters) {
                        if (enemy != player) {
                            enemy.effect("bruATK", 5 * spell.getLevel());
                            enemy.effect("quiATK", 5 * spell.getLevel());
                            enemy.effect("sacATK", 5 * spell.getLevel());
                            enemy.effect("magATK", 5 * spell.getLevel());
                        }
                    }
                }
            }
            player.alchemist(fighters, false);
        }
    }

    private int endTurn(List<Player> active, int current) {
        List<Player> begin = new ArrayList<>(active);
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

    private int basicAttack(List<Player> active, int current) {
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
            switch (GlobalScanner.nextLine()) {
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
                        String id = GlobalScanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(0);
                                defender = playerId <= active.indexOf(user) ? active.get(playerId - 1) : active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.bruteAttack(defender, d20);
                                defender.postDefend(user, 0, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = selected1 = true;
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
                        String id = GlobalScanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(1);
                                defender = playerId <= active.indexOf(user) ? active.get(playerId - 1) : active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.quickAttack(defender, d20);
                                defender.postDefend(user, 1, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = selected1 = true;
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
                        String id = GlobalScanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(2);
                                defender = playerId <= active.indexOf(user) ? active.get(playerId - 1) : active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.sacredAttack(defender, d20);
                                defender.postDefend(user, 2, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = selected1 = true;
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
                        String id = GlobalScanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId < activeNumber && playerId > 0) {
                                type = user.atkType(3);
                                defender = playerId <= active.indexOf(user) ? active.get(playerId - 1) : active.get(playerId);
                                d20 = ((int) (Math.random() * 20) + 1);
                                defender.preDefend(user, type);
                                counter = user.preAttack(d20, type, true);
                                user.magicAttack(defender, d20);
                                defender.postDefend(user, 3, type, counter);
                                current = user.postAttack(defender, type, true, current);
                                current = next(current, activeNumber);
                                wizard(active.get(current));
                                selected2 = selected1 = true;
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
            switch (GlobalScanner.nextLine()) {
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

    private int useSpell(List<Player> active, int current) {
        boolean selected = false;
        Player user = active.get(current);

        while (!selected) {
            Spell[] userSpells = user.getSpells();
            user.seeSpells();
            System.out.println("7- Back");
            switch (GlobalScanner.nextLine()) {
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

        List<Player> active = new ArrayList<Player>();
        active.add(attacker);
        active.add(defender);
        while (!selected) {
            Spell[] userSpells = attacker.getSpells();
            attacker.seeSpells();
            System.out.println("7- Back");
            switch (GlobalScanner.nextLine()) {
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
            switch (GlobalScanner.nextLine()) {
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
                        id = GlobalScanner.nextLine();
                        try {
                            int playerId = Integer.parseInt(id);
                            if (playerId <= players.indexOf(user) && playerId > 0) {
                                while (!selected) {
                                    System.out.println("How much gold will you give?");
                                    id = GlobalScanner.nextLine();
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
                                    id = GlobalScanner.nextLine();
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
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = GlobalScanner.nextLine();
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
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = GlobalScanner.nextLine();
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
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = GlobalScanner.nextLine();
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
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = GlobalScanner.nextLine();
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
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                while (!selected) {
                                    int number = user.seeBackpack() + 1;
                                    System.out.println(number + "- Exit");
                                    id = GlobalScanner.nextLine();
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
                        id = GlobalScanner.nextLine();
                        try {
                            int itemId = Integer.parseInt(id);
                            if (itemId > 0 && itemId < number) {
                                user.seeItem(itemId + 5);
                                while (!selected) {
                                    System.out.println("1- Give Item to Another Player\n2- Deselect item");
                                    switch (GlobalScanner.nextLine()) {
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
                                                id = GlobalScanner.nextLine();
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
                        id = GlobalScanner.nextLine();
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
        List<Spell> shopSpells = new ArrayList<>();
        List<Item> shopItems = new ArrayList<>();
        List<Item> backpack;
        int shopSize;

        while (!finished) {
            System.out.println(
                    "\nWhat would you like to do in the shop?\n1- Browse Items\n2- See Spells\n3- Sell Items\n4- Exit Shop");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    while (!selected1) {
                        System.out.println("\n1- Head\n2- Body\n3- Feet\n4- Weapon\n5- Shield\n6- Amulet\n7- Exit");
                        shopItems.clear();
                        switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
                                    try {
                                        int itemId = Integer.parseInt(id);
                                        if (itemId < j && itemId > 0) {
                                            Item selectedItem = shopItems.get(itemId - 1);
                                            selectedItem.seeItem();
                                            System.out.println("1- Buy\n2- Go Back");
                                            while (!selected2) {
                                                switch (GlobalScanner.nextLine()) {
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
                        switch (GlobalScanner.nextLine()) {
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
                                    String id = GlobalScanner.nextLine();
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
                                                    GlobalScanner.nextLine();
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
                                                switch (GlobalScanner.nextLine()) {
                                                    case "1":
                                                        player.buySpell(selectedSpell);
                                                        spells = GlobalSpells.addSpells();
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
                                    String id = GlobalScanner.nextLine();
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
                                                    GlobalScanner.nextLine();
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
                                                switch (GlobalScanner.nextLine()) {
                                                    case "1":
                                                        player.buySpell(selectedSpell);
                                                        skills = GlobalSkills.addSkills();
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
                            String id = GlobalScanner.nextLine();
                            try {
                                int itemId = Integer.parseInt(id);
                                if (itemId < shopSize + 1 && itemId > 0) {
                                    Item selectedItem = shopItems.get(itemId - 1);
                                    selectedItem.seeItem();
                                    System.out.println("1- Sell\n2- Go Back");
                                    while (!selected2) {
                                        switch (GlobalScanner.nextLine()) {
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
        List<Player> active = new ArrayList<>();
        Item prize = first.randomItem();
        active.add(first);
        active.add(second);

        System.out.println("\n\n! The prize is a " + prize.getName() + " !\n\n");
        gameOver = getReady(active, true);

        if (gameOver) {
            winner(active, first, prize);
            return;
        }

        while (!gameOver) {
            System.out.println("\n\n" + (turn ? first.getName() : second.getName()) + "'s turn");
            System.out.println("1- Character Menu\n2- Basic Attack\n3- Use Spell");
            switch (GlobalScanner.nextLine()) {
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
                winner(active, second, prize);
                second.winTrophy();
                gameOver = true;
            }
            if (second.isDead()) {
                winner(active, first, prize);
                first.winTrophy();
                gameOver = true;
            }
        }
    }

    public void arena(List<Player> players, int current) {
        boolean gameOver = false;
        Player user = players.get(current);
        int activeNumber;

        List<Player> active = new ArrayList<>(players);
        activeNumber = active.size();

        getReady(active, false);

        while (!gameOver) {
            if (activeNumber == 1) {
                winner(players, user, user.randomItem());
                user.winTrophy();
                gameOver = true;
                break;
            }

            System.out.println("\n\n" + user.getName() + "'s turn");
            System.out.println("1- Character Menu\n2- Basic Attack\n3- Use Spell");
            switch (GlobalScanner.nextLine()) {
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

    private Action(List<Player> gamePlayers) {
        playerNumber = gamePlayers.size();
        for (int i = 0; i < playerNumber; i++) {
            players.add(gamePlayers.get(i));
        }
        items = GlobalItems.addItems();
        spells = GlobalSpells.addSpells();
        skills.clear();
        skills = GlobalSkills.addSkills();
    }

    public static Action getAction(List<Player> gamePlayers) {
        if (action == null) {
            action = new Action(gamePlayers);
        }
        return action;
    }
}