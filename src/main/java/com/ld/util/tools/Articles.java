package com.ld.util.tools;

import com.ld.global.GlobalItems;
import com.ld.util.Item;

public class Articles {
    private Articles() {}

    public static String generate(int id) {
        return plural(GlobalItems.getItem(id));
    }

    public static String plural(Item item) {
        String name = item.getName();
        return ("AEIOU".indexOf(name.charAt(0)) != -1 ? "n " : " ") + name;
    }
}
