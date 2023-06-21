package classes;

public class Barbarian extends Player {
    public Barbarian(String name) {
        super(name, 60, 15, 2, 0, 0, 0);
    }

    @Override
    public boolean preAttack(int d20, String type, boolean basic) {
        if (hp < totalhp / 2 && basic) {
            bruATK += 8;
            quiATK += 8;
            sacATK += 8;
            magATK += 8;
        }

        return super.preAttack(d20, type, basic);
    }

    @Override
    public int postAttack(Player defender, String type, boolean basic, int current) {
        if (hp < totalhp / 2 && basic) {
            bruATK -= 8;
            quiATK -= 8;
            sacATK -= 8;
            magATK -= 8;
        }

        return super.postAttack(defender, type, basic, current);
    }

    public String getClassName() {
        return "Barbarian";
    }
}