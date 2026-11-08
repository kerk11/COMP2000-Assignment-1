package Simulation;

import java.awt.Color;

public class Kangaroo extends Animal {

    public Kangaroo() {
        setBackground(new Color(150, 118, 108));
    }

    public void wander(grid a) {
        flee(a);
        super.wander(a);
        update();
        ageAnimal();
        hungry();
        breed(a);

        if (energy > 0) {
            energy -= 1; 
        }
        
        if (energy <= 0) {
            a.death(this);
            System.out.println("Kangaroo died of exhaustion");
        }
        
        if (age > maxAge) {
            a.death(this);
            System.out.println("Kangaroo died of old age");
        }
        
        if (hunger <= 0) {
            a.death(this); 
            System.out.println("Kangaroo died of hunger");
        }
        
        if (hunger < maxHunger) {
            forage(a);
        }
    }

    public void flee(grid g) {
        g.animals.stream()
            .filter(a -> a instanceof Dingo)
            .filter(a -> super.dist(this, a) < SensoryDistance)
            .findFirst()
            .ifPresent(predator -> changeDirection());
    }

    public void forage(grid g) {
        g.vegetation.stream()
            .filter(plant -> Math.sqrt(Math.pow(plant.x - x, 2) + Math.pow(plant.y - y, 2)) < forageDist)
            .findFirst()
            .ifPresent(plant -> {
                hunger += plant.getFoodValue(); 
                g.eaten(plant);
                System.out.println("Forage Successful");
            });
    }
}