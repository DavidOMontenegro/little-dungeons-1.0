package com.ld.util;

import com.ld.global.GlobalItems;
import com.ld.global.GlobalScanner;
import com.ld.global.GlobalSkills;
import com.ld.global.GlobalSpells;
import com.ld.classes.Player;
import com.ld.spells.*;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private static Action action = null;

    List<Item> items;
    List<Spell> spells;
    List<Spell> skills;

    int playerNumber;

    public static int next(int current, int active) {
        return current == active - 1 ? 0 : ++current;
    }

    private boolean getReady(List<Player> players, boolean arena) {
        boolean quit = false;

        System.out.println("Final Preparations");
        for (Player player : players) {
            for (Spell spell : player.getSpells()) {
                if (player.isSpell(spell, "Intimidating Presence")) {
                    for (Player enemy : players) {
                        if (enemy != player) {
                            enemy.effect("bruATK", -5 * spell.getLevel());
                            enemy.effect("quiATK", -5 * spell.getLevel());
                            enemy.effect("sacATK", -5 * spell.getLevel());
                            enemy.effect("magATK", -5 * spell.getLevel());
                        }
                    }
                }
            }
            player.alchemist(players, true);
        }
        for (int i = 0; i < players.size(); i++) {
            quit = menu(players.get(i), i != 0 && !arena);
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

    private void basicAttack() {
        boolean selected1 = false;
        boolean selected2 = false;
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        Player defender;
        while (!selected1) {
            System.out.println("1- Brute Attack\n2- Quick Attack\n3- Sacred Attack\n4- Magic Attack\n5- Exit");
            String atkType = GlobalScanner.nextLine();
            try {
                int nature = Integer.parseInt(atkType);
                if (nature > 0 && nature < 6) {
                    if (nature < 5) {
                        while (!selected2) {
                            String id;
                            if (playerHandler.getActive() > 2) {
                                System.out.println("Which player will you attack?");
                                for (int i = 0, j = 0; i < playerHandler.getActive(); i++) {
                                    Player player = playerHandler.getPlayer(i);
                                    if (player == user) {
                                        continue;
                                    }
                                    j++;
                                    System.out.println(j + "- " + player.getName());
                                }
                                System.out.println((playerHandler.getActive()) + "- Exit");
                                id = GlobalScanner.nextLine();
                            } else {
                                id = "1";
                            }
                            try {
                                int playerId = Integer.parseInt(id);
                                if (playerId < playerHandler.getActive() && playerId > 0) {
                                    defender = playerId <= playerHandler.indexOf(user) ? playerHandler.getPlayer(playerId - 1) : playerHandler.getPlayer(playerId);
                                    user.attack(nature, defender);
                                    playerHandler.next();
                                    selected2 = true;
                                } else if (playerId == playerHandler.getActive()) {
                                    selected2 = true;
                                }
                            } catch (Exception ignored) {
                            }
                        }
                        selected2 = false;
                    }
                    selected1 = true;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void useSpell() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        boolean selected = false;
        Player user = playerHandler.current();

        while (!selected) {
            Spell[] userSpells = user.getSpells();
            user.seeSpells();
            System.out.println("7- Back");
            String spellId = GlobalScanner.nextLine();
            try {
                int id = Integer.parseInt(spellId);
                if (id > 0 && id < 8) {
                    if (id < 7) {
                        try {
                            Spell usedSpell = userSpells[--id];
                            if (usedSpell.getType().contains("Active")) {
                                usedSpell.use();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected = true;
                }
            } catch (Exception ignored) {
            }
        }
    }

    public boolean menu(Player user, boolean isDuel) {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        boolean selected = false;
        String id;
        isDuel = isDuel && user != playerHandler.getPlayer(0);

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
                            Player player = playerHandler.getPlayer(i);
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
                            if (playerId <= playerHandler.indexOf(user) && playerId > 0) {
                                while (!selected) {
                                    System.out.println("How much gold will you give?");
                                    id = GlobalScanner.nextLine();
                                    try {
                                        int gold = Integer.parseInt(id);
                                        if (gold > 0) {
                                            user.giveMoney(gold, playerHandler.getPlayer(playerId - 1));
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
                                            user.giveMoney(gold, playerHandler.getPlayer(playerId));
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
                                                    Player player = playerHandler.getPlayer(i);
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
                                                    if (playerId <= playerHandler.indexOf(user) && playerId > 0) {
                                                        user.giveItem(itemId, playerHandler.getPlayer(playerId - 1));
                                                        selected = true;
                                                        number = user.seeBackpack() + 1;
                                                        System.out.println(number + "- Back");
                                                    } else if (playerId < playerNumber && playerId > 0) {
                                                        user.giveItem(itemId, playerHandler.getPlayer(playerId));
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

    public void duel (Player attacker, Player defender) {
        List<Player> players = new ArrayList<Player>() {{
                add(attacker);
                add(defender);
        }};
        combat(players, false);
    }

    public void arena(List<Player> players) {
        combat(players, true);
    }

    public void combat(List<Player> players, boolean arena) {
        PlayerHandler playerHandler = PlayerHandler.startHandler(players);
        boolean gameOver = false;
        Player user = playerHandler.current();
        Item prize = user.randomItem();

        System.out.println("\n\n! The prize is a " + prize.getName() + " !\n\n");
        gameOver = getReady(players, !arena);

        if (gameOver) {
            winner(playerHandler.getPlayers(), user, prize);
            playerHandler.close();
            return;
        }

        while (!gameOver) {
            if (playerHandler.getActive() == 1) {
                winner(players, user, user.randomItem());
                user.winTrophy();
                playerHandler.close();
                return;
            }

            System.out.println("\n\n" + user.getName() + "'s turn");
            System.out.println("1- Character Menu\n2- Basic Attack\n3- Use Spell");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    menu(user, false);
                    break;
                case "2":
                    basicAttack();
                    playerHandler.removeDeadPlayers();
                    break;
                case "3":
                    useSpell();
                    playerHandler.removeDeadPlayers();
                    break;
                default:
                    System.out.println("Please select an action.");
                    break;
            }
            user = playerHandler.current();
        }
    }

    private Action() {
        items = GlobalItems.addItems();
        spells = GlobalSpells.addSpells();
        skills = GlobalSkills.addSkills();
    }

    public static Action getAction() {
        if (action == null) {
            action = new Action();
        }
        return action;
    }
}