package spells;

public class Heart extends Spell {

    public Heart() {
        super("Heart of Stone",
                new String[] { "Receive 6 less damage", "Receive 12 less damage", "Receive 18 less damage",
                        "Receive 24 less damage", "Receive 30 less damage" },
                new String[] { "Passive" }, 50, 0);
    }
}