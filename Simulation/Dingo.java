package Simulation;

import java.awt.Color;

public class Dingo extends Animal {

    public Dingo() {
        setBackground(new Color(240, 126, 38));
    }

    public void wander(grid g) {
        super.wander(g);
        update();
        ageAnimal();
        hungry();
        breed(g);

        if (energy > 0) {
            energy -= 2; 
        }
        
        if (energy <= 0) {
            g.death(this);
            System.out.println("Dingo died of exhaustion");
        }
        
        if (age > maxAge) {
            g.death(this);
            System.out.println("Dingo died of old age");
        }
        
        if (hunger <= 0) {
            g.death(this);
            System.out.println("Dingo died of hunger");
        }
        
        if (hunger < maxHunger) {
            hunt(g);
        }
    }

    public void hunt(grid g) {
        g.animals.stream()
            .filter(a -> a instanceof Kangaroo)
            .filter(a -> super.dist(this, a) < SensoryDistance)
            .findFirst()
            .ifPresent(prey -> {
                eat();
                System.out.println("Hunt Successful");
                System.out.println("Kangaroo was eaten");
                g.death(prey);
            });
    }
}