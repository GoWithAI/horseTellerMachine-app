package com.deepak.horsetrack.service;

import com.deepak.horsetrack.converter.CommandStringToModel;
import com.deepak.horsetrack.exception.InvalidCommandException;
import com.deepak.horsetrack.model.Command;
import org.springframework.stereotype.Service;

import java.util.*;


// we need to add string in constant for now kept as is
@Service
public class CommandServiceImpl implements CommandService{

    // In case of command either we can use SWITCH OR polymorphism if
    public List<String> supprotedCommand(){
        List<String> stringList = Arrays.asList("q", "r", "w");
        List<String> commands = Collections.unmodifiableList(stringList);
        return commands;
    }

    @Override
    public Command parseAndSetCommand(String command) throws  InvalidCommandException{
        Command commandModel = CommandStringToModel.converterFun.apply(command);
        boolean match = supprotedCommand().stream().anyMatch( s -> s.equalsIgnoreCase(commandModel.getCommandKey()));
        if(match && commandModel.getCommandKey().equalsIgnoreCase("W") && !validateWinnerValue(commandModel))
            throw new InvalidCommandException("");
        else if (match) {
            return commandModel;
        }

        if(!match && !parseBetCommand(commandModel))
            throw new InvalidCommandException("");
        else
            commandModel.setCommandKey("UB");

        return commandModel;
    }

    private boolean validateWinnerValue(Command commandModel) {
       return commandModel.getCommandValue().matches("[1-9]");
    }

    private boolean parseBetCommand(Command commandModel) {
      return  commandModel.getCommandKey().matches("[1-9]") &&  commandModel.getCommandValue().matches("\\d+");
    }

}
