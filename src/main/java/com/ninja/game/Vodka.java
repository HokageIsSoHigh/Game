package com.ninja.game;

public class Vodka implements Slot{

    @Override
    public String getName() {
        return "Vodka";
    }

    @Override
    public int getID() {
        return 666;
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public String getDescription() {
        return "+50% of miss for 20 attacks, +30hp";
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public int heal() {
        return 30;
    }

    @Override
    public int damage() {
        return 0;
    }
}
