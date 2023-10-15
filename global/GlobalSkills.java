package global;

import java.util.ArrayList;
import java.util.List;

import spells.Spell;

public class GlobalSkills {
    private static List<Spell> skills = new ArrayList<Spell>() {
        {
            add(new Spell("Thorns",
                    new String[] { "When an enemy hits you with a BRU or QUI attack, they take 6 damage",
                            "When an enemy hits you with a BRU or QUI attack, they take 12 damage",
                            "When an enemy hits you with a BRU or QUI attack, they take 18 damage",
                            "When an enemy hits you with a BRU or QUI attack, they take 24 damage",
                            "When an enemy hits you with a BRU or QUI attack, they take 30 damage" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Reflective Skin",
                    new String[] { "When an enemy hits you with a SAC or MAG attack, they take 6 damage",
                            "When an enemy hits you with a SAC or MAG attack, they take 12 damage",
                            "When an enemy hits you with a SAC or MAG attack, they take 18 damage",
                            "When an enemy hits you with a SAC or MAG attack, they take 24 damage",
                            "When an enemy hits you with a SAC or MAG attack, they take 30 damage" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Vampiric Aura",
                    new String[] { "When you attack, heal 6 HP",
                            "When you attack, heal 12 HP",
                            "When you attack, heal 18 HP",
                            "When you attack, heal 24 HP",
                            "When you attack, heal 30 HP" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Sorcerer's Blessing",
                    new String[] { "When you attack, recover 6 MP",
                            "When you attack, recover 12 MP",
                            "When you attack, recover 18 MP",
                            "When you attack, recover 24 MP",
                            "When you attack, recover 30 MP" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Heart of Stone",
                    new String[] { "Receive 6 less damage",
                            "Receive 12 less damage",
                            "Receive 18 less damage",
                            "Receive 24 less damage",
                            "Receive 30 less damage" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Intimidating Presence",
                    new String[] { "Other players have -5 attack",
                            "Other players have -10 attack",
                            "Other players have -15 attack",
                            "Other players have -20 attack",
                            "Other players have -25 attack" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Plunder",
                    new String[] { "Get 10 times your level in gold for every player you kill",
                            "Get 20 times your level in gold for every player you kill",
                            "Get 30 times your level in gold for every player you kill",
                            "Get 40 times your level in gold for every player you kill",
                            "Get 50 times your level in gold for every player you kill" },
                    new String[] { "Passive" }, 50, 0));

            add(new Spell("Freezing Attacks",
                    new String[] { "When you use a basic attack, give your enemy 6 freeze",
                            "When you use a basic attack, give your enemy 12 freeze",
                            "When you use a basic attack, give your enemy 18 freeze",
                            "When you use a basic attack, give your enemy 24 freeze",
                            "When you use a basic attack, give your enemy 30 freeze" },
                    new String[] { "Passive" }, 50, 0));
        }
    };

    private GlobalSkills() {
    }

    public static List<Spell> addSkills() {
        return skills;
    }
}
