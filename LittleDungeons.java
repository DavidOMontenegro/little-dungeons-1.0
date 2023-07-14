import classes.*;
import util.Action;
import util.Item;

import java.io.File;
import java.io.FileWriter;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LittleDungeons {

    private static void startTurn(boolean shop, boolean duel, boolean arena) {
        if (shop) {
            System.out.println(
                    "What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player\n4- Save Game\n5- End Game");
        } else if (duel) {
            System.out.println(
                    "What would you like to do?\n1- Character Menu\n2- Fight Player\n3- Save Game\n4- End Game");
        } else if (arena) {
            System.out.println(
                    "What would you like to do?\n1- Character Menu\n2- Enter Arena\n3- Save Game\n4- End Game");
        } else {
            System.out.println(
                    "What would you like to do?\n1- Character Menu\n2- Next Player\n3- Save Game\n4- End Game");
        }
    }

    private static void saveGame(Scanner scan, File[] listOfFiles, ArrayList<Player> players, int current,
            ArrayList<Item> items, boolean shop, boolean duel, boolean arena, int item) throws IOException {
        boolean selected = false;
        boolean newFile = false;
        File saved = new File("saved/error.json");

        if (listOfFiles.length > 0) {
            System.out.println("\nDo you wish to overwrite a previous save file or create a new one?");
            int files = listOfFiles.length + 1;
            for (int i = 0; i < files - 1; i++) {
                String saveName = listOfFiles[i].getName();
                if (listOfFiles[i].isFile()) {
                    System.out.println((i + 1) + "- " + saveName.substring(0, saveName.length() - 5));
                }
            }
            System.out.println(files + "- New File");
            while (!selected) {
                String id = scan.nextLine();
                try {
                    int saveFile = Integer.parseInt(id);
                    if (saveFile > 0 && saveFile < files) {
                        saved = listOfFiles[saveFile - 1];
                        saved.delete();
                        selected = true;
                    } else if (saveFile == files) {
                        newFile = true;
                        selected = true;
                    }
                } catch (Exception ignored) {
                }
            }
            selected = false;
        } else {
            newFile = true;
        }

        if (newFile) {
            String fileName = "error";
            System.out.println("What will you name this new save file?");
            while (!selected) {
                fileName = scan.nextLine();
                if (fileName != null) {
                    if (!fileName.contains(" ") && !fileName.contains(".")) {
                        selected = true;
                    } else {
                        System.out.println("Don't use spaces or periods.");
                    }
                } else {
                    System.out.println("Enter a name.");
                }
            }
            selected = false;

            saved = new File("./save/" + fileName + ".json");
        }

        saved.createNewFile();
        JSONObject game = new JSONObject();
        JSONArray pcs = new JSONArray();
        for (Player player : players) {
            pcs.put(player.save());
        }
        game.put("current", current);
        game.put("shop", shop);
        game.put("duel", duel);
        game.put("arena", arena);
        game.put("players", pcs);
        game.put("item", item);

        FileWriter file = new FileWriter(saved);
        file.write(game.toString());
        file.close();
        System.out.println();
    }

    private static void gameOver(ArrayList<Player> players) {
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
            System.out.println("The winner of this game was " + winners.get(0).getName() + ". Congratulations!");
        } else {
            System.out.print("The winners of this game were ");
            for (int i = 0; i < win; i++) {
                System.out.print(winners.get(i).getName()
                        + (i < win - 1 ? (i < win - 2 ? ", " : " and ") : ". Congratulations!\n"));
            }
        }
        System.out.println("\n\nGame over.");
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        int current = 0;
        Player user;
        boolean gameOver = false;
        boolean shop = false;
        boolean duel = false;
        boolean arena = false;
        int item = 0;

        Scanner myScanner = new Scanner(System.in);

        byte playerNumber = 3;
        boolean selected = false;
        boolean save = false;

        Action.addItems(items);

        // Start "Screen"
        System.out.println("\n\nWelcome to Little Terminal!");
        File folder = new File("./save");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles.length > 0) {
            int files = listOfFiles.length + 1;
            System.out.println(
                    "Select a save file. Type it's number to load it, or d plus it's number to delete it. (Example: d2)\n");
            for (int i = 0; i < files - 1; i++) {
                String saveName = listOfFiles[i].getName();
                if (listOfFiles[i].isFile()) {
                    System.out.println((i + 1) + "- " + saveName.substring(0, saveName.length() - 5));
                }
            }
            System.out.println(files + "- New Game");
            while (!selected) {
                String id = myScanner.nextLine();
                if (id.startsWith("d")) {
                    try {
                        int deleteFile = Integer.parseInt(id.substring(1));
                        File df = new File(listOfFiles[deleteFile - 1].getPath());
                        df.delete();
                        listOfFiles = folder.listFiles();
                        files = listOfFiles.length + 1;
                        System.out.println(
                                "\nSelect a save file. Type it's number to load it, or d plus it's number to delete it. (Example: d2)\n");
                        for (int i = 0; i < files - 1; i++) {
                            String saveName = listOfFiles[i].getName();
                            if (listOfFiles[i].isFile()) {
                                System.out.println((i + 1) + "- " + saveName.substring(0, saveName.length() - 5));
                            }
                        }
                        System.out.println(files + "- New Game");
                    } catch (Exception ignored) {
                    }
                } else {
                    try {
                        int saveFile = Integer.parseInt(id);
                        if (saveFile > 0 && saveFile < files) {
                            File loadFile = new File(listOfFiles[saveFile - 1].getPath());
                            Scanner loader = new Scanner(loadFile);
                            JSONObject load = new JSONObject(loader.nextLine());
                            loader.close();

                            current = (int) load.get("current");
                            shop = (boolean) load.get("shop");
                            duel = (boolean) load.get("duel");
                            arena = (boolean) load.get("arena");
                            item = (int) load.get("item");

                            JSONArray jsonPlayers = (JSONArray) load.get("players");
                            for (Object playerString : jsonPlayers) {
                                JSONObject player = new JSONObject(playerString.toString());
                                switch ((String) player.get("class")) {
                                    case "Barbarian":
                                        players.add(new Barbarian(player, items));
                                        break;
                                    case "Assassin":
                                        players.add(new Assassin(player, items));
                                        break;
                                    case "Priest":
                                        players.add(new Priest(player, items));
                                        break;
                                    case "Wizard":
                                        players.add(new Wizard(player, items));
                                        break;
                                    case "Archer":
                                        players.add(new Archer(player, items));
                                        break;
                                    case "Monk":
                                        players.add(new Monk(player, items));
                                        break;
                                    case "Dark Knight":
                                        players.add(new Knight(player, items));
                                        break;
                                    case "Paladin":
                                        players.add(new Paladin(player, items));
                                        break;
                                    case "Warlock":
                                        players.add(new Warlock(player, items));
                                        break;
                                    case "Captain":
                                        players.add(new Captain(player, items));
                                        break;
                                    case "Inquisitor":
                                        players.add(new Inquisitor(player, items));
                                        break;
                                    case "Ninja":
                                        players.add(new Ninja(player, items));
                                        break;
                                    case "Pyromancer":
                                        players.add(new Pyromancer(player, items));
                                        break;
                                    case "Thief":
                                        players.add(new Thief(player, items));
                                        break;
                                }
                            }
                            selected = true;
                        } else if (saveFile == files) {
                            save = true;
                            selected = true;
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
            selected = false;
        } else {
            save = true;
        }

        if (save) {
            // Select number of players
            System.out.println("\nHow many players will be playing this session?");
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
                        .println(
                                "If you would like to change one of the characters, type it's number. If not, type 'y'.");
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
        }
        Action action = new Action(players, myScanner);

        if (save) {
            for (Player player : players) {
                System.out.println("\n\n" + player.getName() + "'s turn");
                System.out.println("What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
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
        }

        while (!gameOver) {
            user = players.get(current);
            System.out.println("\n\n" + user.getName() + "'s turn");
            if (save) {
                switch ((byte) (Math.random() * 4)) {
                    case 0:
                        item = user.getItem(items, Math.random());
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
            } else {
                if (shop) {
                    System.out.println("You have found a small shop.");
                } else if (duel) {
                    System.out.println("You must challenge another player to a duel.");
                } else if (arena) {
                    System.out.println("You have found an arena.");
                } else {
                    Item found = null;
                    for (Item tmp : items) {
                        if (tmp.isItem(item)) {
                            found = tmp;
                            break;
                        }
                    }
                    System.out.println("You got a" + ("AEIOU".indexOf(found.getName().charAt(0)) != -1 ? "n " : " ")
                            + found.getName() + "!");
                }
                item = 0;
                save = true;
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
                            saveGame(myScanner, listOfFiles, players, current, items, shop, duel, arena, item);
                            listOfFiles = folder.listFiles();
                            startTurn(shop, duel, arena);
                        }
                        break;
                    case "4":
                        if (shop) {
                            saveGame(myScanner, listOfFiles, players, current, items, shop, duel, arena, item);
                            listOfFiles = folder.listFiles();
                            startTurn(shop, duel, arena);
                        } else {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (myScanner.nextLine().equals("agree")) {
                                gameOver(players);
                                return;
                            }
                            startTurn(shop, duel, arena);
                        }
                        break;
                    case "5":
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