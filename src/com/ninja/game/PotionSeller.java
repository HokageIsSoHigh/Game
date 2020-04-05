package com.ninja.game;

import java.util.ArrayList;
import java.util.List;

public class PotionSeller implements NPC, Element {
    @Override
    public List<Equipment> equipment() {
        List<Equipment> sellerPotion = new ArrayList<>();

        sellerPotion.add(new Equipment(5, new HealthPotion(10), "Health Potion(small)"));
        sellerPotion.add(new Equipment(25, new HealthPotion(50), "Health Potion(medium)"));
        sellerPotion.add(new Equipment(50, new HealthPotion(101), "Health Potion(large)"));
        sellerPotion.add(new Equipment(10, new Vodka(), "Vodka"));
        return sellerPotion;
    }

    @Override
    public List<String> hisList() {
        return null;
    }

    @Override
    public String getCSS() {
        return "PotionSeller";
    }

    @Override
    public String getName() {
        return "Seller";
    }

    @Override
    public String getSymbol() {
        return "\uD83C\uDFEA";
    }

    @Override
    public String getHoverName() {
        return "Armor Seller";
    }
}
