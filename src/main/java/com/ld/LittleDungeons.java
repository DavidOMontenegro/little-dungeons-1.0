package com.ld;

import com.ld.classes.Player;
import com.ld.classes.factory.PlayerFactory;
import com.ld.configurations.GameEnder;
import com.ld.configurations.GameSaver;
import com.ld.configurations.GameStarter;
import com.ld.configurations.JSONLoader;
import com.ld.global.GlobalScanner;
import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;
import com.ld.util.encounter.Combat;
import com.ld.util.encounter.Menu;
import com.ld.util.encounter.Shop;
import com.ld.util.encounter.util.RandomEncounter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LittleDungeons {

    public static void main(String[] args) throws IOException {
        Player user;
        List<Player> players = new ArrayList<>();
        RandomEncounter state = RandomEncounter.getRandomEncounter();
        PlayerFactory playerFactory = new PlayerFactory();
        File folder = new File("src/main/resources/persistence/save");

        boolean selected = false;
        boolean newGame;

        System.out.println("\n\nWelcome to Little Terminal!");
        newGame = JSONLoader.chooseBackup(folder, players, playerFactory);

        if (newGame) {
            GameStarter.chooseCharacters(players, playerFactory);

            for (Player player : players) {
                System.out.println("\n\n" + player.getName() + "'s turn");
                System.out.println("What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
                while (!selected) {
                    switch (GlobalScanner.nextLine()) {
                        case "1":
                            Menu.open(player, false);
                            System.out.println(
                                    "What would you like to do?\n1- Character Menu\n2- Enter Shop\n3- Next Player");
                            break;
                        case "2":
                            Shop.enter(player);
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
        PlayerHandler playerHandler = PlayerHandler.getHandler();

        while (true) {
            user = playerHandler.current();
            System.out.println("\n\n" + user.getName() + "'s turn");
            if (newGame) {
                state.random(user);
            } else {
                newGame = true;
            }
            state.encounter();

            while (!selected) {
                switch (GlobalScanner.nextLine()) {
                    case "1":
                        Menu.open(user, false);
                        state.startTurn();
                        break;
                    case "2":
                        switch (state.getState()) {
                            case "shop":
                                Shop.enter(user);
                                state.startTurn();
                                break;
                            case "duel":
                                Player second = PlayerChooser.choosePlayer("Who will you challenge?");
                                if (!second.equals(null)) {
                                    Combat.duel(user, second);
                                }
                                state.startTurn();
                                break;
                            case "arena":
                                Combat.arena(players);
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
                            GameSaver.saveGame(folder, playerHandler, state);
                            state.startTurn();
                        }
                        break;
                    case "4":
                        if (state.getState().equals("shop")) {
                            GameSaver.saveGame(folder, playerHandler, state);
                            state.startTurn();
                        } else {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (GlobalScanner.nextLine().equals("agree")) {
                                GameEnder.gameOver(players);
                                return;
                            }
                            state.startTurn();
                        }
                        break;
                    case "5":
                        if (state.getState().equals("shop")) {
                            System.out.println("Do all players agree to end the game now? If so, type 'agree'.");
                            if (GlobalScanner.nextLine().equals("agree")) {
                                GameEnder.gameOver(players);
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
    }
}