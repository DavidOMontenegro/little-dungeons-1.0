package items;

public class SnowBalls extends Item {
    public SnowBalls() {
        super("Snow Balls", 32, new String[] { "Weapon", "Brute", "Magic", "Snow" }, 10,
                new int[] { 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0 },
                "Throw them at your friends, throw them at your enemies; the cold will numb them a little bit");
    }
}