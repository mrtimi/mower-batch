package com.mrtimi.mower.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Lawn implements Serializable {
    private int width;
    private int height;

    public boolean isInitialized() {
        return width != 0 && height != 0;
    }
}
