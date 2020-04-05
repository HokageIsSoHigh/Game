package com.ninja.game;

public class Creep implements Element, Enemy{

    public int health;
    public int exp;
    public int damage;

    public Creep(int health, int damage, int exp) {
        this.health = health;
        this.exp = exp;
        this.damage = damage;
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
        return "Creep";
    }

    @Override
    public String getName() {
        return "Stack of creeps";
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int giveDamage() {
        return damage;
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDC6A";
    }

    @Override
    public String getHoverName() {
        return "Stack of creeps";
    }
}
