package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Inventory;
import com.deepak.horsetrack.model.Wager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WinningCalculationServiceImpl implements  WinningCalculationService{

    @Autowired
    private InventoryService inventoryService;

    @Override
    public int calculateBetAmountWin(int betAmount, int odds) {
        return betAmount * odds;
    }

    @Override
    public List<Wager> dispenseWinningAmount(int winningAmount) {
        List<Wager> wagerList = new ArrayList<>();

        List<Integer> demoniationList = inventoryService.getInventory().stream()
                .sorted(Comparator.comparing(Inventory::getDenomination).reversed())
                .map(Inventory::getDenomination).collect(Collectors.toList());

        for(Integer demo :demoniationList){

        }

        return wagerList;
    }
}
