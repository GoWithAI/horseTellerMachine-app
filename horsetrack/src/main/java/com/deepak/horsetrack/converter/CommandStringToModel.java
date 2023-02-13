package com.deepak.horsetrack.converter;

import com.deepak.horsetrack.model.Command;

import java.util.Arrays;
import java.util.function.Function;

public class CommandStringToModel {

    public static Function<String, Command> converterFun = command -> {
        String[] commentKV=  Arrays.stream(command.split(" ")).map(String::trim).toArray(String[]::new);
        return new Command(commentKV[0], commentKV[1], false);
    };
}
