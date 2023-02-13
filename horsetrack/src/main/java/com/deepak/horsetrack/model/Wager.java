package com.deepak.horsetrack.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Wager {
    private final int denomination;
    private final int billCount;

}
