package com.deepak.horsetrack.validationService;


import com.deepak.horsetrack.exception.InvalidCommandException;
import com.deepak.horsetrack.model.Command;
import org.springframework.stereotype.Service;

// It always best practice to validate data in Bussiness/Service layer
// Bussiness Layer try to put core domain logic
public interface UserInputValidation {

    public Command validateInputCommand(String command) throws InvalidCommandException;

    public void parseInputCommand(String command);

}
