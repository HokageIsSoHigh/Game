package com.ninja.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Buff {

    List<UsedItems> buffList = new ArrayList();

    public boolean criticalStrike() {
        Random rand = new Random();
        int chance = rand.nextInt(11);
        if (chance <= 3) {
            return true;
        }
        return false;
    }

    public void addBuff(int time, Slot slot){
        buffList.add(new UsedItems(time, slot));
    }

    public List<UsedItems> getBuffList(){
        return buffList;
    }

    public UsedItems getBuff(String name){
        for (UsedItems it : getBuffList()){
            if (it.slot.getName().equals(name)){
                return it;
            }
        }
        return null;
    }

    public boolean miss(int chance){
        Random rand = new Random();
        int randNumber = rand.nextInt(11);
        if (randNumber <= chance) {
            return true;
        }
        return false;
    }

    public void removeBuff(String name){
        buffList.removeIf(usedItems -> usedItems.slot.getName().equals(name));
    }

    public boolean isEmpty(){
        if (getBuffList().size() == 0){
            return true;
        }
        return false;
    }

    public boolean isInList(String name){
        for (UsedItems it : getBuffList()){
            if (it.slot.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean timeOver(String name){
        if (isInList(name)){
            if (getBuff(name).time == 0){
                return true;
            }
        }
        return false;
    }

}
