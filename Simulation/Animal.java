package Simulation;

import java.awt.*;

public class Animal extends Panel {

    double moveX;
    double moveY;
    double x;
    double y;

    int Animal_Size = 25;
    double energy = 100;
    double restEnergy = 50;
    double maxEnergy = 100;

    double hunger = 75;
    double maxHunger = 100;

    double speed = energy / 18;
    double maxSpeed = maxEnergy / 18;

    int age;
    int maxAge = 200;
    int update;
    int SensoryDistance = 50;

    double breedEnergy = 70;
    int breedAge = 30;
    long breedCooldown = 10000;
    long lastBreedTime = 0;

    int plantFoodValue = 10;
    int kangarooFoodValue = 50;
    int forageDist = 50;

    private AnimalState currentState;

    public Animal() {
        setBounds((int) x, (int) y, 10, 10);
        this.currentState = new WanderingState();
    }

    public void setState(AnimalState state) {
        this.currentState = state;
    }

    public void wander(grid a) {
        if (this.currentState != null) {
            this.currentState.handleAction(this, a);
        }
    }

    public void create(double initX, double initY) {
        x = initX;
        y = initY;
        moveY = Math.random() * speed;
        moveX = Math.random() * speed;
        changeDirection();
    }

    public double dist(Animal A, Animal B) {
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void rest(grid a) {
        if (energy <= maxEnergy) {
            energy = maxEnergy;
        }
    }

    public int ageAnimal() {
        age += 1;
        return age;
    }

    public int update() {
        update += 1;
        return update;
    }

    public void breed(grid g) {
        if (canBreed()) {
            if (this instanceof Kangaroo) {
                g.newKangaroo(x, y);
                System.out.println("New Kangaroo");
            }
            if (this instanceof Dingo) {
                g.newDingo(x, y);
                System.out.println("New Dingo");
            }
            energy -= 20;
            lastBreedTime = System.currentTimeMillis();
        }
    }

    public boolean canBreed() {
        long currentTime = System.currentTimeMillis();
        return age >= breedAge &&
                energy >= breedEnergy &&
                currentTime - lastBreedTime >= breedCooldown &&
                hunger > 30;
    }

    public void hungry() {
        hunger -= 1;
    }

    public void eat() {
        if (this instanceof Kangaroo) {
            hunger += plantFoodValue;
        }
        if (this instanceof Dingo) {
            hunger += kangarooFoodValue;
        }
    }

    public void changeDirection() {
        moveX = Math.random() * speed;
        moveY = Math.random() * speed;
        int direction = (int) Math.ceil(Math.random() * 4);
        if (direction == 1 || direction == 2) {
            moveX *= -1;
        }
        if (direction == 2 || direction == 4) {
            moveY *= -1;
        }
    }
    public double getEnergy() {
        return this.energy;
    }
}