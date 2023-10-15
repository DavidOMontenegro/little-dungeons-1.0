package global;

import java.util.ArrayList;
import java.util.List;

import spells.Armageddon;
import spells.Fireball;
import spells.Freeze;
import spells.Healing;
import spells.Icicles;
import spells.Spell;
import spells.Supernova;
import spells.Tackle;
import spells.Wrath;

public class GlobalSpells {
        private static List<Spell> spells = new ArrayList<Spell>() {
                {
                        add(new Fireball());
                        add(new Freeze());
                        add(new Healing());
                        add(new Tackle());
                        add(new Supernova());
                        add(new Armageddon());
                        add(new Icicles());
                        add(new Wrath());
                }
        };

        private GlobalSpells() {
        }

        public static List<Spell> addSpells() {
                return spells;
        }
}
