package classes;

public class Paladin extends Player {
    public Paladin(String name) {
        super(name, 60, 30, 1, 0, 1, 0);
    }

    public String getClassName() {
        return "Paladin";
    }
}