package com.ninja.game;

public class Chest implements Element {
    public int money;

    public Chest(int money) {
        this.money = money;
    }

    @Override
    public String getCSS() {
        return "Chest";
    }

    @Override
    public String getName() {
        return "Chest";
    }

    @Override
    public String getSymbol() {
        return "☒";
    }

    @Override
    public String getHoverName() {
        return "Chest";
    }

}
