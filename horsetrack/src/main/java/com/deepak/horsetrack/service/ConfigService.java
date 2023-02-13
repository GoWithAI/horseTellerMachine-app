package com.deepak.horsetrack.service;

import com.deepak.horsetrack.data.TCDefaultDataCache;
import com.deepak.horsetrack.repository.HorseRepository;
import com.deepak.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// In real world we can featch data from Application Properties, From external centralized cloud storage,
// HashiCorp, from DBs, or for standalone from local machine cache or data storage structure
@Service
public class ConfigService {

   @Autowired
   private HorseRepository horseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;


    public void loadHorses(){
        horseRepository.saveAll(TCDefaultDataCache.getRaceHorses());
    }

    public void loadInventory() {
        inventoryRepository.saveAll(TCDefaultDataCache.getInventory());
    }

    public void startupLoad() {
        loadHorses();
        loadInventory();
    }

}
