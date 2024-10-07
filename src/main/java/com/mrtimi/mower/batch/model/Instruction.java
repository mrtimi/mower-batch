package com.mrtimi.mower.batch.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Instruction {
    LEFT('G'),
    RIGHT('D'),
    FORWARD('A');
    private final char value;

    public static Instruction fromValue(char value) {
        for (Instruction instruction : Instruction.values()) {
            if (value == instruction.value) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid instruction value, input value is: " + value);
    }
}
