package com.ninja.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Hero implements Element, Enemy {
    public int x;
    public int y;
    public int view = 2;
    public int health = 50;
    public int curHealth = this.health;
    public int money = 0;
    public int damage = 5;
    public int exp = 0;
    public int lvl = 1;
    public float armor = 1F;
    public Notification notification;
    public Buff buff;
    public List<Slot> items = new ArrayList();
    public String symbol;

    public Hero(Notification notification, Buff buff) {
        this.notification = notification;
        this.buff = buff;
    }


    public boolean canSee(int x, int y, Map map) {
        double howFar = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        if (howFar - 0.3 <= view) {
            for (Coordinates c : vision(this.x, this.y, x, y)) {
                if (map.getElement(c.x, c.y) instanceof Tree) {
                    return false;
                }

            }
            return true;
        }
        return false;
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
        while (i <= L) {
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

    public List<Coordinates> vision(int x, int y) {

        int xStart = this.x;
        int yStart = this.y;
        int xEnd = x;
        int yEnd = y;

        int xLine = xEnd - xStart;
        int yLine = yEnd - yStart;
        float dx = xLine / L(xLine, yLine);
        float dy = yLine / L(xLine, yLine);

        float newX = (float) (xStart * 0.5 * sign(dx));
        float newY = (float) (yStart * 0.5 * sign(dy));

        List<Coordinates> coordinates = new ArrayList<>();

        for (int i = 0; i <= L(xLine, yLine); i++) {
            newX = newX + dx;
            newY = newY + dy;

            coordinates.add(new Coordinates(Math.round(newX), Math.round(newY)));
        }

        return coordinates;

    }

    private int L(int xLine, int yLine) {
        return Math.max(xLine, yLine);
    }

    private int sign(float d) {
        if (d < 0) {
            return -1;
        }
        if (d > 0) {
            return 1;
        } else {

            return 0;
        }
    }

    private void print() {
        for (Coordinates c : vision(6, 6)) {
            System.out.print("x: " + c.x + "y: " + c.y);
        }
    }


}
