package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Inventory;

import java.util.List;

public interface InventoryService {

    public void restock();

    public List<Inventory> getInventory();

    public Inventory getInventory(int denomination);

    public boolean isSufficentFundsAvailable();

    public void reduceInventory(int denomination, int amount);

}
