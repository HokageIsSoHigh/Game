package com.ninja.game;

public class Phyrion implements Enemy, Element {
    public int health;
    public int damage;
    public int exp;
    private Buff buff = new Buff();

    public Phyrion(int health, int damage, int exp) {
        this.health = health;
        this.damage = damage;
        this.exp = exp;
    }

    @Override
    public String getSymbol() {
        return "⛄";
    }

    @Override
    public String getHoverName() {
        return "Phyrion";
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
        return "Phyrion";
    }

    @Override
    public String getName() {
        return "Phyrion";
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            if (buff.miss(2)) {
                health += 5;
            } else {
                health -= damage - 5;
            }
        }
    }

    @Override
    public int giveDamage() {
        return damage();
    }
}
