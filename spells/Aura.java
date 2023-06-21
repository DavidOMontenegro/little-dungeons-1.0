package spells;

public class Aura extends Spell {

    public Aura() {
        super("Vampiric Aura",
                new String[] { "When you attack, heal 6 HP", "When you attack, heal 12 HP",
                        "When you attack, heal 18 HP", "When you attack, heal 24 HP", "When you attack, heal 30 HP" },
                new String[] { "Passive" }, 50, 0);
    }
}