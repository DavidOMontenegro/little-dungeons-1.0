package classes;

public class Priest extends Player {
    public Priest(String name) {
        super(name, 55, 65, 0, 0, 2, 0);
    }

    public String getClassName() {
        return "Priest";
    }
}