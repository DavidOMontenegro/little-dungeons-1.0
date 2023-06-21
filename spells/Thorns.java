package spells;

public class Thorns extends Spell {

    public Thorns() {
        super("Thorns",
                new String[] { "When an enemy hits you with a BRU or QUI attack, they take 6 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 12 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 18 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 24 damage",
                        "When an enemy hits you with a BRU or QUI attack, they take 30 damage" },
                new String[] { "Passive" }, 50, 0);
    }
}