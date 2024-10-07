package com.mrtimi.mower.batch.reader;

import com.mrtimi.mower.batch.model.*;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MowerReader implements ItemReader<Mower>, ItemReadListener<Mower> {

    private final BufferedReader reader;
    private final Lawn lawn;

    private StepExecution stepExecution;

    public MowerReader(final Resource resource, final Lawn lawn) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        this.lawn = lawn;
    }

    @Override
    public Mower read() throws Exception {
        if (!lawn.isInitialized()) {
            initializeLawn();
        }
        return parseMower();
    }

    private Mower parseMower() throws IOException {
        String mowerPositionLine = reader.readLine();
        if (mowerPositionLine == null) {
            return null;
        }
        Position position = parseMowerPosition(mowerPositionLine);

        List<Instruction> instructions = reader.readLine().chars()
                .mapToObj(c -> Instruction.fromValue((char) c))
                .toList();

        return new Mower(position, instructions);
    }

    private Position parseMowerPosition(String mowerPositionLine) {
        String[] position = mowerPositionLine.split(" ");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        Direction direction = Direction.fromValue(position[2].charAt(0));
        return new Position(x, y, direction);
    }

    private void initializeLawn() throws IOException {
        String firstLine = reader.readLine();
        String[] size = firstLine.split(" ");
        lawn.setWidth(Integer.parseInt(size[0]));
        lawn.setHeight(Integer.parseInt(size[1]));
    }


}
