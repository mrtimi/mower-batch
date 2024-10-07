package com.mrtimi.mower.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static com.mrtimi.mower.batch.model.Direction.*;

@Data
@AllArgsConstructor
public class Mower {
    private Position position;
    private List<Instruction> instructions;

    public void moveForward(Lawn lawn) {
        if (isMowerOutsideTheLawn(lawn)) {
            return;
        }
        switch (position.getDirection()) {
            case NORTH -> position.setY(position.getY() + 1);
            case EAST -> position.setX(position.getX() + 1);
            case SOUTH -> position.setY(position.getY() - 1);
            case WEST -> position.setX(position.getX() - 1);
        }
    }

    public void turnLeft() {
        switch (position.getDirection()) {
            case NORTH -> position.setDirection(WEST);
            case WEST -> position.setDirection(SOUTH);
            case SOUTH -> position.setDirection(EAST);
            case EAST -> position.setDirection(NORTH);
        }
    }

    public void turnRight() {
        switch (position.getDirection()) {
            case NORTH -> position.setDirection(EAST);
            case EAST -> position.setDirection(SOUTH);
            case SOUTH -> position.setDirection(WEST);
            case WEST -> position.setDirection(NORTH);
        }
    }

    public boolean isMowerOutsideTheLawn(Lawn lawn) {
        return position.getX() > lawn.getWidth()
                || position.getX() < 0
                || position.getY() > lawn.getHeight()
                || position.getY() < 0;
    }
}
