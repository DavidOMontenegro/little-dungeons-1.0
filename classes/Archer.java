package classes;

public class Archer extends Player {
    public Archer(String name) {
        super(name, 50, 40, 0, 1, 0, 1);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        return super.preAttack(d20, type, basic) ? right == null ? true : !right.getType().contains("Bow") : false;
    }

    public String getClassName() {
        return "Archer";
    }
}