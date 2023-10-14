package util.encounter;

import util.tools.Plural;

public class State {
    private String name;
    private String msg;
    private String opt;

    State(String state, String message, String options) {
        name = state;
        msg = message;
        opt = options;
    }

    public String getState() {
        return name;
    }

    public void encounter(int item) {
        System.out.println(item == 0 ? "You got a" + Plural.plural(item) + "!" : msg);
    }

    public void startTurn() {
        System.out.println("What would you like to do?\n1- Character Menu\n" + opt);
    }
}
