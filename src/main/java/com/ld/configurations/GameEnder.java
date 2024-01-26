package com.ld.configurations;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;

import java.util.ArrayList;
import java.util.List;

public class GameEnder {

    private GameEnder() {}

    public static void gameOver(List<Player> players) {
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

        GlobalScanner.close();
    }
}
