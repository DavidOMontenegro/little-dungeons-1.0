package spells;

public class Presence extends Spell {

    public Presence() {
        super("Intimidating Presence",
                new String[] { "Other players have -5 attack", "Other players have -10 attack",
                        "Other players have -15 attack", "Other players have -20 attack",
                        "Other players have -25 attack" },
                new String[] { "Passive" }, 50, 0);
    }
}