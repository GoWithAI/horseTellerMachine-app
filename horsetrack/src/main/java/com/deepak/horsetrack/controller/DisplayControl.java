package com.deepak.horsetrack.controller;

public interface DisplayControl {

    public void initLoadStartupData();

    void startUpDefaultScreen();

    public boolean quit();

    public void winnerHorse(int horseNumber);

    public void restock();

    public void betHourse(int horseNumer, int betAmount);

    public void executeInputCommand(String command);
}
