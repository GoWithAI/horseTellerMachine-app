package com.deepak.horsetrack.service;

import com.deepak.horsetrack.config.ApplicationProperties;
import com.deepak.horsetrack.model.Horse;
import com.deepak.horsetrack.model.Inventory;
import com.deepak.horsetrack.repository.HorseRepository;
import com.deepak.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;


@Service
public class MessageService {

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ApplicationProperties properties;

    public void startupDisplay(){
        displayInventory();
        displayHorse();
    }

    public void displayInventory() {
        String currencySymbol = properties.getTm().getCurrencySymbol();
        System.out.println("Inventory:");
        List<Inventory> inventories = inventoryRepository.findAll();
        Consumer<Inventory> printInv =inventory -> System.out.println(currencySymbol+inventory.getDenomination()+","+inventory.getBillCount());
        inventories.forEach(printInv);
    }

    public void displayHorse() {
        List<Horse> horse = horseRepository.findAll();
        System.out.println("Horses:");
        Consumer<Horse> printHorseDetails = (h) -> System.out.println(h.getHorseNumber()+","+h.getHorseName()+","+h.getOdds()+","+h.getRaceStatus().toString().toLowerCase());
        horse.forEach(printHorseDetails);
    }

    public void printHorses() {
        Iterable<Horse> horses = horseRepository.findAll();
        System.out.println("Horses:");
        horses.forEach((horse) -> {
            System.out.println(horse.getHorseNumber()
                    +","+horse.getHorseName()
                    +","+horse.getOdds()
                    +","+horse.getRaceStatus().toString().toLowerCase());
        });
    }

    public void printInventory() {Iterable<Inventory> inventories = inventoryRepository.findAll();
        System.out.println("Inventory: ");
        inventories.forEach((inventory) -> {
            System.out.println("$"
                    +inventory.getDenomination()
                    +","+inventory.getBillCount());
        });

    }
}
