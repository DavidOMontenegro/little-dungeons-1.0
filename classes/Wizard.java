package classes;

public class Wizard extends Player {
    public Wizard(String name) {
        super(name, 45, 80, 0, 0, 0, 2);
    }

    public String getClassName() {
        return "Wizard";
    }
}