package com.ld;

import com.ld.classes.Player;
import com.ld.classes.factory.PlayerFactory;
import com.ld.global.GlobalScanner;
import com.ld.util.Action;
import com.ld.util.PlayerHandler;
import com.ld.util.encounter.RandomEncounter;

import java.io.File;
import java.io.FileWriter;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LittleDungeons {

    private static void saveGame(File[] listOfFiles, PlayerHandler playerHandler, RandomEncounter state)
            throws IOException {
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
                String id = GlobalScanner.nextLine();
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
                fileName = GlobalScanner.nextLine();
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
        for (Player player : playerHandler.getPlayers()) {
            pcs.put(player.save());
        }
        game.put("current", playerHandler.indexOf(playerHandler.current()));
        game.put("state", state.saveState());
        game.put("item", state.saveItem());
        game.put("players", pcs);

        FileWriter file = new FileWriter(saved);
        file.write(game.toString());
        file.close();
        System.out.println();
    }

    private static void gameOver(List<Player> players) {
        List<Player> winners = new ArrayList<>();
        int win;
        winners.add(players.get(0));
        for (Player player : players) {
            if (player.getTrophies() > winners.get(0).getTrophies()) {
                winners.clear();
                winners.add(player);
            } else if (player.getTrophies() == winners.get(0).getTrophies())
                winners.add(player);

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
        List<Player> players = new ArrayList<>();
        Player user;
        boolean gameOver = false;
        RandomEncounter state = RandomEncounter.getRandomEncounter();
        PlayerFactory playerFactory = new PlayerFactory();

        byte playerNumber = 3;
        boolean selected = false;
        boolean save = false;

        // Start "Screen"
        System.out.println("\n\nWelcome to Little Terminal!");
        File folder = new File("src/main/resources/persistence/save");
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
                String id = GlobalScanner.nextLine();
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

                            PlayerHandler.load((int) load.get("current"));
                            state.setState((byte) load.get("state"));
                            state.setItem((int) load.get("item"));

                            JSONArray jsonPlayers = (JSONArray) load.get("players");
                            for (Object playerString : jsonPlayers) {
                                JSONObject player = new JSONObject(playerString.toString());
                                players.add(playerFactory.JSONPlayer(player));
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
                switch (GlobalScanner.nextLine()) {
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
                String name = GlobalScanner.nextLine();
                System.out.println("And what is " + name
                        + "'s class?\n1- Barbarian\n2- Assassin\n3- Priest\n4- Wizard\n5- Archer\n6- Monk\n7- Dark Knight\n8- Paladin\n9- Warlock\n10- Captain\n11- Inquisitor\n12- Ninja\n13- Pyromancer\n14- Thief");
                while (!selected) {
                    try {
                        int classId = Integer.parseInt(GlobalScanner.nextLine());
                        players.add(playerFactory.newPlayer(classId, name));
                        selected = true;
                    } catch (Exception ignored) {
                        System.out.println("Please type one of the classes numbers.");
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
                String choice = GlobalScanner.nextLine();
                if (choice.equals("y")) {
                    selected = true;
                    break;
                } else {
                    try {
                        byte id = Byte.parseByte(choice);
                        String name;
                        if (id > 0 && id <= playerNumber) {
                            System.out.println("What is player " + id-- + "'s name?");
                            name = GlobalScanner.nextLine();
                            System.out.println("And what is " + name
                                    + "'s class?\n1- Barbarian\n2- Assassin\n3- Priest\n4- Wizard\n5- Archer\n6- Monk\n7- Dark Knight\n8- Paladin\n9- Warlock\n10- Captain\n11- Inquisitor\n12- Ninja\n13- Pyromancer\n14- Thief");
                            while (!selected) {
                                try {
                                    int classId = Integer.parseInt(GlobalScanner.nextLine());
                                    players.set(id, playerFactory.newPlayer(classId, name));
                                    selected = true;
                                } catch (Exception ignored) {
                                    System.out.println("Please type one of the classes numbers.");
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
        Action action = Action.getAction();
        PlayerHandler playerHandler = PlayerHandler.startHandler(players);

        if (save) {
            for (Player player : players) {
                System.out.println("\n\n" + player.getName() + "'s turn");
                System.out.println("What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
                while (!selected) {
                    switch (GlobalScanner.nextLine()) {
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
            user = playerHandler.current();
            System.out.println("\n\n" + user.getName() + "'s turn");
            if (save) {
                state.random(user);
            } else {
                save = true;
            }
            state.encounter();

            while (!selected) {
                switch (GlobalScanner.nextLine()) {
                    case "1":
                        action.menu(user, false);
                        state.startTurn();
                        break;
                    case "2":
                        switch (state.getState()) {
                            case "shop":
                                action.shop(user);
                                state.startTurn();
                                break;
                            case "duel":
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
                                    String id = GlobalScanner.nextLine();
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
                                            state.startTurn();
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }
                                break;
                            case "arena":
                                action.arena(players);
                                selected = true;
                                break;
                            default:
                                selected = true;
                                break;
                        }
                        break;
                    case "3":
                        if (state.getState().equals("shop")) {
                            selected = true;
                        } else {
                            saveGame(listOfFiles, playerHandler, state);
                            listOfFiles = folder.listFiles();
                            state.startTurn();
                        }
                        break;
                    case "4":
                        if (state.getState().equals("shop")) {
                            saveGame(listOfFiles, playerHandler, state);
                            listOfFiles = folder.listFiles();
                            state.startTurn();
                        } else {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (GlobalScanner.nextLine().equals("agree")) {
                                gameOver(players);
                                return;
                            }
                            state.startTurn();
                        }
                        break;
                    case "5":
                        if (state.getState().equals("shop")) {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (GlobalScanner.nextLine().equals("agree")) {
                                gameOver(players);
                                return;
                            }
                            state.startTurn();
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

            playerHandler.next();
        }

        GlobalScanner.close();
    }
}