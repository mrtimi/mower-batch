package com.mrtimi.mower.batch.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Direction {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    private final char value;

    public static Direction fromValue(char value) {
        for (Direction direction : Direction.values()) {
            if (value == direction.value) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction value, input value is: " + value);
    }
}
