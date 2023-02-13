package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Wager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface WinningCalculationService {

    public int calculateBetAmountWin(int betAmount, int odds);

    public List<Wager> dispenseWinningAmount(int winningAmount);

}
