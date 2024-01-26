package com.ld.util.encounter;

import com.ld.classes.Player;
import com.ld.global.GlobalScanner;
import com.ld.spells.Spell;
import com.ld.util.Item;
import com.ld.util.PlayerChooser;
import com.ld.util.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

public class Combat {
    private Combat() {}
    public static void duel (Player attacker, Player defender) {
        List<Player> players = new ArrayList<Player>() {{
            add(attacker);
            add(defender);
        }};
        combat(players, false);
    }

    public static void arena(List<Player> players) {
        combat(players, true);
    }

    private static void combat(List<Player> players, boolean arena) {
        PlayerHandler playerHandler = PlayerHandler.startHandler(players);
        boolean gameOver = false;
        Player user = playerHandler.current();
        Item prize = user.randomItem();

        System.out.println("\n\n! The prize is a " + prize.getName() + " !\n\n");
        gameOver = getReady(players, !arena);

        if (gameOver) {
            winner(playerHandler.getPlayers(), user, prize);
            playerHandler.close();
            return;
        }

        while (!gameOver) {
            if (playerHandler.getActive() == 1) {
                winner(players, user, user.randomItem());
                user.winTrophy();
                playerHandler.close();
                return;
            }

            System.out.println("\n\n" + user.getName() + "'s turn");
            System.out.println("1- Character Menu\n2- Basic Attack\n3- Use Spell");
            switch (GlobalScanner.nextLine()) {
                case "1":
                    Menu.open(user, false);
                    break;
                case "2":
                    basicAttack();
                    playerHandler.removeDeadPlayers();
                    break;
                case "3":
                    useSpell();
                    playerHandler.removeDeadPlayers();
                    break;
                default:
                    System.out.println("Please select an action.");
                    break;
            }
            user = playerHandler.current();
        }
    }

    private static void basicAttack() {
        boolean selected = false;
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        Player user = playerHandler.current();
        Player defender;

        while (true) {
            defender = PlayerChooser.choosePlayer("Which player will you attack?");
            if (defender.equals(null)) {
                return;
            }
            while (!selected) {
                System.out.println("1- Brute Attack\n2- Quick Attack\n3- Sacred Attack\n4- Magic Attack\n5- Exit");
                String atkType = GlobalScanner.nextLine();
                try {
                    int nature = Integer.parseInt(atkType);
                    if (nature > 0 && nature < 6) {
                        if (nature < 5) {
                            user.attack(nature, defender);
                            playerHandler.next();
                            return;
                        }
                        selected = true;
                    }
                } catch (Exception ignored) {
                }
            }
            selected = false;
        }
    }

    private static void useSpell() {
        PlayerHandler playerHandler = PlayerHandler.getHandler();
        boolean selected = false;
        Player user = playerHandler.current();

        while (!selected) {
            Spell[] userSpells = user.getSpells();
            user.seeSpells();
            System.out.println("7- Back");
            String spellId = GlobalScanner.nextLine();
            try {
                int id = Integer.parseInt(spellId);
                if (id > 0 && id < 8) {
                    if (id < 7) {
                        try {
                            Spell usedSpell = userSpells[--id];
                            if (usedSpell.getType().contains("Active")) {
                                usedSpell.use();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    selected = true;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static boolean getReady(List<Player> players, boolean arena) {
        boolean quit = false;

        System.out.println("Final Preparations");
        for (Player player : players) {
            for (Spell spell : player.getSpells()) {
                if (player.isSpell(spell, "Intimidating Presence")) {
                    for (Player enemy : players) {
                        if (enemy != player) {
                            enemy.effect("bruATK", -5 * spell.getLevel());
                            enemy.effect("quiATK", -5 * spell.getLevel());
                            enemy.effect("sacATK", -5 * spell.getLevel());
                            enemy.effect("magATK", -5 * spell.getLevel());
                        }
                    }
                }
            }
            player.alchemist(players, true);
        }
        for (int i = 0; i < players.size(); i++) {
            quit = Menu.open(players.get(i), i != 0 && !arena);
        }
        return quit;
    }

    private static void winner(List<Player> fighters, Player victor, Item item) {
        System.out.println(
                "\n--------------------------\n" + victor.getName() + " won the game!\n--------------------------");
        victor.getMoney(50);
        victor.getItem(item);
        for (Player player : fighters) {
            player.revive();
            for (Spell spell : player.getSpells()) {
                if (player.isSpell(spell, "Intimidating Presence")) {
                    for (Player enemy : fighters) {
                        if (enemy != player) {
                            enemy.effect("bruATK", 5 * spell.getLevel());
                            enemy.effect("quiATK", 5 * spell.getLevel());
                            enemy.effect("sacATK", 5 * spell.getLevel());
                            enemy.effect("magATK", 5 * spell.getLevel());
                        }
                    }
                }
            }
            player.alchemist(fighters, false);
        }
    }
}
