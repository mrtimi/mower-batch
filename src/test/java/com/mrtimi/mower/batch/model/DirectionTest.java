package com.mrtimi.mower.batch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {
    @ParameterizedTest
    @CsvSource(value = {
            "N, NORTH",
            "E, EAST",
            "W, WEST",
            "S, SOUTH"
    })
    @DisplayName("should return expected direction")
    void fromValueTest(char value, Direction expectedDirection) {
        // WHEN
        Direction direction = Direction.fromValue(value);
        // THEN
        assertThat(direction).isEqualTo(expectedDirection);
    }

    @Test
    @DisplayName("should throw exception if direction value is invalid")
    void shouldThrowException() {
        assertThatThrownBy(() -> Direction.fromValue('*'))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid direction value, input value is: *");
    }

}