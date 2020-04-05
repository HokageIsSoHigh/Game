package com.ninja.game;

public interface Enemy  {
    int health();
    int damage();
    int exp();
    String getName();
    void takeDamage(int damage);
    int giveDamage();
}
