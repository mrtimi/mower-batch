package com.mrtimi.mower.batch.processor;

import com.mrtimi.mower.batch.model.Instruction;
import com.mrtimi.mower.batch.model.Lawn;
import com.mrtimi.mower.batch.model.Mower;
import com.mrtimi.mower.batch.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@RequiredArgsConstructor
public class MowerProcessor implements ItemProcessor<Mower, Position> {

    private final Lawn lawn;

    @Override
    public Position process(Mower mower) {
        List<Instruction> instructions = mower.getInstructions();
        Position position = mower.getPosition();
        for (Instruction instruction : instructions) {
            if (mower.isMowerOutsideTheLawn(lawn)) {
                return position;
            }
            switch (instruction) {
                case LEFT -> mower.turnLeft();
                case RIGHT -> mower.turnRight();
                case FORWARD -> mower.moveForward(lawn);
            }
        }

        return position;
    }


}
