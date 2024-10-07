package com.mrtimi.mower.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Position {
    private int x;
    private int y;
    private Direction direction;

    public String toString() {
        return x + " " + y + " " + direction.getValue();
    }
}
