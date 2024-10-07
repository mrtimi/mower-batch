package com.mrtimi.mower.batch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InstructionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "G, LEFT",
            "D, RIGHT",
            "A, FORWARD"
    })
    @DisplayName("should return expected instruction")
    void fromValueTest(char value, Instruction expectedDirection) {
        // WHEN
        Instruction direction = Instruction.fromValue(value);
        // THEN
        assertThat(direction).isEqualTo(expectedDirection);
    }

    @Test
    @DisplayName("should throw exception if instruction value is invalid")
    void shouldThrowException() {
        assertThatThrownBy(() -> Instruction.fromValue('*'))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid instruction value, input value is: *");
    }
}