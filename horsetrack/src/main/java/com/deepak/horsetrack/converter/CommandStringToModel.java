package com.deepak.horsetrack.converter;

import com.deepak.horsetrack.model.Command;

import java.util.Arrays;
import java.util.function.Function;

public class CommandStringToModel {

    public static Function<String, Command> converterFun = command -> {
        String[] commentKV=  Arrays.stream(command.split(" ")).map(String::trim).toArray(String[]::new);
        String key = commentKV[0];
        String value =  commentKV.length ==2 ? commentKV[1] : "";
        return new Command(key, value, false);
    };
}
