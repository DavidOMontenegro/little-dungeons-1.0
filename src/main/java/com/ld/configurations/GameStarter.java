package com.ld.configurations;

import com.ld.classes.Player;
import com.ld.classes.factory.PlayerFactory;
import com.ld.global.GlobalScanner;
import com.ld.util.PlayerHandler;

import java.util.List;

public class GameStarter {

    private GameStarter() {}

    public static void chooseCharacters(List<Player> players, PlayerFactory playerFactory) {
        int playerNumber = 3;
        boolean selected = false;

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
//                case "1":
//                    playerNumber = 1;
//                    break;
                default:
                    System.out.println("Please type a number between 3 and 8.");
                    break;
            }

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

        PlayerHandler.startHandler(players);
    }
}
