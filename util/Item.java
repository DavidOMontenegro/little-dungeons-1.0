package util;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Player;

public class Item {
    int id;
    String name;
    String desc;
    ArrayList<String> type;
    int price;
    int str, dex, wis, it;
    int bruATK, quiATK, sacATK, magATK;
    int bruDEF, quiDEF, sacDEF, magDEF;

    public Item(String itemName, int itemId, String[] itemType, int itemPrice, int[] stats, String spellDesc) {
        id = itemId;
        name = itemName;
        desc = spellDesc;
        price = itemPrice;
        str = stats[0];
        dex = stats[1];
        wis = stats[2];
        it = stats[3];
        bruATK = stats[4];
        quiATK = stats[5];
        sacATK = stats[6];
        magATK = stats[7];
        bruDEF = stats[8];
        quiDEF = stats[9];
        sacDEF = stats[10];
        magDEF = stats[11];
        type = new ArrayList<>();
        type.addAll(Arrays.asList(itemType));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public String getPrice() {
        return "  (" + price + " Gold)";
    }

    public void seeItem() {
        String s = str > 9 ? str + " " : str + "  ";
        String d = dex > 9 ? dex + " " : dex + "  ";
        String w = wis > 9 ? wis + " " : wis + "  ";
        String i = it > 9 ? it + " " : it + "  ";
        String ba = bruATK > 9 ? bruATK + " " : bruATK + "  ";
        String qa = quiATK > 9 ? quiATK + " " : quiATK + "  ";
        String sa = sacATK > 9 ? sacATK + " " : sacATK + "  ";
        String ma = magATK > 9 ? magATK + " " : magATK + "  ";
        String bd = bruDEF > 9 ? bruDEF + " " : bruDEF + "  ";
        String qd = quiDEF > 9 ? quiDEF + " " : quiDEF + "  ";
        String sd = sacDEF > 9 ? sacDEF + " " : sacDEF + "  ";
        String md = magDEF > 9 ? magDEF + " " : magDEF + "  ";

        System.out.printf("\n" + name + "     Price: " + price
                + "\n\n( " + String.join(", ", type) + " )\n\nSTR: %s BRUATK: %s BRUDEF: %s\nDEX: %s QUIATK: %s QUIDEF: %s\nWIS: %s SACATK: %s SACDEF: %s\nINT: %s MAGATK: %s MAGDEF: %s\n\n%s\n\n",
                s, ba, bd, d, qa, qd, w, sa, sd, i, ma, md, desc);
    }

    public void putOn(Player player) {
        player.effect("str", str);
        player.effect("dex", dex);
        player.effect("wis", wis);
        player.effect("int", it);
        player.effect("bruATK", bruATK);
        player.effect("quiATK", quiATK);
        player.effect("sacATK", sacATK);
        player.effect("magATK", magATK);
        player.effect("bruDEF", bruDEF);
        player.effect("quiDEF", quiDEF);
        player.effect("sacDEF", sacDEF);
        player.effect("magDEF", magDEF);
        switch (id) {
            case 5:
                player.effect("heal", 4);
            case 10:
                player.effect("hp", 20);
                break;
            case 23:
                player.effect("mp", 20);
                break;
            case 45:
                player.effect("hp", 10);
                break;
            case 69:
                player.effect("heal", 6);
            default:
                break;
        }
    }

    public void takeOff(Player player) {
        player.effect("str", -str);
        player.effect("dex", -dex);
        player.effect("wis", -wis);
        player.effect("int", -it);
        player.effect("bruATK", -bruATK);
        player.effect("quiATK", -quiATK);
        player.effect("sacATK", -sacATK);
        player.effect("magATK", -magATK);
        player.effect("bruDEF", -bruDEF);
        player.effect("quiDEF", -quiDEF);
        player.effect("sacDEF", -sacDEF);
        player.effect("magDEF", -magDEF);
        switch (id) {
            case 5:
                player.effect("heal", -4);
            case 10:
                player.effect("hp", -20);
                break;
            case 23:
                player.effect("mp", -20);
                break;
            case 45:
                player.effect("hp", -10);
                break;
            case 69:
                player.effect("heal", -6);
            default:
                break;
        }
    }
}