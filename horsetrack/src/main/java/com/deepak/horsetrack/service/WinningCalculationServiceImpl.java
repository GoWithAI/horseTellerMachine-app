package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Wager;

import java.util.ArrayList;
import java.util.List;

public class WinningCalculationServiceImpl implements  WinningCalculationService{
    @Override
    public int calculateBetAmountWin(int betAmount, int odds) {
        return betAmount * odds;
    }

    @Override
    public List<Wager> dispenseWinningAmount(int winningAmount) {
        List<Wager> wagerList = new ArrayList<>();
        return wagerList;
    }
}
