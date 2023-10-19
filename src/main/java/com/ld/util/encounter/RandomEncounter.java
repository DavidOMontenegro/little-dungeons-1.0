package com.ld.util.encounter;

import com.ld.classes.Player;

public class RandomEncounter {
    private static RandomEncounter singleton = null;
    private byte state;
    private int item;
    private final State[] encounters = new State[] {
        new State("item", "You got an item!", "2- Enter Shop\n3- Next Player\n4- Save Game\n5- End Game"),
        new State("shop", "You have found a small shop.", "2- Fight Player\n3- Save Game\n4- End Game"),
        new State("duel", "You must challenge another player to a duel.", "2- Enter Arena\n3- Save Game\n4- End Game"),
        new State("arena", "You have found an arena.", "2- Next Player\n3- Save Game\n4- End Game")
    };

    private RandomEncounter() {}

    public static RandomEncounter getRandomEncounter() {
        if (singleton == null) {
            singleton = new RandomEncounter();
        }
        return singleton;
    }

    public String getState() {
        return encounters[state].getState();
    }

    public byte saveState() {
        return state;
    }

    public void setState(byte arg) {
        state = arg;
    }

    public int saveItem() {
        return item;
    }

    public void setItem(int arg) {
        item = arg;
    }

    public void random(Player user) {
        item = 0;
        state = (byte) (Math.random() * 4);
        item = state == 0 ? user.getItem() : 0;
    }

    public void encounter() {
        encounters[state].encounter(item);
        startTurn();
    }

    public void startTurn() {
        encounters[state].startTurn();
    }
}
