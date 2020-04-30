package com.ninja.game;

public class Tree implements Element {
    @Override
    public String getCSS() {
        return "Tree";
    }

    @Override
    public String getName() {
        return "Tree";
    }

    @Override
    public String getSymbol() {
        return "\uD83C\uDF32";
    }

    @Override
    public String getHoverName() {
        return "";
    }
}
