package com.ninja.game;


public class Map {

    public static int WIDTH = 26;
    public static int HEIGHT = 40;
    private Element[][] elements;

    public Notification notification;
    private Hero hero;
    public Buff buff;


    public Map() {
        elements = new Element[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                elements[x][y] = new Land();
            }
        }
        buff = new Buff();
        notification = new Notification();
        hero = new Hero(notification, buff);



    }

    public Hero getHero() {
        return hero;
    }


    public NPC getNPC(Hero hero) {
        if (elements[hero.x][hero.y] instanceof NPC) {
            return (NPC) elements[hero.x][hero.y];
        } else {
            return null;
        }
    }

    public Enemy getEnemy(Hero hero) {
        if (elements[hero.x][hero.y] instanceof Enemy) {
            return (Enemy) elements[hero.x][hero.y];
        } else {
            return null;
        }

    }

    public Element getElement(int x, int y) {
        return elements[x][y];
    }

    public void addElement(int x, int y, Element element) {
        elements[x][y] = element;
    }

    public void move(String direction) {
        int x = hero.x;
        int y = hero.y;


        if (direction.equals("left")) {
            x--;
        }
        if (direction.equals("right")) {
            x++;
        }
        if (direction.equals("down")) {
            y++;
        }
        if (direction.equals("top")) {
            y--;
        }

        Element el = getElement(x, y);
        if (el instanceof Slot) {
            hero.collectSlot((Slot) el);
            notification.addNotification("You picked up " + el.getName());
            addElement(x, y, new Land());
        }

        el = getElement(x, y);
        if (el instanceof Land) {
            hero.x = x;
            hero.y = y;
        }

        if (el instanceof Trap) {
            hero.curHealth -= 13;
            addElement(x, y, new Land());
            notification.addBadNotification("You stumbled upon a trap!");
        }

        if (el instanceof Chest) {
            hero.collectMoney(((Chest) el).money);
            notification.addNotification("You collected " + ((Chest) el).money + " coins!");
            addElement(x, y, new Land());
            hero.x = x;
            hero.y = y;
        }

        if (el instanceof WeaponSeller) {
            hero.x = x;
            hero.y = y;
            notification.addNotification("You met a seller");
        }
        if (el instanceof PotionSeller) {
            hero.x = x;
            hero.y = y;
            notification.addNotification("You met a seller");
        }

        if (el instanceof ExperienceBook) {
            hero.collectExp(((ExperienceBook) el).expInBook);
            addElement(x, y, new Land());
            notification.addNotification("You found an experience book and got " + ((ExperienceBook) el).expInBook + " exp");
        }

        if (el instanceof Enemy) {
            hero.x = x;
            hero.y = y;
            notification.addBadNotification("You met a " + el.getName());
        }
        if (el instanceof Helper) {
            hero.x = x;
            hero.y = y;
            notification.addNotification("You met helper");
        }
        if (el instanceof Water) {
            hero.x = x;
            hero.y = y;
        }
        if (el instanceof Bog){
            hero.x = x;
            hero.y = y;
        }

    }

    public void battle() {
        Enemy enemyName = getEnemy(hero);
        if (hero.buff.isEmpty()) {
            attack(enemyName, hero.calculateAttackDamage());
        }
        if (!hero.buff.isEmpty()) {
            if (hero.buff.isInList("Vodka")) {
                if (!hero.buff.timeOver("Vodka")) {
                    if (!hero.buff.miss(5)) {
                        attack(enemyName, 0);
                    } else {
                        attack(enemyName, hero.calculateAttackDamage());
                    }
                    hero.buff.getBuff("Vodka").time--;
                    if (hero.buff.timeOver("Vodka")) {
                        hero.buff.removeBuff("Vodka");
                    }
                }
            }
        }
        if (enemyName.health() <= 0) {
            notification.addNotification(enemyName.getName() + " is dead!");
            notification.addNotification("You collect " + enemyName.exp() + " exp");
            hero.collectExp(enemyName.exp());
            addElement(hero.x, hero.y, new Land());
        }
    }

    public void attack(Enemy enemyName, int dmg) {
        enemyName.takeDamage(dmg);
        hero.takeDamage(enemyName.giveDamage());
        notification.addNotification("You hit " + enemyName.getName() + " with " + dmg + " damage");
        int enemyDamage = enemyName.giveDamage();
        notification.addBadNotification(enemyName.getName() + " hit you with " + enemyDamage + " damage");
    }

}

