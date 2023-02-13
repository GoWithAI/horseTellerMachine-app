package com.deepak.horsetrack.service;

public interface HorseService {

    public void setWinnerHorse(int hourseNumber);

    public boolean isValidHorseNumber(int hourseNumber);

    public String getHorseName(int hourseNumber);

    public int getHorseOdds(int hourseNumber);

    public boolean isHorseWinner(int hourseNumber);


}
