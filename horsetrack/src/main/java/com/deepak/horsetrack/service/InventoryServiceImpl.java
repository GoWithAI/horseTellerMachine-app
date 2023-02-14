package com.deepak.horsetrack.service;

import com.deepak.horsetrack.config.ApplicationProperties;
import com.deepak.horsetrack.model.Inventory;
import com.deepak.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
public class InventoryServiceImpl implements  InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ApplicationProperties properties;

    @Override
    public void restock() {
        String restockAmount = properties.getTm().getRestockAmount();
        inventoryRepository.findAll().stream().forEach( i -> {
            i.setBillCount(Integer.parseInt(restockAmount));
        inventoryRepository.save(i);
        } );
    }

    @Override
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventory(int denomination) {
        return inventoryRepository.findByDenominationEquals(denomination);
    }

    @Override
    public boolean isSufficentFundsAvailable(int winAmount) {
        List<Inventory> inventories = inventoryRepository.findAll();
        //we can extract below statement
        BiFunction<Integer, Inventory, Integer> availableAmount =  (total, inventory) -> total + (inventory.getDenomination() * inventory.getBillCount());
        Integer totalAvailableAmount = inventories.stream().reduce(0, availableAmount, Integer::sum);
        return (totalAvailableAmount -winAmount) >= 0 ;
    }

    @Override
    public void reduceInventory(int denomination, int billCount) {
        Inventory inventory = inventoryRepository.findByDenominationEquals(denomination);
        int currentBillCount = inventory.getBillCount();
        inventory.setBillCount(currentBillCount - billCount);
        inventoryRepository.save(inventory);
        }

}
