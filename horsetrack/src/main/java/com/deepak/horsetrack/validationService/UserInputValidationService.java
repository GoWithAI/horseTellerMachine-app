package com.deepak.horsetrack.validationService;

import com.deepak.horsetrack.exception.InvalidCommandException;
import com.deepak.horsetrack.model.Command;
import com.deepak.horsetrack.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserInputValidationService implements  UserInputValidation{

    @Autowired
    CommandService commandService;

    @Override
    public Command validateInputCommand(String commandStr) throws InvalidCommandException {
        if(StringUtils.isEmpty(commandStr))
            throw new InvalidCommandException("");
        try{
            return commandService.parseAndSetCommand(commandStr);
        }catch (InvalidCommandException ie){
            throw ie;
        }
    }

    @Override
    public void parseInputCommand(String command) {

    }
}
