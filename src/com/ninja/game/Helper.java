package com.ninja.game;

import java.util.ArrayList;
import java.util.List;

public class Helper implements NPC, Element {
    @Override
    public String getCSS() {
        return "Helper";
    }

    @Override
    public String getName() {
        return "Helper";
    }

    @Override
    public String getSymbol() {
        return "?";
    }

    @Override
    public String getHoverName() {
        return "Helper";
    }

    @Override
    public List<Equipment> equipment() {
        return null;
    }

    @Override
    public List<String> hisList() {
        List<String> list = new ArrayList<>();
        list.add("Hello!");
        list.add("Welcome to 'Black Forest'. Here you will met sellers.");
        list.add("Seller sell weapons, armor and potions.");
        list.add("Also there are lot of enemies such as 'Creeps', 'Nagas' and main boss 'Demon Valera'.");
        list.add("You can find chess with coins. You can use coins to buy armor, salves and weapons. There are traps, so be careful.");
        list.add("Some items are in secret places, this items will help you to complete game.");
        list.add("So bey!");
        list.add("(my suggestion not to drink 'Vodka')");

        return list;
    }
}
