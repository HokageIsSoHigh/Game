package com.ninja.game;

public class Equipment {

    public int price;
    public Slot slot;
    public String name;

    public Equipment(int price, Slot slot, String name) {
        this.price = price;
        this.slot = slot;
        this.name = name;
    }
}
