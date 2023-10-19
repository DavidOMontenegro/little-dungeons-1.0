package com.ld.global;

import java.util.ArrayList;
import java.util.List;

import com.ld.spells.Armageddon;
import com.ld.spells.Fireball;
import com.ld.spells.Freeze;
import com.ld.spells.Healing;
import com.ld.spells.Icicles;
import com.ld.spells.Spell;
import com.ld.spells.Supernova;
import com.ld.spells.Tackle;
import com.ld.spells.Wrath;

public class GlobalSpells {
        private static final List<Spell> spells = new ArrayList<Spell>() {
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
