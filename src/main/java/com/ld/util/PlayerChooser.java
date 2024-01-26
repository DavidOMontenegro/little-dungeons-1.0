package com.ld.util;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;

public class PlayerChooser {

    private PlayerChooser() {}

    public static Player choosePlayer(String message) {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        int playerNumber = playerHandler.getActive();

        if (playerNumber == 2) {
            return playerHandler.getOnlyOption();
        }

        while (true) {
            if (!message.equals(null)) {
                System.out.println(message);
            }

            for (int i = 0, j = 0; i < playerNumber; i++) {
                Player player = playerHandler.getPlayer(i);
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
                if (playerId <= playerHandler.indexOf(user) && playerId > 0) {
                    return playerHandler.getPlayer(--playerId);
                } else if (playerId < playerNumber && playerId > 0) {
                    return playerHandler.getPlayer(playerId);
                } else if (playerId == playerNumber) {
                    return null;
                }
            } catch (Exception ignored) {
            }
        }
    }

    public static Player choosePlayer() {
        return choosePlayer(null);
    }
}
