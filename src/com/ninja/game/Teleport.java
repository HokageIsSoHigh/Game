package com.ninja.game;

public class Teleport implements Element {

    @Override
    public String getCSS() {
        return "TP";
    }

    @Override
    public String getName() {
        return "tp";
    }

    @Override
    public String getSymbol() {
        return "O";
    }

    @Override
    public String getHoverName() {
        return "Teleport";
    }

    public void teleport(){

    }
}
