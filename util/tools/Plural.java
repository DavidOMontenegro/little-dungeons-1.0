package util.tools;

import global.GlobalItems;
import util.Item;

public class Plural {
    private Plural() {}

    public static String plural(int id) {
        return plural(GlobalItems.getItem(id));
    }

    public static String plural(Item item) {
        return ("AEIOU".indexOf(item.getName().charAt(0)) != -1 ? "n " : " ") + item.getName();
    }
}
