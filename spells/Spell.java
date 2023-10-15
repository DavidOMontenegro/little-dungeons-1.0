package spells;

import org.json.JSONObject;

import classes.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spell {
    String name;
    String desc;
    String desc2;
    String desc3;
    String desc4;
    String desc5;
    int level = 0;
    List<String> type;
    int price;
    int baseCost;
    int mpCost = 0;

    public Spell(String spellName, String[] spellDesc, String[] spellType, int spellPrice, int spellCost) {
        name = spellName;
        desc = spellDesc[0];
        desc2 = spellDesc[1];
        desc3 = spellDesc[2];
        desc4 = spellDesc[3];
        desc5 = spellDesc[4];
        price = spellPrice;
        baseCost = spellCost;
        type = new ArrayList<>();
        type.addAll(Arrays.asList(spellType));
    }

    public Spell(JSONObject spell, String[] spellDesc, String[] spellType, int spellCost) {
        name = (String) spell.get("name");
        level = (int) spell.get("level");
        price = (int) spell.get("price");
        mpCost = (int) spell.get("cost");
        desc = spellDesc[0];
        desc2 = spellDesc[1];
        desc3 = spellDesc[2];
        desc4 = spellDesc[3];
        desc5 = spellDesc[4];
        baseCost = spellCost;
        type = new ArrayList<>();
        type.addAll(Arrays.asList(spellType));
    }

    public String getName() {
        return name;
    }

    public String[] getDesc() {
        return new String[] { desc, desc2, desc3, desc4, desc5 };
    }

    public List<String> getType() {
        return type;
    }

    public String getPrice() {
        return "  (" + price + " Gold)";
    }

    public int getLevel() {
        return level;
    }

    public int getCost() {
        return mpCost;
    }

    public void seeSpell(boolean shop, Player player) {
        String thisDesc = desc;
        if (shop) {
            switch (level) {
                case 1:
                    thisDesc = desc2;
                    break;
                case 2:
                    thisDesc = desc3;
                    break;
                case 3:
                    thisDesc = desc4;
                    break;
                case 4:
                case 5:
                    thisDesc = desc5;
                    break;
            }
        }
        System.out.println("\n" + name + " (" + String.join(", ", type) + ")" + "  Price: " + price + "\nLVL: "
                + (shop ? level + 1 : level)
                + (type.get(0).equals("Active") ? "   MP: " + (mpCost + (shop ? baseCost : 0)
                        - (player.getClassName().equals("Priest") && level == 0 && type.contains("Heal") ? 15 : 0))
                        : "")
                + "\n\n" + thisDesc + "\n");
    }

    public boolean levelUp(boolean bought, Player player) {
        switch (level) {
            case 1:
                desc = desc2;
                if (player.getClassName().equals("Priest") && type.contains("Heal")) {
                    mpCost -= 15;
                }
                break;
            case 2:
                desc = desc3;
                break;
            case 3:
                desc = desc4;
                break;
            case 4:
                desc = desc5;
                break;
            case 5:
                System.out.println("This spell is already max level.");
                return false;
        }
        level++;
        price += bought ? 50 : 0;
        mpCost += baseCost;
        return true;
    }

    public int use(int current, List<Player> active) {
        active.get(current).useMP(mpCost);
        return current;
    }

    public JSONObject save() {
        JSONObject save = new JSONObject();
        save.put("active", type.contains("Active"));
        save.put("name", name);
        save.put("level", level);
        save.put("price", price);
        save.put("cost", mpCost);
        return save;
    }
}