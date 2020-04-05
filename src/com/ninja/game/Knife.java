package com.ninja.game;

public class Knife implements Element, Slot {
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public String getCSS() {
        return "Knife";
    }

    @Override
    public String getName() {
        return "Knife";
    }

    @Override
    public String getSymbol() {
        return "⚔";
    }

    @Override
    public String getHoverName() {
        return "Knife";
    }

    @Override
    public String getDescription() {
        return "Give you hero +5 damage";
    }

    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public int heal() {
        return 0;
    }

    @Override
    public int damage() {
        return 5;
    }
}
