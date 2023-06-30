import classes.*;
import util.Action;
import util.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class LittleDungeons {

    public static void startTurn(boolean shop, boolean duel, boolean arena) {
        if (shop) {
            System.out.println(
                    "What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player\n4- End Game");
        } else if (duel) {
            System.out.println("What would you like to do?\n1- Character Menu\n2- Fight Player\n3- End Game");
        } else if (arena) {
            System.out.println("What would you like to do?\n1- Character Menu\n2- Enter Arena\n3- End Game");
        } else {
            System.out.println("What would you like to do?\n1- Character Menu\n2- Next Player\n3- End Game");
        }
    }

    public static void gameOver(ArrayList<Player> players) {
        ArrayList<Player> winners = new ArrayList<>();
        int win = 1;
        winners.add(players.get(0));
        for (Player player : players) {
            if (player.getTrophies() > winners.get(0).getTrophies()) {
                winners.clear();
                winners.add(player);
            } else if (player.getTrophies() == winners.get(0).getTrophies()) {
                winners.add(player);
            }
        }
        win = winners.size();
        System.out.println("\n\n");
        if (win == 1) {
            System.out.println("The winner of this games was " + winners.get(0).getName() + ". Congratulations!");
        } else {
            System.out.print("The winners of this games were ");
            for (int i = 0; i < win; i++) {
                System.out.print(winners.get(i).getName() + (i < win - 1 ? ", " : ". Congratulations!\n"));
            }
        }
        System.out.println("\n\nGame over.");
    }

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        int current = 0;
        Player user;
        boolean gameOver = false;

        Scanner myScanner = new Scanner(System.in);

        byte playerNumber = 3;
        boolean selected = false;

        Action.addItems(items);

        // Start "Screen"
        System.out.println("Welcome to Little Terminal!\nHow many players will be playing this session?");
        while (!selected)
            switch (myScanner.nextLine()) {
                case "8":
                    playerNumber++;
                case "7":
                    playerNumber++;
                case "6":
                    playerNumber++;
                case "5":
                    playerNumber++;
                case "4":
                    playerNumber++;
                case "3":
                    selected = true;
                    break;
                default:
                    System.out.println("Please type a number between 3 and 8.");
                    break;
            }
        selected = false;

        // Class Selection
        for (byte i = 1; i != playerNumber + 1; i++) {
            System.out.println("What is player " + i + "'s name?");
            String name = myScanner.nextLine();
            System.out.println("And what is " + name
                    + "'s class?\n1- Barbarian\n2- Assassin\n3- Priest\n4- Wizard\n5- Archer\n6- Monk\n7- Dark Knight\n8- Paladin\n9- Warlock\n10- Captain\n11- Inquisitor\n12- Ninja\n13- Pyromancer\n14- Thief");
            while (!selected) {
                switch (myScanner.nextLine()) {
                    case "1":
                        players.add(new Barbarian(name));
                        selected = true;
                        break;
                    case "2":
                        players.add(new Assassin(name));
                        selected = true;
                        break;
                    case "3":
                        players.add(new Priest(name));
                        selected = true;
                        break;
                    case "4":
                        players.add(new Wizard(name));
                        selected = true;
                        break;
                    case "5":
                        players.add(new Archer(name));
                        selected = true;
                        break;
                    case "6":
                        players.add(new Monk(name));
                        selected = true;
                        break;
                    case "7":
                        players.add(new Knight(name));
                        selected = true;
                        break;
                    case "8":
                        players.add(new Paladin(name));
                        selected = true;
                        break;
                    case "9":
                        players.add(new Warlock(name));
                        selected = true;
                        break;
                    case "10":
                        players.add(new Captain(name));
                        selected = true;
                        break;
                    case "11":
                        players.add(new Inquisitor(name));
                        selected = true;
                        break;
                    case "12":
                        players.add(new Ninja(name));
                        selected = true;
                        break;
                    case "13":
                        players.add(new Pyromancer(name));
                        selected = true;
                        break;
                    case "14":
                        players.add(new Thief(name));
                        selected = true;
                        break;
                    default:
                        System.out.println("Please type one of the classes numbers.");
                        break;
                }
            }
            selected = false;
        }

        while (!selected) {
            System.out.println("You have chosen");
            for (byte i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                System.out.printf("%d- %s (%s)\n", i + 1, player.getName(), player.getClassName());
            }
            System.out
                    .println("If you would like to change one of the characters, type it's number. If not, type 'y'.");
            String choice = myScanner.nextLine();
            if (choice.equals("y")) {
                selected = true;
                break;
            } else {
                try {
                    byte id = Byte.parseByte(choice);
                    String name;
                    if (id > 0 && id <= playerNumber) {
                        System.out.println("What is player " + id + "'s name?");
                        name = myScanner.nextLine();
                        id--;
                        System.out.println("And what is " + name
                                + "'s class?\n1- Barbarian\n2- Assassin\n3- Priest\n4- Wizardn\n5- Archer\n6- Monk\n7- Dark Knight\n8- Paladin\n9- Warlock\n10- Captain\n11- Inquisitor\n12- Ninja\n13- Pyromancer\n14- Thief");
                        while (!selected) {
                            switch (myScanner.nextLine()) {
                                case "1":
                                    players.set(id, new Barbarian(name));
                                    selected = true;
                                    break;
                                case "2":
                                    players.set(id, new Assassin(name));
                                    selected = true;
                                    break;
                                case "3":
                                    players.set(id, new Priest(name));
                                    selected = true;
                                    break;
                                case "4":
                                    players.set(id, new Wizard(name));
                                    selected = true;
                                    break;
                                case "5":
                                    players.set(id, new Archer(name));
                                    selected = true;
                                    break;
                                case "6":
                                    players.set(id, new Monk(name));
                                    selected = true;
                                    break;
                                case "7":
                                    players.set(id, new Knight(name));
                                    selected = true;
                                    break;
                                case "8":
                                    players.set(id, new Paladin(name));
                                    selected = true;
                                    break;
                                case "9":
                                    players.set(id, new Warlock(name));
                                    selected = true;
                                    break;
                                case "10":
                                    players.set(id, new Captain(name));
                                    selected = true;
                                    break;
                                case "11":
                                    players.set(id, new Inquisitor(name));
                                    selected = true;
                                case "12":
                                    players.set(id, new Ninja(name));
                                    selected = true;
                                    break;
                                case "13":
                                    players.set(id, new Pyromancer(name));
                                    selected = true;
                                    break;
                                case "14":
                                    players.set(id, new Thief(name));
                                    selected = true;
                                    break;
                                default:
                                    System.out.println("Please type one of the classes numbers.");
                                    break;
                            }
                        }
                        selected = false;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        selected = false;
        Action action = new Action(players, myScanner);

        for (Player player : players) {
            System.out.println("\n\n" + player.getName() + "'s turn");
            System.out.println(
                    "What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
            while (!selected) {
                switch (myScanner.nextLine()) {
                    case "1":
                        action.menu(player, false);
                        System.out.println(
                                "What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
                        break;
                    case "2":
                        action.shop(player);
                        System.out.println(
                                "What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
                        break;
                    case "3":
                        selected = true;
                        break;
                    default:
                        System.out.println("Please select an action.");
                        break;
                }
            }
            selected = false;
        }

        while (!gameOver) {
            boolean shop = false;
            boolean duel = false;
            boolean arena = false;
            user = players.get(current);
            System.out.println("\n\n" + user.getName() + "'s turn");
            switch ((byte) (Math.random() * 4)) {
                case 0:
                    user.getItem(items, Math.random());
                    break;
                case 1:
                    shop = true;
                    System.out.println("You have found a small shop.");
                    break;
                case 2:
                    duel = true;
                    System.out.println("You must challenge another player to a duel.");
                    break;
                case 3:
                    arena = true;
                    System.out.println("You have found an arena.");
                    break;
            }
            startTurn(shop, duel, arena);
            while (!selected) {
                switch (myScanner.nextLine()) {
                    case "1":
                        action.menu(user, false);
                        startTurn(shop, duel, arena);
                        break;
                    case "2":
                        if (shop) {
                            action.shop(user);
                            startTurn(shop, duel, arena);
                            break;
                        } else if (duel) {
                            Player second;
                            while (!selected) {
                                for (int i = 0, j = 0; i < playerNumber; i++) {
                                    Player player = players.get(i);
                                    if (player == user) {
                                        continue;
                                    }
                                    j++;
                                    System.out.println(j + "- " + player.getName());
                                }
                                System.out.println((playerNumber) + "- Exit");
                                String id = myScanner.nextLine();
                                try {
                                    int playerId = Integer.parseInt(id);
                                    if (playerId <= players.indexOf(user) && playerId > 0) {
                                        second = players.get(playerId - 1);
                                        action.duel(user, second);
                                        selected = true;
                                    } else if (playerId < playerNumber && playerId > 0) {
                                        second = players.get(playerId);
                                        action.duel(user, second);
                                        selected = true;
                                    } else if (playerId == playerNumber) {
                                        selected = true;
                                        startTurn(shop, duel, arena);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                            break;
                        } else if (arena) {
                            action.arena(players, current);
                            selected = true;
                        } else {
                            selected = true;
                            break;
                        }
                        break;
                    case "3":
                        if (shop) {
                            selected = true;
                        } else {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (myScanner.nextLine().equals("agree")) {
                                gameOver(players);
                                return;
                            }
                            startTurn(shop, duel, arena);
                        }
                        break;
                    case "4":
                        if (shop) {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (myScanner.nextLine().equals("agree")) {
                                gameOver(players);
                                return;
                            }
                            startTurn(shop, duel, arena);
                        } else {
                            System.out.println("Please select an action.");
                        }
                        break;
                    default:
                        System.out.println("Please select an action.");
                        break;
                }
            }
            selected = false;

            current = Action.next(current, playerNumber);
        }

        myScanner.close();
    }
}