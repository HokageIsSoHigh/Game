package com.ninja.game;

public interface Slot {
    String getName();
    int getID();
    String getSymbol();
    String getDescription();
    boolean canUse();
    int heal();
    int damage();
}
