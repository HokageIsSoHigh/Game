package com.ninja.game;

public class Sword implements Slot, Element {
    @Override
    public String getCSS() {
        return "Sword";
    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public int getID() {
        return 1337;
    }

    @Override
    public String getSymbol() {
        return "||";
    }

    @Override
    public String getHoverName() {
        return "Sword";
    }

    @Override
    public String getDescription() {
        return "Give you 30 damage";
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
        return 30;
    }
}
