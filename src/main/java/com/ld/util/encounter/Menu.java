package com.ld.util.encounter;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;
import com.ld.util.PlayerHandler;

public class Menu {
    private Menu() {}

    public static boolean open(Player user, boolean isDuel) {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        int playerNumber = playerHandler.getActive();
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
}
