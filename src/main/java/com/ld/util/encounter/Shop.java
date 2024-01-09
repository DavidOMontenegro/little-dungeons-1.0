package com.ld.util.encounter;

import com.ld.classes.Player;
import com.ld.global.GlobalItems;
import com.ld.global.GlobalScanner;
import com.ld.global.GlobalSkills;
import com.ld.global.GlobalSpells;
import com.ld.spells.Spell;
import com.ld.util.Item;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private Shop() {}

    private static List<Item> items = GlobalItems.addItems();
    private static List<Spell> spells = GlobalSpells.addSpells();
    private static List<Spell> skills = GlobalSkills.addSkills();

    public static void enter(Player player) {
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
}
