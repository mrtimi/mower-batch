package com.mrtimi.mower.batch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MowerTest {

    private final Lawn lawn = new Lawn(5, 5);

    @Nested
    @DisplayName("moveForward() test")
    class MoveForwardTest {
        @ParameterizedTest
        @MethodSource("getMowerWithExpectedFinalPosition")
        @DisplayName("should move the mower according to the direction")
        void shouldMoveTheMower(Mower mower, Position expectedFinalPosition) {
            // WHEN
            mower.moveForward(lawn);
            // THEN
            assertThat(mower.getPosition()).isEqualTo(expectedFinalPosition);
        }

        private static Stream<Arguments> getMowerWithExpectedFinalPosition() {
            Mower northMower = new Mower(new Position(1, 1, Direction.NORTH), Collections.singletonList(Instruction.FORWARD));
            Position northMowerFinalPosition = new Position(1, 2, Direction.NORTH);
            Mower eastMower = new Mower(new Position(1, 2, Direction.EAST), Collections.singletonList(Instruction.FORWARD));
            Position eastMowerFinalPosition = new Position(2, 2, Direction.EAST);
            Mower westMower = new Mower(new Position(1, 2, Direction.WEST), Collections.singletonList(Instruction.FORWARD));
            Position westMowerFinalPosition = new Position(0, 2, Direction.WEST);
            Mower southMower = new Mower(new Position(1, 2, Direction.SOUTH), Collections.singletonList(Instruction.FORWARD));
            Position southMowerFinalPosition = new Position(1, 1, Direction.SOUTH);
            Mower mowerOutsideLawn = new Mower(new Position(6, 6, Direction.NORTH), Collections.singletonList(Instruction.FORWARD));

            return Stream.of(
                    arguments(northMower, northMowerFinalPosition),
                    arguments(eastMower, eastMowerFinalPosition),
                    arguments(westMower, westMowerFinalPosition),
                    arguments(southMower, southMowerFinalPosition),
                    arguments(mowerOutsideLawn, mowerOutsideLawn.getPosition())
            );
        }
    }

    @Nested
    @DisplayName("turnLeft test")
    class TurnLeftTest {
        @ParameterizedTest
        @CsvSource(value = {
                "NORTH, WEST",
                "WEST, SOUTH",
                "SOUTH, EAST",
                "EAST, NORTH"
        })
        void turnLeftTest(Direction direction, Direction expectedDirectionAfterTurnLeft) {
            // GIVEN
            Mower mower = new Mower(new Position(1, 1, direction), Collections.singletonList(Instruction.LEFT));
            // WHEN
            mower.turnLeft();
            // THEN
            assertThat(mower.getPosition().getDirection()).isEqualTo(expectedDirectionAfterTurnLeft);

        }
    }

    @Nested
    @DisplayName("turnRight test")
    class TurnRightTest {
        @ParameterizedTest
        @CsvSource(value = {
                "NORTH, EAST",
                "EAST, SOUTH",
                "SOUTH, WEST",
                "WEST, NORTH"
        })
        void turnRightTest(Direction direction, Direction expectedDirectionAfterTurnLeft) {
            // GIVEN
            Mower mower = new Mower(new Position(1, 1, direction), Collections.singletonList(Instruction.LEFT));
            // WHEN
            mower.turnRight();
            // THEN
            assertThat(mower.getPosition().getDirection()).isEqualTo(expectedDirectionAfterTurnLeft);

        }
    }

}