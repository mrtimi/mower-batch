package com.mrtimi.mower.batch.reader;

import com.mrtimi.mower.batch.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import static com.mrtimi.mower.batch.model.Instruction.*;
import static org.assertj.core.api.Assertions.assertThat;

class MowerReaderTest {

    @Test
    @DisplayName("read method should initialize lawn and read Mower object when lawn is not yet initialized")
    void shouldInitializeLawnAndReadMowerObject() throws Exception {
        // GIVEN
        final Resource resource = new FileSystemResource("src/test/resources/test-input.txt");
        final Lawn lawn = new Lawn(0, 0);
        MowerReader mowerReader = new MowerReader(resource, lawn);
        // WHEN
        Mower mower = mowerReader.read();
        // THEN
        assertThat(lawn).extracting(
                Lawn::getWidth,
                Lawn::getHeight
        ).containsExactly(
                5, 4
        );
        Position mowerPosition = mower.getPosition();
        assertThat(mowerPosition).extracting(
                Position::getX,
                Position::getY,
                Position::getDirection
        ).containsExactly(
                1,
                2,
                Direction.NORTH
        );
        assertThat(mower.getInstructions()).containsExactly(
                LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD
        );
    }

    @Test
    @DisplayName("should read Mower object when lawn is initialized")
    void shouldReadMowerWhenLawnIsInitialized() throws Exception {
        // GIVEN
        final Resource resource = new FileSystemResource("src/test/resources/test-mower-input.txt");
        final Lawn lawn = new Lawn(0, 0);
        lawn.setHeight(1);
        lawn.setWidth(1);
        MowerReader mowerReader = new MowerReader(resource, lawn);
        // WHEN
        Mower mower = mowerReader.read();
        // THEN
        assertThat(lawn).extracting(
                Lawn::getWidth,
                Lawn::getHeight
        ).containsExactly(
                1, 1
        );
        Position mowerPosition = mower.getPosition();
        assertThat(mowerPosition).extracting(
                Position::getX,
                Position::getY,
                Position::getDirection
        ).containsExactly(
                3,
                5,
                Direction.EAST
        );
        assertThat(mower.getInstructions()).containsExactly(
                FORWARD, LEFT, RIGHT, LEFT, FORWARD, FORWARD, LEFT
        );
    }

}