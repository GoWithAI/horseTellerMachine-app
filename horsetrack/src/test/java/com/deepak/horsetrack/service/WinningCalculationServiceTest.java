package com.deepak.horsetrack.service;

import com.deepak.horsetrack.data.TCDefaultDataCacheTest;
import com.deepak.horsetrack.model.Wager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.BiFunction;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WinningCalculationServiceTest {

  @InjectMocks
  WinningCalculationServiceImpl winningCalculationServiceImpl;

  @Mock
  private InventoryService inventoryService;

  @Test
  public void testCalculateAmountWon() {
    int betAmount = 55;
    int odds = 5;
    int expected = 275;
    int actual = winningCalculationServiceImpl.calculateBetAmountWin(betAmount, odds);
    Assertions.assertEquals(expected,actual);
  }

  @Test
  public void testDespenseWinningAmount() {
    int betAmount = 55;
    int odds = 5;
    int expected = 275;
    int winningAmount = winningCalculationServiceImpl.calculateBetAmountWin(betAmount, odds);

    when(inventoryService.getInventory()).thenReturn(TCDefaultDataCacheTest.getInventory());
    when(inventoryService.getInventory(anyInt())).thenReturn(TCDefaultDataCacheTest.getInventory().get(0));
    List<Wager> wagers = winningCalculationServiceImpl.dispenseWinningAmount(winningAmount);
    BiFunction<Integer, Wager, Integer> availableAmountFun =  (total, inventory) -> total + (inventory.getDenomination() * inventory.getBillCount());
    Integer totalAvailableAmount = wagers.stream().reduce(0, availableAmountFun, Integer::sum);

    Assertions.assertEquals(expected,totalAvailableAmount);
  }

}