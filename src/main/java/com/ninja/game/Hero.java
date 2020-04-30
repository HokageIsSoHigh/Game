package com.ninja.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hero implements Element, Enemy {
    public int x;
    public int y;
    public int view = 2;
    public int health = 100;
    public int curHealth = this.health;
    public int money = 0;
    public int damage = 25;
    public int exp = 0;
    public int lvl = 1;
    public float armor = 1F;
    public Notification notification;
    public Buff buff;
    public List<Slot> items = new ArrayList();
    public Set<Coordinates> seen = new HashSet();
    public Set<Flag> flags = new HashSet<>();
    public String symbol;

    public Hero(Notification notification, Buff buff) {
        this.notification = notification;
        this.buff = buff;
    }


    public boolean canSee(int x, int y, Map map) {
        double howFar = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        if (howFar - 0.3 <= view()) {
            for (Coordinates c : vision(this.x, this.y, x, y)) {
                if (map.getElement(c.x, c.y) instanceof Tree) {
                    return false;
                }
            }
            this.seen.add(new Coordinates(x, y));
            return true;
        }
        return false;
    }

    public boolean hasSeen(int x, int y) {
        return seen.contains(new Coordinates(x, y));
    }

    public String see(int x, int y, Map map) {
        if (canSee(x, y, map)) {
            return "visible";
        }
        if (hasSeen(x, y)) {
            return "seen";
        } else {
            return "fog";
        }
    }

    public int view() {
        for (Slot s : items) {
            if (s.getName().equals("Binoculars")) {
                return view + 3;
            }
        }
        return view;
    }


    private List<Coordinates> vision(float x1, float y1, float x2, float y2) {
        List<Coordinates> coordinates = new ArrayList<>();
        int i, L, xStart, yStart, xEnd, yEnd;
        float dX, dY;
        float[] x = new float[Map.WIDTH];
        float[] y = new float[Map.HEIGHT];
        xStart = Math.round(x1);
        yStart = Math.round(y1);
        xEnd = Math.round(x2);
        yEnd = Math.round(y2);
        L = Math.max(Math.abs(xEnd - xStart), Math.abs(yEnd - yStart));
        dX = (x2 - x1) / L;
        dY = (y2 - y1) / L;
        i = 0;
        x[i] = x1;
        y[i] = y1;
        i++;
        while (i < L) {
            x[i] = x[i - 1] + dX;
            y[i] = y[i - 1] + dY;
            if (coordinates.isEmpty()) {
                coordinates.add(new Coordinates(Math.round(x[i - 1]), Math.round(y[i - 1])));
            }
            coordinates.add(new Coordinates(Math.round(x[i]), Math.round(y[i])));
            i++;
        }
        return coordinates;
    }


    public void collectSlot(Slot slot) {
        items.add(slot);
    }

    public Slot getSlot(int id) {
        for (Slot s : items) {
            if (s.getID() == id) {
                return s;
            }

        }
        return null;
    }

    @Override
    public int health() {
        return this.health;
    }

    @Override
    public int damage() {
        return this.damage;
    }

    @Override
    public int exp() {
        return this.exp;
    }

    @Override
    public String getCSS() {
        return "Hero";
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            damage = damage - ((int) armor / 2);
            curHealth -= damage;
        }

    }

    @Override
    public int giveDamage() {
        return damage();
    }

    @Override
    public String getSymbol() {
        symbol = "\uD83E\uDDCD";
        return symbol;
    }

    @Override
    public String getHoverName() {
        return "Your hero";
    }

    public void deleteSlot(int id) {
        int idx = 0;
        while (idx < items.size()) {
            if (idx == id) {
                notification.addNotification("Successful");
                items.remove(idx);
                break;
            } else {
                idx++;
            }
        }
    }

    public void dropSlot(int id, Map map) {
        int idx = 0;
        while (idx < items.size()) {
            if (idx == id) {
                if (items.get(id) instanceof Element) {
                    map.addElement(this.x, this.y, (Element) items.get(id));
                    notification.addNotification("Successful");
                    items.remove(idx);
                }
                notification.addNotification("Failed!");
                break;
            } else {
                idx++;
            }
        }
    }

    public boolean searchSlot(String name) {
        for (Slot s : items) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public void collectMoney(int money) {
        this.money += money;
    }

    public void heroBuy(NPC seller, int id) {
        for (Equipment e : seller.equipment()) {
            if (this.money >= e.price) {
                if (e.slot.getID() == id) {
                    if (searchSlot(e.slot.getName()) && e.slot.heal() <= 0) {
                        notification.addBadNotification("You already have " + e.slot.getName());
                        break;
                    }
                    this.money -= e.price;
                    items.add(e.slot);
                    notification.addNotification("You buy " + e.slot.getName());
                }
            }
            if (e.slot.getID() == id) {
                if (this.money < e.price) {
                    notification.addBadNotification("You don't have enough coins!");
                }
            }
        }

    }


    public void collectExp(int exp) {
        this.exp += exp;
        if (this.exp >= 100) {
            this.exp = Math.abs(this.exp - 100);
            this.lvl++;
            int increasingArmorFormula = this.lvl * 4;
            int increasingHpFormula = this.lvl * 10;
            this.health += increasingHpFormula;
            this.armor += increasingArmorFormula;
            curHealth = this.health;
        }
    }

    public void drinkingHealthPotion(int heal, int idx) {
        if (curHealth + heal <= this.health) {
            curHealth += heal;
        } else {
            curHealth = this.health;

        }
        notification.addNotification("+" + heal + " hp.");
        notification.addSound("drink.mp3");
        deleteSlot(idx);
    }

    public int calculateBaseDamage() {
        int totalDamage = damage;
        for (Slot s : items) {
            totalDamage += s.damage();
        }
        totalDamage += this.lvl * 2;
        return totalDamage;
    }

    public int calculateAttackDamage() {
        int damage = calculateBaseDamage();
        if (searchSlot("BOA")) {
            if (buff.criticalStrike()) {
                damage += getSlot(228).damage() * 2;
                notification.addNotification("Critical strike!");
            }
        }
        return damage;
    }

    public void addBuff(int time, Slot slot, int id) {
        buff.addBuff(time, slot);
        if (slot.heal() > 0) {
            if (curHealth + slot.heal() <= this.health) {
                curHealth += slot.heal();
            } else {
                curHealth = this.health;
            }
            notification.addNotification("+" + slot.heal() + " hp.");
        }
        deleteSlot(id);

    }


    public void addFlag(Flag flag) {
        flags.add(flag);
    }

    public void removeFlag(Flag flag) {
        flags.remove(flag);
    }


    public boolean canMove() {
        for (Flag s : flags) {
            if (s.getWorkName().equals("inBattle")) {
                return false;
            }
        }
        return true;
    }

    public Set<Flag> getFlags() {
        return flags;
    }

    public void escape() {
        flags.removeAll(flags);
    }

    public boolean inBattle() {
        for (Flag s : flags) {
            if (s.getWorkName().equals("inBattle")) {
                return true;
            }
        }
        return false;
    }

    public boolean drunkVodka() {
        for (UsedItems b : buff.getBuffList()) {
            if (b.slot.getName().equals("Vodka")) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> prevStats(int health, int damage, int money, int lvl, int exp, int armor) {
        List<Integer> stats = new ArrayList<>();
        stats.add(health);
        stats.add(damage);
        stats.add(money);
        stats.add(lvl);
        stats.add(exp);
        stats.add(armor);
        return stats;
    }
}
