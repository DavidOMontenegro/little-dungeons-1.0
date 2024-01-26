package com.ld.util;

import com.ld.classes.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandler {
    static int start = 0;
    static List<Player> players;
    int current = 0;
    static PlayerHandler parent = null;
    static PlayerHandler mainHandler = null;
    int halt = 0;
    private PlayerHandler(List<Player> playerList, int start) {
        current = start;
        players = new ArrayList<>(playerList);
    }

    public void close() {
        mainHandler = parent;
    }

    public static PlayerHandler startHandler(List<Player> playerList) {
        parent = mainHandler;
        mainHandler = new PlayerHandler(playerList, start);
        start = 0;
        return mainHandler;
    }

    public static PlayerHandler getHandler() {
        return mainHandler;
    }

    public void next() {
        if (halt == 0) {
            current = current == players.size() - 1 ? 0 : ++current;
        } else {
            halt--;
        }

        if (parent != null) {
            wizard();
        }
    }

    public void halt() {
        halt++;
    }

    public void removeDeadPlayers() {
        List<Player> begin = new ArrayList<>(players);
        for (Player player : begin) {
            if (player.isDead()) {
                player.revive();
                players.remove(player);
                if (current > begin.indexOf(player)) {
                    current--;
                }
            }
        }

        int n = players.size();
        while (current >= n) {
            current -= n;
        }
    }

    public Player current() {
        return players.get(current);
    }

    public static void load(int n) {
        start = n;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int i){
        return players.get(i);
    }

    public Player getOnlyOption() {
        if (players.size() != 2) {
            return players.get(current);
        }
        int i = current == 0 ? 1 : 0;
        return players.get(i);
    }

    public int indexOf(Player player) {
        return players.indexOf(player);
    }

    public int getActive() {
        return players.size();
    }

    private void wizard() {
        Player player = current();
        if (player.getClassName().equals("Wizard")) {
            player.healMP(25);
        }

        player.clover();
    }
}
