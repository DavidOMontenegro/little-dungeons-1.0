package spells;

public class Skin extends Spell {

    public Skin() {
        super("Reflective Skin",
                new String[] { "When an enemy hits you with a SAC or MAG attack, they take 6 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 12 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 18 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 24 damage",
                        "When an enemy hits you with a SAC or MAG attack, they take 30 damage" },
                new String[] { "Passive" }, 50, 0);
    }
}