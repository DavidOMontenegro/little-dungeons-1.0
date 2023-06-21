package spells;

public class Plunder extends Spell {

    public Plunder() {
        super("Plunder",
                new String[] { "Get 10 times your level in gold for every player you kill",
                        "Get 20 times your level in gold for every player you kill",
                        "Get 30 times your level in gold for every player you kill",
                        "Get 40 times your level in gold for every player you kill",
                        "Get 50 times your level in gold for every player you kill" },
                new String[] { "Passive" }, 50, 0);
    }
}