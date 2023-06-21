package classes;

public class Monk extends Player {
    public Monk(String name) {
        super(name, 55, 50, 0, 1, 1, 0);
    }

    public String getClassName() {
        return "Monk";
    }
}