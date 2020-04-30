package com.ninja.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Notification {


    private List<String> notification = new ArrayList<>();
    private List<String> badNotification = new ArrayList<>();
    private List<String> sounds = new ArrayList<>();

    public void addBadNotification(String message){
        badNotification.add(message);
    }

    public void addNotification(String message) {
        notification.add(message);
    }

    public void addSound(String sound) {
        sounds.add("sounds/" + sound);
    }

    public List<String> getSounds() {
        List<String> sound = sounds;
        sounds = new ArrayList<>();
        return sound;
    }

    public List<String> getNotification() {
        List<String> list = notification;
        notification = new ArrayList<>();
        return list;
    }

    public List<String> getBadNotification() {
        List<String> list = badNotification;
        badNotification = new ArrayList<>();
        return list;
    }
}
