package spells;

public class Blessing extends Spell {

    public Blessing() {
        super("Sorcerer's Blessing",
                new String[] { "When you attack, recover 6 MP", "When you attack, recover 12 MP",
                        "When you attack, recover 18 MP", "When you attack, recover 24 MP",
                        "When you attack, recover 30 MP" },
                new String[] { "Passive" }, 50, 0);
    }
}