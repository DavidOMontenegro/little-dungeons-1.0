package classes;

public class Thief extends Player {
    public Thief(String name) {
        super(name, 40, 50, 0, 1, 0, 0);
    }

    public String getClassName() {
        return "Thief";
    }
}