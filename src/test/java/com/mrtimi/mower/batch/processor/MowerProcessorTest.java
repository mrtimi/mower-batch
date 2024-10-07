package com.mrtimi.mower.batch.processor;

import com.mrtimi.mower.batch.model.Instruction;
import com.mrtimi.mower.batch.model.Lawn;
import com.mrtimi.mower.batch.model.Mower;
import com.mrtimi.mower.batch.model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.mrtimi.mower.batch.model.Direction.EAST;
import static com.mrtimi.mower.batch.model.Direction.NORTH;
import static com.mrtimi.mower.batch.model.Instruction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MowerProcessorTest {

    @ParameterizedTest
    @MethodSource("getMowerDefinitionWithExpectedFinalPosition")
    @DisplayName("should return expected final mower position")
    void shouldReturnExpectedFinalMowerPosition(Mower mower, Position expectedFinalPosition) {
        // GIVEN
        Lawn lawn = new Lawn(5, 5);
        MowerProcessor mowerProcessor = new MowerProcessor(lawn);
        // WHEN
        Position finalPosition = mowerProcessor.process(mower);
        // THEN
        assertThat(finalPosition).isEqualTo(expectedFinalPosition);
    }

    private static Stream<Arguments> getMowerDefinitionWithExpectedFinalPosition() {
        return Stream.of(
                arguments(createFirstMower(), new Position(1, 3, NORTH)),
                arguments(createSecondMower(), new Position(5, 1, EAST))
        );
    }

    private static Mower createFirstMower() {
        Position intialPosition = new Position(1, 2, NORTH);
        List<Instruction> instructions = List.of(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD);
        return new Mower(intialPosition, instructions);
    }

    private static Mower createSecondMower() {
        Position intialPosition = new Position(3, 3, EAST);
        List<Instruction> instructions = List.of(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD);
        return new Mower(intialPosition, instructions);
    }


}