package com.ninja.game;

public class Binoculars implements Element, Slot {

    @Override
    public String getCSS() {
        return "Binoculars";
    }

    @Override
    public String getName() {
        return "Binoculars";
    }

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public String getSymbol() {
        return "∞";
    }

    @Override
    public String getHoverName() {
        return "Binoculars";
    }

    @Override
    public String getDescription() {
        return "Increase your view";
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
        return 0;
    }
    // hello

}
