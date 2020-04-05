package com.ninja.game;

public class HealthPotion implements Slot {
    public int healPoints;

    public HealthPotion(int healPoints){
        this.healPoints = healPoints;
    }

    @Override
    public String getName() {
        return "Health potion";
    }

    @Override
    public int getID() {
        return healPoints;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public String getDescription() {
        return "Restore your hero " + healPoints + " health ";
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public int heal() {
        return healPoints;
    }

    @Override
    public int damage() {
        return 0;
    }
}
