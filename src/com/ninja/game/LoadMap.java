package com.ninja.game;

import java.io.*;
import java.util.Scanner;

public class LoadMap {

    public static void loadMap(Map map, InputStream stream) {
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("[,\n]");

        for (int y = 0; y < Map.HEIGHT; y++) {
            for (int x = 0; x < Map.WIDTH; x++) {
                if (!scanner.hasNext()) {
                    break;
                }
                String cell = scanner.next();
                if (cell.equals("t")) {
                    map.addElement(x, y, new Tree());
                } else if (cell.contains("Naga")) {
                    map.addElement(x, y, new Naga(getHealthPoints(cell), getDamage(cell), getExp(cell)));
                } else if (cell.contains("Chest")) {
                    map.addElement(x, y, new Chest(getMoney(cell)));
                } else if (cell.contains("Trap")) {
                    map.addElement(x, y, new Trap(getDamage(cell)));
                } else if (cell.contains("Armor Seller")) {
                    map.addElement(x, y, new WeaponSeller());
                } else if (cell.contains("Seller Poision")) {
                    map.addElement(x, y, new PotionSeller());
                } else if (cell.contains("Binoculars")) {
                    map.addElement(x, y, new Binoculars());
                } else if (cell.contains("Hero")) {
                    map.getHero().x = x;
                    map.getHero().y = y;
                } else if (cell.contains("Helper")) {
                    map.addElement(x, y, new Helper());
                } else if (cell.contains("Con")) {
                    map.addElement(x, y, new Creep(getHealthPoints(cell), getDamage(cell), getExp(cell)));
                } else if (cell.contains("v")) {
                    map.addElement(x, y, new Water());
                } else if (cell.contains("Woodman")) {
                    map.addElement(x, y, new Woodman(getHealthPoints(cell), getDamage(cell), getExp(cell)));
                }else if (cell.contains("Phyrion")){
                    map.addElement(x, y, new Phyrion( getHealthPoints(cell), getDamage(cell), getExp(cell)));
                }else if (cell.contains("Exp")){
                    map.addElement(x, y, new ExperienceBook(getMoney(cell)));
                } else if (cell.contains("b")){
                    map.addElement(x, y, new Bog());
                }else if (cell.contains("Slark")){
                    map.addElement(x, y, new Slark( getHealthPoints(cell), getDamage(cell), getExp(cell)));
                }
            }
        }
    }

    private static int getHealthPoints(String cell) {
        int health = 50;
        String[] params = cell.split(" ");
        if (params.length > 1) {
            return Integer.parseInt(params[1]);
        }
        return health;
    }

    private static int getDamage(String cell) {
        int damage = 10;
        String[] params = cell.split(" ");
        if (params.length > 2) {
            return Integer.parseInt(params[2]);
        }
        return damage;
    }

    private static int getExp(String cell) {
        int exp = 10;
        String[] params = cell.split(" ");
        if (params.length > 3) {
            return Integer.parseInt(params[3]);
        }
        return exp;
    }

    private static int getMoney(String cell) {
        int coins = 50;
        String[] params = cell.split(" ");
        if (params.length > 1) {
            return Integer.parseInt(params[1]);
        }
        return coins;
    }
}

