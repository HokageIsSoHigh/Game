package com.ninja.game;

public class Water implements Element {
    @Override
    public String getCSS() {
        return "Water";
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public String getSymbol() {
        return "~";
    }

    @Override
    public String getHoverName() {
        return "Water";
    }
}
