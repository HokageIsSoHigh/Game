package com.ninja.game;

public class Naga implements Element, Enemy {
    public int health;
    public int exp;
    public int damage;

    public Naga(int health, int damage, int exp) {
        this.health = health;
        this.exp = exp;
        this.damage = damage;
    }


    @Override
    public String getCSS() {
        return "Naga";
    }

    @Override
    public String getName() {
        return "Naga";
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            damage -= 3;
            health -= damage;
        }
    }

    @Override
    public int giveDamage() {
        return damage();
    }

    @Override
    public String getSymbol() {
        return "♗";
    }

    @Override
    public String getHoverName() {
        return "Naga with " + health + " hp";
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


}
