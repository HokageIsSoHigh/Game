package com.ninja.game;

public class Woodman implements Element, Enemy {
    public int health;
    public int damage;
    public int exp;

    public Woodman(int health, int damage, int exp) {
        this.health = health;
        this.damage = damage;
        this.exp = exp;
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
    public String getCSS() {
        return "Woodman";
    }

    @Override
    public String getName() {
        return "Woodman";
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            damage = damage / 2;
            health -= damage;
        }
    }

    @Override
    public int giveDamage() {
        return damage;
    }

    @Override
    public String getSymbol() {
        return "\uD83E\uDDDD";
    }

    @Override
    public String getHoverName() {
        return "Woodman";
    }
}
