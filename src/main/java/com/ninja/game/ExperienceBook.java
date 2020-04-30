package com.ninja.game;

public class ExperienceBook implements Element {
    public int expInBook;

    public ExperienceBook(int expInBook){
        this.expInBook = expInBook;
    }

    @Override
    public String getCSS() {
        return "ExperienceBook";
    }

    @Override
    public String getName() {
        return "ExpBook";
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDCDA";
    }

    @Override
    public String getHoverName() {
        return "ExpBook";
    }
}
