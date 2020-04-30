package com.ninja.game;

public class Trap implements Element{
    public int damage;

    public Trap(int damage) {
        this.damage = damage;
    }

    @Override
    public String getCSS() {
        return "Trap";
    }

    @Override
    public String getName() {
        return "Trap";
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDD73️";
    }

    @Override
    public String getHoverName() {
        return "Explosion trap";
    }
}
