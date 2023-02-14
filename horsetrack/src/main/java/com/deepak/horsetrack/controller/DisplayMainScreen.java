package com.deepak.horsetrack.controller;

import com.deepak.horsetrack.model.Command;
import com.deepak.horsetrack.model.Wager;
import com.deepak.horsetrack.service.*;
import com.deepak.horsetrack.validationService.UserInputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Autowired
    WinningCalculationService calculateAmountWon;

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

        if (!(horseService.isValidHorseNumber(horseNumber))) {
            System.out.println("Invalid Horse Number:"+ horseNumber);
            return;
        }

        if (!(horseService.isHorseWinner(horseNumber))) {
            System.out.println("No Payout:"+ horseService.getHorseName(horseNumber));
            return;
        }

        int amountWin = calculateAmountWon.calculateBetAmountWin(betAmount, horseService.getHorseOdds(horseNumber));

        if (inventoryService.isSufficentFundsAvailable(amountWin)) {
            System.out.println("Payout:" + " " + horseService.getHorseName(horseNumber) + ", $" + amountWin);
            List<Wager> wagerList = calculateAmountWon.dispenseWinningAmount(amountWin);
            System.out.println("Dispensing:");
            wagerList.forEach(wager-> {
                System.out.println("$"
                        + wager.getDenomination()
                        + ","
                        + wager.getBillCount()
                );
            });
        } else {
            System.out.println("Insufficient Funds :" + " " + "$" + amountWin);
        }

        messageService.printInventory();
        messageService.printHorses();
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
