package com.ninja.game;


public class BladeOfAssassin implements Element, Slot {


    @Override
    public String getCSS() {
        return "BOA";
    }

    @Override
    public String getName() {
        return "BOA";
    }

    @Override
    public int getID() {
        return 228;
    }

    @Override
    public String getSymbol() {
        return "||";
    }

    @Override
    public String getDescription() {
        return "Give your hero 125 damage(chance of critical strike 30%)";
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
        return 125;
    }

    @Override
    public String getHoverName() {
        return "Assassin blade";
    }

}
