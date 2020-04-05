package com.ninja.game;

import java.util.ArrayList;
import java.util.List;

public class Notification {


    private List<String> notification = new ArrayList<>();
    private List<String> badNotification = new ArrayList<>();

    public void addBadNotification(String message){
        badNotification.add(message);
    }

    public void addNotification(String message) {
        notification.add(message);
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
