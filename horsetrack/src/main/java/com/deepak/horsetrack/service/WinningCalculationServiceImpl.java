package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Inventory;
import com.deepak.horsetrack.model.Wager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        Wager wager;

        List<Integer> demoniationList = inventoryService.getInventory().stream()
                .sorted(Comparator.comparing(Inventory::getDenomination).reversed())
                .map(Inventory::getDenomination).collect(Collectors.toList());

        for(Integer demoni :demoniationList){
            boolean wagerAdded = false;
                for(int billCount = inventoryService.getInventory(demoni).getBillCount(); billCount >0 ; billCount-- ){
                       int availableAmountOfTotalDemoni = billCount * demoni;

                       if(winningAmount >= availableAmountOfTotalDemoni){
                           wager = new Wager(demoni, billCount);
                           wagerList.add(wager);
                           winningAmount = winningAmount - availableAmountOfTotalDemoni;
                           wagerAdded=true;
                           break;
                       }
                }
                if(!wagerAdded){
                    wager = new Wager(demoni,0);
                    wagerList.add(wager);
                }
        }
        reduceInventory(wagerList);
        return wagerList;
    }

    private void reduceInventory(List<Wager> wagerList){
        wagerList.forEach(k-> {
            inventoryService.reduceInventory(k.getDenomination(), k.getBillCount());
        });
    }
}
