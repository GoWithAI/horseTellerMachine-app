package com.deepak.horsetrack.controller;

import com.deepak.horsetrack.model.Command;
import com.deepak.horsetrack.service.ConfigService;
import com.deepak.horsetrack.service.HorseService;
import com.deepak.horsetrack.service.InventoryService;
import com.deepak.horsetrack.service.MessageService;
import com.deepak.horsetrack.validationService.UserInputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayMainScreen implements  DisplayControl{

    private boolean quit = false;
    @Autowired
    ConfigService configService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserInputValidation inputValidation;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    HorseService horseService;

    @Override
    public void initLoadStartupData() {
        configService.startupLoad();
    }

    @Override
    public void startUpDefaultScreen() {
        messageService.startupDisplay();
    }

    @Override
    public boolean quit() {
        return quit;
    }

    @Override
    public void winnerHorse(int horseNumber) {
        if (horseService.isValidHorseNumber(horseNumber)) {
            horseService.setWinnerHorse(horseNumber);
            messageService.displayInventory();
            messageService.displayHorse();
        } else {
            System.out.println("Invalid horse number:" + " " + horseNumber);
        }
    }

    @Override
    public void restock() {
        inventoryService.restock();
        messageService.displayInventory();
    }

    @Override
    public void betHourse(int horseNumber, int betAmount) {
        System.out.println("PENDING BET Horse");
    //validate horse
        //validate winner or loser
        // calculateAmountWin
    }

    @Override
    public void executeInputCommand(String commandStr) {
        System.out.println("User Command : "+ commandStr);

        Command command = null;
        try{
            command = inputValidation.validateInputCommand(commandStr);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid Command :" + " " + commandStr);
            return;
        }

        if(command.getCommandKey().equalsIgnoreCase("Q"))
            quit = true;

        if (command.getCommandKey().equalsIgnoreCase("r"))
            restock();

        if (command.getCommandKey().equalsIgnoreCase("w"))
            winnerHorse(Integer.parseInt(command.getCommandValue()));

        if (command.getCommandKey().equalsIgnoreCase("ub"))
            betHourse(Integer.parseInt(command.getCommandKey()), Integer.parseInt(command.getCommandValue()));

    }
}
