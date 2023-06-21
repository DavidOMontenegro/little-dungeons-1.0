package items;

public class ImprovBow extends Item {
    public ImprovBow() {
        super("Improvised Bow", 28, new String[] { "Weapon", "Quick", "Bow", "Double-Handed" }, 15,
                new int[] { 0, 2, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0 },
                "A makeshift bow that actually shoots straight (most of the time)");
    }
}