package global;

import java.util.ArrayList;

import util.Item;

public class GlobalItems {
    private static ArrayList<Item> items = new ArrayList<Item>() {
        {
            add(new Item("Leather Hood", 22,
                    new String[] { "Head" }, 15,
                    new int[] { 0, 3, 0, 3, 1, 1, 0, 0, 3, 0, 0, 0 }, new String[] {
                            "Offers some protection, mostly from the rain" }));

            add(new Item("Clay Pot", 15,
                    new String[] { "Head" }, 15,
                    new int[] { 3, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 0 }, new String[] {
                            "You probably shouldn't put one of these on your head" }));

            add(new Item("Wolf Head Hood", 18,
                    new String[] { "Head" }, 20,
                    new int[] { 0, 0, 3, 2, 0, 0, 1, 1, 0, 0, 0, 3 }, new String[] {
                            "Used by the guardians of the forest during their rituals" }));

            add(new Item("Blindfold", 25,
                    new String[] { "Head" }, 15,
                    new int[] { 0, -1, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, new String[] {
                            "A black band to cover your eyes and help your inner focus" }));

            add(new Item("Dirty Rags", 1,
                    new String[] { "Body" }, 10,
                    new int[] { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "The homeless look; not a very exciting reward for an arena" }));

            add(new Item("Yellow Sheet", 6,
                    new String[] { "Body" }, 20,
                    new int[] { 0, 0, 2, 2, 0, 0, 0, 0, 0, 1, 0, 0 }, new String[] {
                            "Never feel cold again wrapped in these sheets" }));

            add(new Item("Not-So-Maculine Cuirass", 20,
                    new String[] { "Body" }, 15,
                    new int[] { 0, 2, 0, 0, 1, 0, 0, 0, 1, 2, 0, 0 }, new String[] {
                            "If you don't mind the look, it's actually pretty decent armour" }));

            add(new Item("Shrub", 17,
                    new String[] { "Body" }, 25,
                    new int[] { 0, 4, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 }, new String[] {
                            "Hide in plain sight with a shrub disguise" }));

            add(new Item("Adventurer's Boots", 14,
                    new String[] { "Feet" }, 20,
                    new int[] { 3, 0, 2, 0, 0, 0, 1, 0, 2, 0, 0, 2 }, new String[] {
                            "Sturdy boots for anyone making a long and difficult journey" }));

            add(new Item("Webbed Feet", 23,
                    new String[] { "Feet" }, 20,
                    new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, new String[] {
                            "Helps you swim faster and gives you 20 MP" }));

            add(new Item("Pointy Shoes", 26,
                    new String[] { "Feet" }, 10,
                    new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0 }, new String[] {
                            "If you put on a funny hat, you would look like a great fool" }));

            add(new Item("Fluffy Slippers", 7,
                    new String[] { "Feet" }, 20,
                    new int[] { 0, 0, 0, 3, 0, 0, 1, 0, 0, 0, 0, 1 }, new String[] {
                            "Feels like stepping on clouds" }));

            add(new Item("Wooden Leg", 21,
                    new String[] { "Feet" }, 25,
                    new int[] { 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "If you still have both legs, there's no real reason why you would need this, aside from the fact it gives you +5% gold for every victory" }));

            add(new Item("Snow Rackets", 29,
                    new String[] { "Feet", "Snow" }, 10,
                    new int[] { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, new String[] {
                            "Rackets to help you walk in the snow, they give +2 snow attack" }));

            add(new Item("Improvised Bow", 28,
                    new String[] { "Weapon", "Quick", "Bow", "Double-Handed" }, 15,
                    new int[] { 0, 2, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A makeshift bow that actually shoots straight (most of the time)" }));

            add(new Item("Magic Wand", 12,
                    new String[] { "Weapon", "Magic" }, 20,
                    new int[] { 0, 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, 0 }, new String[] {
                            "From the fairy godmother's factory, very good for beginner magicians" }));

            add(new Item("Gladius", 13,
                    new String[] { "Weapon", "Brute" }, 20,
                    new int[] { 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A simple but reliable sword; you'll never be disappointed" }));

            add(new Item("Snow Balls", 32,
                    new String[] { "Weapon", "Brute", "Magic", "Snow" }, 10,
                    new int[] { 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0 }, new String[] {
                            "Throw them at your friends, throw them at your enemies; the cold will numb them a little bit" }));

            add(new Item("Priestly Scepter", 9,
                    new String[] { "Weapon", "Sacred", "Double-Handed" }, 20,
                    new int[] { 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, 0, 0 }, new String[] {
                            "Used by priests for religious activities or to channel the power of a deity against their enemies" }));

            add(new Item("Bronze Dagger", 16,
                    new String[] { "Weapon", "Quick" }, 15,
                    new int[] { 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "They say one of these was used to kill the emperor" }));

            add(new Item("Incense", 31,
                    new String[] { "Weapon", "Sacred" }, 10,
                    new int[] { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 }, new String[] {
                            "If you burn enough of it, some being might help you out" }));

            add(new Item("Chopsticks", 27,
                    new String[] { "Weapon", "Quick" }, 20,
                    new int[] { 0, 0, 0, 4, 0, 1, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Usually used for eating, but they can hurt quite a bit when used for stabbing" }));

            add(new Item("Whip with Nails", 8,
                    new String[] { "Weapon", "Quick" }, 20,
                    new int[] { 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Not the most powerful weapon, and definitely not the most humane" }));

            add(new Item("Very Thin Sword", 5,
                    new String[] { "Weapon", "Quick" }, 15,
                    new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "This majestic sword also makes your healing abilities heal 4 more HP" }));

            add(new Item("Spear", 34,
                    new String[] { "Weapon", "Brute" }, 10,
                    new int[] { 3, 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A combat spear" }));

            add(new Item("Silver Shield", 19,
                    new String[] { "Shield" }, 25,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4 }, new String[] {
                            "Strong, resistent, durable; the best shield in the market" }));

            add(new Item("Kite Shield", 33,
                    new String[] { "Shield" }, 10,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 2 }, new String[] {
                            "Classic shield to cover your whole body" }));

            add(new Item("Paper Shield", 3,
                    new String[] { "Shield" }, 15,
                    new int[] { 0, 0, 0, 0, 0, 3, 0, 0, 4, 0, 1, 0 }, new String[] {
                            "Believe it or not, paper is really strong" }));

            add(new Item("Round Target", 11,
                    new String[] { "Shield" }, 15,
                    new int[] { 0, 0, 2, 0, 0, 0, 0, 0, 0, 5, 0, 0 }, new String[] {
                            "Test your enemy's aim" }));

            add(new Item("Viper Fang", 4,
                    new String[] { "Amulet" }, 15,
                    new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Poisonous enough that every one of your basic attacks will do 1 poison damage" }));

            add(new Item("Potato", 10,
                    new String[] { "Amulet" }, 20,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Holding this potato that gives 20 HP, you'll be unstoppble" }));

            add(new Item("Shiny Rock", 2,
                    new String[] { "Amulet" }, 50,
                    new int[] { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "It glows pretty" }));

            add(new Item("Bear Trap", 24,
                    new String[] { "Amulet" }, 50,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Anyone making a quick attack on you will have a nasty surprise: 2 damage" }));

            add(new Item("Cracked Gladiator Helmet", 57,
                    new String[] { "Head" }, 30,
                    new int[] { 3, 0, 1, 0, 1, 1, 0, 0, 5, 0, 0, 0 }, new String[] {
                            "The last person who wore this died to the sound of applause" }));

            add(new Item("Straw Hat", 73,
                    new String[] { "Head" }, 30,
                    new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 7 }, new String[] {
                            "Keeps you safe from the sun and from MAG attacks" }));

            add(new Item("Samurai Bun", 64,
                    new String[] { "Head" }, 45,
                    new int[] { 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "You don't have to be a samurai to wear one of these" }));

            add(new Item("Monacle", 69,
                    new String[] { "Head" }, 45,
                    new int[] { 0, 0, 0, 3, 0, 2, 0, 0, 0, 1, 0, 0 }, new String[] {
                            "Typically worn by physicians when doing surgery" }));

            add(new Item("Quiver of Poisonous Arrows", 68,
                    new String[] { "Body" }, 50,
                    new int[] { 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Will do 8 poison damage if you're using a bow" }));

            add(new Item("Robust Plates", 53,
                    new String[] { "Body" }, 30,
                    new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 8, 1, 8, 0 }, new String[] {
                            "The king had these made for his most important knights" }));

            add(new Item("Grandmaster's Cape", 59,
                    new String[] { "Body" }, 30,
                    new int[] { 0, 0, 5, 5, 0, 1, 0, 1, 0, 0, 0, 4 }, new String[] {
                            "Gives off a majestic and royal image" }));

            add(new Item("Dark Cloak", 49,
                    new String[] { "Body" }, 30,
                    new int[] { 0, 6, 0, 0, 0, 1, 0, 1, 0, 0, 3, 0 }, new String[] {
                            "Helps you hide in the darkness and look mysterious" }));

            add(new Item("Mantle Covered in Weeds", 71,
                    new String[] { "Body" }, 30,
                    new int[] { 0, 0, 0, 2, 0, 0, 2, 0, 0, 4, 0, 1 }, new String[] {
                            "It's a little itchy, but it will make you immune to poison" }));

            add(new Item("Light Sandals", 51,
                    new String[] { "Feet" }, 30,
                    new int[] { 0, 3, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 }, new String[] {
                            "Doesn't restrict your movement" }));

            add(new Item("Toe Ring", 52,
                    new String[] { "Feet" }, 25,
                    new int[] { 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Please don't wear this and the cuirass at the same time" }));

            add(new Item("Ball and Chain", 65,
                    new String[] { "Feet" }, 45,
                    new int[] { 10, 0, 0, 0, -8, -8, -8, -8, 0, 0, 0, 0 }, new String[] {
                            "You probably shouldn't have stolen that chicken" }));

            add(new Item("Clasped Sandals", 74,
                    new String[] { "Feet" }, 30,
                    new int[] { 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Doesn't slide off your feet easily" }));

            add(new Item("Boots with Hobnails", 60,
                    new String[] { "Feet" }, 50,
                    new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 4, 0, 4, 4 }, new String[] {
                            "A hobnail is a little nail at the sole of the boot that helps you not lose your footing on difficult terrain" }));

            add(new Item("Flasks of Poison Gas", 66,
                    new String[] { "Weapon", "Quick", "Magic" }, 50,
                    new int[] { 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0 }, new String[] {
                            "Throw one of these at an enemy and it will do 8 poison damage" }));

            add(new Item("Arena Sword", 62,
                    new String[] { "Weapon", "Brute" }, 30,
                    new int[] { 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "This sword has seen a lot of blood" }));

            add(new Item("Pilgrim's Staff", 42,
                    new String[] { "Weapon", "Sacred" }, 45,
                    new int[] { 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Travelers from the whole world would make the trip to the top of the mountain, it's always been a bit of a mystery what it is they were after" }));

            add(new Item("Bouquet of Tulips", 45,
                    new String[] { "Weapon", "Sacred", "Magic" }, 50,
                    new int[] { 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 1, 0 }, new String[] {
                            "Not the most threatening weapon, but it will give you 10 HP" }));

            add(new Item("Flail", 75,
                    new String[] { "Weapon", "Brute" }, 25,
                    new int[] { 1, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A mace on a chain" }));

            add(new Item("Cleaver", 44,
                    new String[] { "Weapon", "Quick" }, 50,
                    new int[] { 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Chop chop" }));

            add(new Item("Ice Bow", 58,
                    new String[] { "Weapon", "Quick", "Magic", "Bow", "Double-Handed", "Snow" }, 45,
                    new int[] { 0, 0, 0, 0, 0, 10, 0, 10, 0, 0, 0, 0 }, new String[] {
                            "Completely made of ice, pretty cool" }));

            add(new Item("Fishing Bow", 67,
                    new String[] { "Weapon", "Quick", "Bow", "Double-Handed" }, 50,
                    new int[] { 0, 0, 8, 0, 0, 6, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "More hardcore than traditional fishing with a fishing pole" }));

            add(new Item("Two-Handed Sword", 61,
                    new String[] { "Weapon", "Brute", "Double-Handed" }, 70,
                    new int[] { 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A long and heavy sword, one of the favorites in the arena" }));

            add(new Item("Native Shield", 56,
                    new String[] { "Shield" }, 50,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 1, 6, 8, 0, 2 }, new String[] {
                            "Carefully crafted from the toughest woods in the magic forest" }));

            add(new Item("Bark Shield", 48,
                    new String[] { "Shield" }, 50,
                    new int[] { 6, 0, 0, 0, 0, 2, 0, 0, 0, 1, 5, 5 }, new String[] {
                            "Tree bark might not be the best material for a shield" }));

            add(new Item("Gate", 41,
                    new String[] { "Shield" }, 50,
                    new int[] { 0, 0, 0, 4, 1, 0, 0, 0, 4, 4, 0, 0 }, new String[] {
                            "In a moment of desperation, anything can be used as a shield" }));

            add(new Item("Tablet with Ancient Symbols", 72,
                    new String[] { "Shield" }, 45,
                    new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 3, 3, 0, 3 }, new String[] {
                            "No one knows who wrote these runes or why they give you protection" }));

            add(new Item("Viking Skull", 43,
                    new String[] { "Amulet" }, 50,
                    new int[] { 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "The spiky hat suggests this used to belong to a viking, but some suspect it might be one of you in a previous life" }));

            add(new Item("Sacred Relic", 50,
                    new String[] { "Amulet" }, 40,
                    new int[] { 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "The church tried it's hardest to protect it, yet somehow it appeared in the arena one day" }));

            add(new Item("Magma Orb", 46,
                    new String[] { "Amulet" }, 50,
                    new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Like a round lava lamp that gives you +5 fire damage" }));

            add(new Item("Holy Water", 47,
                    new String[] { "Amulet" }, 40,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4 }, new String[] {
                            "Blessed" }));

            add(new Item("Ice Shank", 63,
                    new String[] { "Amulet" }, 50,
                    new int[] { 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "It might look like any other icicle, but this one gives you +6 snow damage" }));

            add(new Item("Laurel", 114,
                    new String[] { "Head" }, 40,
                    new int[] { 1, 0, 4, 0, 0, 0, 0, 0, 0, 0, 6, 0 }, new String[] {
                            "You earn one of these by putting your life in danger to save someone else" }));

            add(new Item("Hatter's Top Hat", 98,
                    new String[] { "Head" }, 100,
                    new int[] { 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 4, 4 }, new String[] {
                            "Well-rounded and versatile" }));

            add(new Item("Golden Helm", 84,
                    new String[] { "Head" }, 40,
                    new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 6, 0, 0, 6 }, new String[] {
                            "Shining from the distance, only the greatest heroes can wear these" }));

            add(new Item("Braided Beard", 113,
                    new String[] { "Head" }, 150,
                    new int[] { 6, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Goes all the way to the belly button" }));

            add(new Item("Festive Dragon Mask", 105,
                    new String[] { "Head" }, 80,
                    new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 10 }, new String[] {
                            "Made for the religious festivals, gives you +2 fire damage" }));

            add(new Item("Royal Cape", 85,
                    new String[] { "Body" }, 50,
                    new int[] { 0, 0, 4, 0, 0, 1, 0, 0, 3, 2, 0, 5 }, new String[] {
                            "The king always wears the finest, most expensive fabrics" }));

            add(new Item("Artificial Wings", 103,
                    new String[] { "Body" }, 50,
                    new int[] { 0, 1, 6, 1, 0, 0, 0, 0, 1, 0, 5, 0 }, new String[] {
                            "Don't fly too close to the sun or too close to the ocean" }));

            add(new Item("Oracle's Vestment", 89,
                    new String[] { "Body" }, 50,
                    new int[] { 0, 2, 2, 2, 0, 0, 3, 0, 0, 2, 2, 2 }, new String[] {
                            "The prophecy said you're stuck here" }));

            add(new Item("Frozen Shirt", 96,
                    new String[] { "Body" }, 80,
                    new int[] { 0, 0, 0, 1, 0, 0, 0, 1, 1, 3, 0, 0 }, new String[] {
                            "Gives you 4 armour against fire attacks" }));

            add(new Item("Chestplate With Burning Coals", 97,
                    new String[] { "Body" }, 80,
                    new int[] { 2, 0, 2, 0, 1, 0, 0, 1, 3, 2, 0, 0 }, new String[] {
                            "Gives you 4 armour against snow attacks" }));

            add(new Item("Backwards Feet", 101,
                    new String[] { "Feet" }, 50,
                    new int[] { 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1 }, new String[] {
                            "Does up to 8 extra damage on QUI attacks" }));

            add(new Item("White Bandage", 105,
                    new String[] { "Feet" }, 40,
                    new int[] { 5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0 }, new String[] {
                            "Wrap this around your foot and kick as hard as you can" }));

            add(new Item("Boots With Many Precious Jewels", 91,
                    new String[] { "Feet" }, 80,
                    new int[] { 0, 0, 4, 0, 3, 0, 0, 0, 6, 6, 0, 6 }, new String[] {
                            "Decorated with all kinds of precious stones" }));

            add(new Item("Flying Carpet", 93,
                    new String[] { "Feet" }, 50,
                    new int[] { 0, 5, 0, 5, 0, 0, 1, 0, 5, 0, 0, 2 }, new String[] {
                            "The best sidekick when you need to travel fast" }));

            add(new Item("Thick Iron Boots", 82,
                    new String[] { "Feet" }, 50,
                    new int[] { 4, 0, 0, 0, 1, 0, 1, 1, 10, 10, 0, 0 }, new String[] {
                            "Heavy, but sturdy" }));

            add(new Item("War Axe", 92,
                    new String[] { "Weapon", "Brute", "Double-Handed" }, 150,
                    new int[] { 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Powerful and threatening" }));

            add(new Item("Twin Daggers", 102,
                    new String[] { "Weapon", "Quick", "Double-Handed" }, 150,
                    new int[] { 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Two daggers made to be used together" }));

            add(new Item("Inquisition Hammer", 86,
                    new String[] { "Weapon", "Brute" }, 150,
                    new int[] { 0, 0, 5, 0, 8, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Crush the infidels" }));

            add(new Item("Dragon Staff", 95,
                    new String[] { "Weapon", "Magic", "Double-Handed" }, 80,
                    new int[] { 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "This powerful weapon was feared even by the great sages who created it" }));

            add(new Item("Flaming Sword", 100,
                    new String[] { "Weapon", "Brute", "Fire" }, 150,
                    new int[] { 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Does fire damage" }));

            add(new Item("Sabre", 83,
                    new String[] { "Weapon", "Quick" }, 150,
                    new int[] { 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Quick and strong" }));

            add(new Item("Delicate Bow", 106,
                    new String[] { "Weapon", "Quick", "Bow", "Double-Handed" }, 80,
                    new int[] { 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Made for display, not for use; gives you 20 HP and 20 MP" }));

            add(new Item("Ice Scepter", 101,
                    new String[] { "Weapon", "Sacred", "Snow" }, 150,
                    new int[] { 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0 }, new String[] {
                            "The clerics in the snowy mountain peaks had strange powers no one understood" }));

            add(new Item("Cursed Shield", 107,
                    new String[] { "Shield" }, 150,
                    new int[] { -3, -3, -6, -3, 0, 0, 0, 0, 12, 12, -1, 12 }, new String[] {
                            "Don't make deals with spirits, it's usually not worth the cost" }));

            add(new Item("Spartan Shield", 108,
                    new String[] { "Shield" }, 80,
                    new int[] { 6, 0, 0, 0, 2, 2, 0, 0, 6, 3, 0, 0 }, new String[] {
                            "Doesn't cover your whole body, but it allows you to move faster" }));

            add(new Item("Alchemist's Shield", 88,
                    new String[] { "Shield" }, 80,
                    new int[] { 0, 0, 0, 1, 0, 1, 0, 1, 8, 0, 12, 1 }, new String[] {
                            "Made to protect mages during the inquisition" }));

            add(new Item("Church Shield", 87,
                    new String[] { "Shield" }, 80,
                    new int[] { 0, 0, 2, 0, 1, 0, 1, 0, 6, 0, 1, 10 }, new String[] {
                            "Made to protect inquisitors from anyone who would fight back" }));

            add(new Item("Shield Enchanted With Thorns", 104,
                    new String[] { "Shield" }, 150,
                    new int[] { 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 3 }, new String[] {
                            "Anyone who hits you with a basic attack will take 6 poison damage" }));

            add(new Item("Dragon Scale", 99,
                    new String[] { "Shield" }, 50,
                    new int[] { 0, 0, 0, 0, 3, 0, 0, 1, 0, 5, 5, 0 }, new String[] {
                            "Withstands lightnings, hail and volcanoes; impenetrable to everything but the claws of another dragon" }));

            add(new Item("Holy Book", 94,
                    new String[] { "Amulet" }, 80,
                    new int[] { 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Contains several teachings on how to live a good life, sadly that's not what people use it for; gives you +6 heal" }));

            add(new Item("Four Leaf Clover", 110,
                    new String[] { "Amulet" }, 80,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Win 5 gold for every round you survive in a fight" }));

            add(new Item("Alchemy Kit", 109,
                    new String[] { "Amulet" }, 150,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "All enemies have -2 of all DEF and -4 of all ATK" }));

            add(new Item("Helm of Hades", 131,
                    new String[] { "Head" }, 300,
                    new int[] { 0, 8, 0, 0, 0, 1, 0, 0, 6, 1, 6, 0 }, new String[] {
                            "The king of the underworld wore this in the war against the titans" }));

            add(new Item("Pointy Hat", 128,
                    new String[] { "Head" }, 80,
                    new int[] { 0, 0, 0, 9, 0, 0, 0, 0, 2, 0, 0, 1 }, new String[] {
                            "For witches and wizards who whish to look the part" }));

            add(new Item("Royal Crown", 142,
                    new String[] { "Head" }, 250,
                    new int[] { 0, 0, 4, 2, 1, 0, 1, 0, 0, 0, 14, 0 }, new String[] {
                            "Made of gold and adorned with rubies and sapphires" }));

            add(new Item("Officer's Helmet", 129,
                    new String[] { "Head" }, 80,
                    new int[] { 5, 0, 0, 0, 0, 1, 4, 0, 10, 10, 0, 0 }, new String[] {
                            "Protects the head from sword blows and arrows" }));

            add(new Item("Tonsure", 132,
                    new String[] { "Head" }, 80,
                    new int[] { 0, 0, 8, 0, 1, 0, 0, 0, 0, 0, 2, 1 }, new String[] {
                            "Also known as the monk cut, the intentional baldness symbolizes humility" }));

            add(new Item("Dark Mask", 135,
                    new String[] { "Head" }, 100,
                    new int[] { 0, 6, 1, 0, 0, 0, 0, 3, 0, 0, 6, 6 }, new String[] {
                            "Black as the night" }));

            add(new Item("Andromeda Cloak", 136,
                    new String[] { "Body" }, 250,
                    new int[] { 0, 0, 0, 12, 0, 0, 2, 2, 1, 6, 0, 6 }, new String[] {
                            "Dark, but shining; black, but colourful; wizard's robes that look like the night sky" }));

            add(new Item("Ephod", 130,
                    new String[] { "Body" }, 250,
                    new int[] { 1, 0, 8, 0, 0, 0, 0, 0, 0, 1, 2, 8 }, new String[] {
                            "Everything a priest needs to lead the people" }));

            add(new Item("Chestplate of Blood", 124,
                    new String[] { "Body" }, 250,
                    new int[] { 6, 0, 0, 2, 0, 0, 3, 0, 10, 3, 10, 1 }, new String[] {
                            "No one's really sure what this is, all we know is it offers really good protection" }));

            add(new Item("Pilferer's Garb", 134,
                    new String[] { "Body" }, 250,
                    new int[] { 0, 8, 0, 0, 0, 0, 0, 0, 0, 6, 2, 0 }, new String[] {
                            "Light and easy to move around in; covers your face and lets you get lost in the crowd" }));

            add(new Item("Ares' Greaves", 125,
                    new String[] { "Feet" }, 300,
                    new int[] { 5, 0, 0, 0, 1, 1, 0, 0, 8, 8, 1, 0 }, new String[] {
                            "The god of war never loses a fight" }));

            add(new Item("Hermes' Winged Sandals", 123,
                    new String[] { "Feet" }, 300,
                    new int[] { 0, 6, 0, 4, 0, 0, 2, 0, 0, 5, 5, 0 }, new String[] {
                            "Used by the messenger of the gods to fly to all the most distant corners of the world and beyond" }));

            add(new Item("Satyr Legs", 127,
                    new String[] { "Feet" }, 300,
                    new int[] { 0, 0, 8, 8, 3, 0, 0, 0, 0, 0, 3, 2 }, new String[] {
                            "Goat legs" }));

            add(new Item("Two-Bladed Sword", 152,
                    new String[] { "Weapon", "Quick" }, 80,
                    new int[] { 0, 2, 0, 0, 6, 0, 0, 0, 5, 0, 0, 0 }, new String[] {
                            "One sword to defend and attack; use blades on both sides" }));

            add(new Item("Greek Fire Cannon", 137,
                    new String[] { "Weapon", "Magic", "Double-Handed", "Fire" }, 300,
                    new int[] { 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A giant ancient flamethrower; this actually exists, look it up" }));

            add(new Item("Head of a Sacrificed Goat", 150,
                    new String[] { "Weapon", "Sacred" }, 300,
                    new int[] { 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "The greater the sacrifice, the greater the favour" }));

            add(new Item("Dark Sword", 153,
                    new String[] { "Weapon", "Brute", "Quick", "Sacred", "Magic" }, 300,
                    new int[] { 0, 0, 0, 0, 10, 10, 10, 10, 0, 0, 0, 0 }, new String[] {
                            "Absorbs all light that comes close, you can use it in any kind of basic attack" }));

            add(new Item("Poseidon's Trident", 127,
                    new String[] { "Weapon", "Magic" }, 300,
                    new int[] { 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Forged for the god of the sea" }));

            add(new Item("Prophet's Sword", 122,
                    new String[] { "Weapon", "Brute" }, 100,
                    new int[] { 0, 0, 8, 0, 10, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "A great prophet long ago foretold of a curse that would come unto the land" }));

            add(new Item("Crossbow", 143,
                    new String[] { "Weapon", "Quick", "Bow" }, 300,
                    new int[] { 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Shoot from afar, avoiding counter attacks, thorns, spikes and such" }));

            add(new Item("Mirage Sword", 145,
                    new String[] { "Weapon", "Sacred" }, 100,
                    new int[] { 0, 0, 5, 5, 0, 0, 11, 0, 0, 0, 0, 0 }, new String[] {
                            "Try to strike a tree with it and you'll see it has no physical blade" }));

            add(new Item("Zeus' Shield", 33,
                    new String[] { "Shield" }, 300,
                    new int[] { 0, 0, 8, 0, 4, 1, 1, 0, 8, 2, 8, 0 }, new String[] {
                            "Magical shield used by the king of Olympus; few people have even seen it, let alone used it" }));

            add(new Item("Sai Dagger", 33,
                    new String[] { "Shield" }, 300,
                    new int[] { 0, 0, 0, 0, 8, 8, 8, 8, 3, 0, 3, 0 }, new String[] {
                            "Special defensive daggers made to catch the enemy's sword" }));

            add(new Item("Dueling Shield", 121,
                    new String[] { "Shield", "Double-Handed" }, 250,
                    new int[] { 0, 0, 0, 0, 8, 0, 0, 0, 12, 12, 12, 12 }, new String[] {
                            "Giant two-handed shield used to defend and attack" }));

            add(new Item("Dreamcatcher", 148,
                    new String[] { "Amulet" }, 300,
                    new int[] { 0, 0, 0, 4, 0, 0, 1, 0, 0, 0, 0, 2 }, new String[] {
                            "Protects you from evil spirits and you heal 1 to 6 HP for every basic attack you do" }));

            add(new Item("Amulet of the Protective Spirit", 139,
                    new String[] { "Amulet" }, 300,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 1, 9, 9, 9, 9 }, new String[] {
                            "Some mystic being was trapped in this locket and now protects any who carries it" }));

            add(new Item("Cauldron", 147,
                    new String[] { "Amulet" }, 100,
                    new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new String[] {
                            "Brew some potions and do 6 poison damage for every MAG attack" }));

            add(new Item("Super Mega Powerful Item", 144,
                    new String[] { "Amulet" }, 300,
                    new int[] { 3, 3, 3, 3, 5, 5, 5, 5, 0, 0, 0, 0 }, new String[] {
                            "It looks funky" }));

            add(new Item("Plate Scale", 154,
                    new String[] { "Amulet" }, 300,
                    new int[] { 0, 0, 8, 8, 0, 0, 0, 0, 2, 2, 0, 0 }, new String[] {
                            "Justice is blind" }));
        }
    };

    private GlobalItems() {
    }

    public static ArrayList<Item> addItems() {
        return items;
    }

    public static Item getItem(int id) {
        for (Item tmp : items) {
            if (tmp.isItem(id)) {
                return tmp;
            }
        }
        return null;
    }
}
