package com.ld.configurations;

import com.ld.classes.Player;
import com.ld.classes.factory.PlayerFactory;
import com.ld.global.GlobalScanner;
import com.ld.util.PlayerHandler;
import com.ld.util.encounter.util.RandomEncounter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class JSONLoader {

    private JSONLoader() {}

    public static boolean chooseBackup(File folder, List<Player> players, PlayerFactory playerFactory) {
        boolean selected = false;
        boolean save = false;
        RandomEncounter state = RandomEncounter.getRandomEncounter();
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
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
                            PlayerHandler.startHandler(players);
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

        return save;
    }
}
