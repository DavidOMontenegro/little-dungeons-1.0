package com.ld.configurations;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;
import com.ld.util.PlayerHandler;
import com.ld.util.encounter.util.RandomEncounter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {

    private GameSaver() {}

    public static void saveGame(File folder, PlayerHandler playerHandler, RandomEncounter state)
            throws IOException {
        boolean selected = false;
        boolean newFile = false;
        File saved = new File("saved/error.json");
        File[] listOfFiles = folder.listFiles();

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
}
