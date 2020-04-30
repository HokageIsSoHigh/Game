package com.ninja.game;

public class Land implements Element {

    @Override
    public String getCSS() {
        return "Land";
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getSymbol() {
        return " ";
    }

    @Override
    public String getHoverName() {
        return "";
    }
}
