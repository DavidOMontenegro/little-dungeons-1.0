package com.ld.util.encounter.util;

import com.ld.util.tools.Articles;

public class State {
    private final String name;
    private final String msg;
    private final String opt;

    State(String state, String message, String options) {
        name = state;
        msg = message;
        opt = options;
    }

    public String getState() {
        return name;
    }

    public void encounter(int item) {
        System.out.println(item != 0 ? "You got a" + Articles.generate(item) + "!" : msg);
    }

    public void startTurn() {
        System.out.println("What would you like to do?\n1- Character Menu\n" + opt);
    }
}
