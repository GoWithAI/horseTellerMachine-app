package com.deepak.horsetrack.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Command {


    private String commandKey;

    private String commandValue;

    private boolean isValidCommand = false;

}
