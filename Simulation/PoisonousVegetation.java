package Simulation;

import java.awt.*;

public class PoisonousVegetation extends VegetationDecorator {
    public PoisonousVegetation(Vegetation vegetation) {
        super(vegetation);
        setBackground(new Color(128, 0, 128)); 
    }

    @Override
    public int getFoodValue() {
        return -20;
    }
}