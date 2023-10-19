package com.ld.util.tools;

import com.ld.global.GlobalItems;
import com.ld.util.Item;

public class Plural {
    private Plural() {}

    public static String plural(int id) {
        return plural(GlobalItems.getItem(id));
    }

    public static String plural(Item item) {
        return ("AEIOU".indexOf(item.getName().charAt(0)) != -1 ? "n " : " ") + item.getName();
    }
}
