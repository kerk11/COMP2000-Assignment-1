package Simulation;

import java.awt.*;

public class Vegetation extends Panel {

    double x;
    double y;

    public Vegetation(double x, double y) {
        setBackground(new Color(76, 122, 35));
        setBounds((int) x, (int) y, 10, 10);
        this.x = x;
        this.y = y;
    }

    public int getFoodValue() {
        return 10;
    }
}