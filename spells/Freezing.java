package spells;

public class Freezing extends Spell {

    public Freezing() {
        super("Freezing Attacks",
                new String[] { "When you use a basic attack, give your enemy 6 freeze",
                        "When you use a basic attack, give your enemy 12 freeze",
                        "When you use a basic attack, give your enemy 18 freeze",
                        "When you use a basic attack, give your enemy 24 freeze",
                        "When you use a basic attack, give your enemy 30 freeze" },
                new String[] { "Passive" }, 50, 0);
    }
}