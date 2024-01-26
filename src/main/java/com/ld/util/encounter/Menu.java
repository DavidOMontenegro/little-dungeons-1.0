package com.ld.util.encounter;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;
import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;

public class Menu {

    private static Player user;

    private Menu() {}

    public static boolean open(Player player, boolean isDuel) {
        user = player;
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        isDuel = isDuel && user != playerHandler.getPlayer(0);

        user.seeStats();
        while (true) {
            System.out.println(
                    "\nTo open item menu, type 'i'.\nTo open spellbook, type 's'.\nTo give someone gold, type 'g'.\nTo exit character menu, type 'y'."
                            + (isDuel ? "\nTo concede this match, type 'q'." : ""));
            switch (GlobalScanner.nextLine()) {
                case "h":
                    selectEquippedItem(0);
                    break;
                case "b":
                    selectEquippedItem(1);
                    break;
                case "f":
                    selectEquippedItem(2);
                    break;
                case "m":
                    selectEquippedItem(3);
                    break;
                case "o":
                    selectEquippedItem(4);
                    break;
                case "i":
                    openInventory();
                    break;
                case "s":
                    openSpellbook();
                    break;
                case "g":
                    giveGold();
                    break;
                case "y":
                    return false;
                case "q":
                    if (isDuel) {
                        return true;
                    }
                default:
                    break;
            }
        }
    }

    private static void selectEquippedItem(int place) {
        boolean selected = false;
        while (true) {
            user.seeItem(place + 1);
            System.out.println("1- Equip an item on head\n2- Unequip current item\n3- Deselect item");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    while (!selected) {
                        int number = user.seeBackpack() + 1;
                        System.out.println(number + "- Exit");
                        String id = GlobalScanner.nextLine();
                        try {
                            int itemId = Integer.parseInt(id);
                            if (itemId < number && itemId > 0) {
                                if (user.putOn(itemId - 1, 0)) {
                                    user.seeStats();
                                    return;
                                }
                            } else if (itemId == number) {
                                selected = true;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected = false;
                    break;
                case "2":
                    user.takeOff(place);
                    user.seeStats();
                    return;
                case "3":
                    user.seeStats();
                    return;
                default:
                    break;
            }
        }
    }

    private static void openInventory() {
        int number = user.seeBackpack() + 1;

        System.out.println(number + "- Back");
        while (true) {
            String id = GlobalScanner.nextLine();
            try {
                int itemId = Integer.parseInt(id);
                if (itemId > 0 && itemId < number) {
                    user.seeItem(itemId + 5);
                    number = giveItem(itemId);
                } else if (itemId == number) {
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static void openSpellbook() {
        user.seeSpells();
        System.out.println("7- Back");
        while (true) {
            String id = GlobalScanner.nextLine();
            try {
                int spellId = Integer.parseInt(id);
                if (spellId > 0 && spellId < 7) {
                    user.seeSpell(spellId - 1, false);
                    System.out.println();
                    user.seeSpells();
                    System.out.println("7- Back");
                } else if (spellId == 7) {
                    return;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static int giveItem(int itemId) {
        int number;

        while (true) {
            System.out.println("1- Give Item to Another Player\n2- Deselect item");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    Player player = PlayerChooser.choosePlayer();
                    if (!player.equals(null)) {
                        user.giveItem(itemId, player);
                        number = user.seeBackpack() + 1;
                        System.out.println(number + "- Back");
                    }
                    break;
                case "2":
                    number = user.seeBackpack() + 1;
                    System.out.println(number + "- Back");
                    return number;
                default:
                    break;
            }
        }
    }

    private static void giveGold() {
        Player player = PlayerChooser.choosePlayer("You have " + user.seeGold() + " gold.\nWho will you give gold to?");
        if (!player.equals(null)) {
            while (true) {
                System.out.println("How much gold will you give?");
                String id = GlobalScanner.nextLine();
                try {
                    int gold = Integer.parseInt(id);
                    if (gold >= 0) {
                        if (gold > 0) {
                            user.giveMoney(gold, player);
                        }
                        user.seeStats();
                        return;
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
