package com.ninja.game;

import java.util.ArrayList;
import java.util.List;

public class WeaponSeller implements Element, NPC {


    @Override
    public String getCSS() {
        return "WeaponSeller";
    }

    @Override
    public String getName() {
        return "WeaponSeller";
    }

    @Override
    public String getSymbol() {
        return "\uD83C\uDFEA️";
    }

    @Override
    public String getHoverName() {
        return "Weapon seller";
    }

    @Override
    public List<Equipment> equipment() {
        List<Equipment> sellerWeapon = new ArrayList<>();
        sellerWeapon.add(new Equipment(10, new Knife(), "Knife"));
        sellerWeapon.add(new Equipment(100, new Sword(), "Sword"));
        sellerWeapon.add(new Equipment(500, new BladeOfAssassin(), "Blade of assassin"));

        return sellerWeapon;
    }

    @Override
    public List<String> hisList() {
        return null;
    }

}

