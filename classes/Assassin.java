package classes;

public class Assassin extends Player {
    public Assassin(String name) {
        super(name, 45, 20, 0, 2, 0, 0);
    }

    public String getClassName() {
        return "Assassin";
    }
}