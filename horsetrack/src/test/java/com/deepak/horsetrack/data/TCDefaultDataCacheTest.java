package com.deepak.horsetrack.data;

import com.deepak.horsetrack.model.Horse;
import com.deepak.horsetrack.model.Inventory;
import com.deepak.horsetrack.model.RaceStatus;

import java.util.Arrays;
import java.util.List;

public class TCDefaultDataCacheTest {

    public static List<Horse> getRaceHorses(){
       return Arrays.asList(
        new Horse(1, "That Darn Gray Cat", 5, RaceStatus.WON),
        new Horse(2, "Fort Utopia", 10, RaceStatus.LOST),
        new Horse(3, "Count Sheep", 9, RaceStatus.LOST),
        new Horse(4, "Ms Traitour", 4, RaceStatus.LOST),
        new Horse(5, "Real Princess", 3, RaceStatus.LOST),
        new Horse(6, "Pa Kettle", 5, RaceStatus.LOST),
        new Horse(7, "Gin Stinger", 6, RaceStatus.LOST)
        );
    }

    public static List<Inventory> getInventory(){
        return Arrays.asList(
        new Inventory(1,10),
        new Inventory(5,10),
        new Inventory(10,10),
        new Inventory(20,10),
        new Inventory(100,10)
        );
    }

}
