package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Command;

public interface CommandService {

    public Command parseAndSetCommand(String command);

}
