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
    private static List<Item> shopItems = new ArrayList<>();
    private static List<Spell> shopSpells = new ArrayList<>();

    private static Player user;
    private static int j = 1;

    public static void enter(Player player) {
        boolean finished = false;
        user = player;

        while (!finished) {
            System.out.println(
                    "\nWhat would you like to do in the shop?\n1- Browse Items\n2- See Spells\n3- Sell Items\n4- Exit Shop");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    browseItems();
                case "2":
                    seeSpells();
                    break;
                case "3":
                    sellItems();
                    break;
                case "4":
                    finished = true;
                    break;
                default:
                    break;
            }
        }
    }

    private static void browseItems() {
        while (true) {
            System.out.println("\n1- Head\n2- Body\n3- Feet\n4- Weapon\n5- Shield\n6- Amulet\n7- Exit");
            shopItems.clear();
            switch (GlobalScanner.nextLine()) {
                case "1":
                    chooseItem("Head");
                    break;
                case "2":
                    chooseItem("Body");
                    break;
                case "3":
                    chooseItem("Feet");
                    break;
                case "4":
                    chooseItem("Weapon");
                    break;
                case "5":
                    chooseItem("Shield");
                    break;
                case "6":
                    chooseItem("Amulet");
                    break;
                case "7":
                    return;
                default:
                    break;
            }
        }
    }

    private static void listItems(String type) {
        for (int i = 0; i < user.getRank(); i++) {
            Item item = items.get(i);
            if (item.getType().contains(type)) {
                shopItems.add(item);
                System.out.println(j + "- " + item.getName() + item.getPrice());
                j++;
            }
        }
        System.out.println(j + "- Go Back");
    }

    private static void chooseItem(String type) {
        while (true) {
            listItems(type);
            String id = GlobalScanner.nextLine();
            try {
                int itemId = Integer.parseInt(id);
                if (itemId < j && itemId > 0) {
                    Item selectedItem = shopItems.get(itemId - 1);
                    selectedItem.seeItem();
                    buyItem(selectedItem, "1- Buy\n2- Go Back");
                }
                if (itemId == j) {
                    j = 1;
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static void buyItem(Item item, String msg) {
        while (true) {
            System.out.println(msg);
            switch (GlobalScanner.nextLine()) {
                case "1":
                    user.buyItem(item);
                case "2":
                    return;
                default:
                    break;
            }
        }
    }

    private static void seeSpells() {
        while (true) {
            shopSpells.clear();
            System.out.println("\n1- Active\n2- Passive\n3- Exit");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    chooseSpell(spells);
                    j = 1;
                    break;
                case "2":
                    chooseSpell(skills);
                    j = 1;
                    break;
                case "3":
                    return;
                default:
                    break;
            }
        }
    }

    private static void listSpells(List<Spell> type) {
        for (int i = 0; i < type.size(); i++) {
            Spell spell = type.get(i);
            j = 0;
            for (Spell[] playerSpells = user.getSpells(); j < playerSpells.length; j++) {
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
    }

    private static void chooseSpell(List<Spell> type) {
        while (true) {
            listSpells(type);
            String id = GlobalScanner.nextLine();
            try {
                int spellId = Integer.parseInt(id);
                if (spellId < j && spellId > 0) {
                    Spell selectedSpell = shopSpells.get(spellId - 1);
                    int level = selectedSpell.getLevel();
                    selectedSpell.seeSpell(true, user);
                    switch (level) {
                        case 0:
                            upgradeSpell(selectedSpell, "1- Buy\n2- Go Back");
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            upgradeSpell(selectedSpell, "1- Upgrade\n2- Go Back");
                            break;
                        case 5:
                            System.out.println(
                                    "This spell is already max level.\n\nPress any key to go back.");
                            GlobalScanner.nextLine();
                            break;
                    }
                }
                if (spellId == j) {
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static void upgradeSpell(Spell spell, String msg) {
        while (true) {
            System.out.println(msg);
            switch (GlobalScanner.nextLine()) {
                case "1":
                    user.buySpell(spell);
                    spells = GlobalSpells.addSpells();
                    skills = GlobalSkills.addSkills();
                case "2":
                    return;
                default:
                    break;
            }
        }
    }

    private static void sellItems() {
        boolean selected = false;

        while (true) {
            shopItems.clear();
            List<Item> backpack = user.getBackpack();
            int shopSize = backpack.size();

            if (shopSize == 0) {
                System.out.println("You have no items in your backpack.");
                return;
            }

            for (int i = 0; i < shopSize; i++) {
                Item item = backpack.get(i);
                shopItems.add(item);
                System.out.println((i + 1) + "- " + item.getName() + item.getPrice());
            }
            System.out.println((shopSize + 1) + "- Go Back");
            System.out.println("\nRemember that when selling items, they go for half the above shown price.");

            String id = GlobalScanner.nextLine();
            try {
                int itemId = Integer.parseInt(id);
                if (itemId < shopSize + 1 && itemId > 0) {
                    Item selectedItem = shopItems.get(itemId - 1);
                    selectedItem.seeItem();
                    System.out.println("1- Sell\n2- Go Back");
                    while (!selected) {
                        switch (GlobalScanner.nextLine()) {
                            case "1":
                                user.sellItem(selectedItem);
                                selected = true;
                                break;
                            case "2":
                                selected = true;
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (itemId == shopSize + 1) {
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }
}
