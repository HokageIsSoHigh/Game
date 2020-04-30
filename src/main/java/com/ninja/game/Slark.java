package com.ninja.game;

public class Slark implements Element, Enemy {
    public int health;
    public int damage;
    public int exp;

    public Slark(int health, int damage, int exp) {
        this.health = health;
        this.damage = damage;
        this.exp = exp;
    }

    @Override
    public String getCSS() {
        return "Slark";
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public int exp() {
        return exp;
    }

    @Override
    public String getName() {
        return "Slark";
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            damage = damage - 1;
            health -= damage;
        }
    }

    @Override
    public int giveDamage() {
        return damage();
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDC09";
    }

    @Override
    public String getHoverName() {
        return "Slark";
    }
}
