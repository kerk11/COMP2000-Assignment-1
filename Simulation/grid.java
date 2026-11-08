package Simulation;

import java.awt.*;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class grid {

    Container<Animal> animals;
    Container<Vegetation> vegetation;
    Frame f;

    int VegetationTimer = 0;
    int VegetationGrowthTime = 1;

    public <T> void set(Container<Animal> i, Container<Vegetation> v, Frame o) {
        animals = i;
        vegetation = v;
        f = o;
    }

    public void newDingo(double x, double y) {
        Dingo d = new Dingo();
        d.create(x, y);
        this.f.add(d);
        this.animals.add(d);
    }

    public void newKangaroo(double x, double y) {
        Kangaroo k = new Kangaroo();
        k.create(x, y);
        this.f.add(k);
        this.animals.add(k);
    }

    public void newVegetation(double x, double y) {
        Vegetation v = new Vegetation(x, y);
        this.f.add(v);
        this.vegetation.add(v);
    }

    public void death(Animal a) {
        animals.remove(a);
        f.remove(a);
    }

    public void checkBounds(Animal a) {
        if (a.x + a.Animal_Size >= f.getWidth() || a.x < 1) {
            a.moveX *= -1;
        }
        if (a.y + 2 * a.Animal_Size >= f.getHeight() || a.y < 1) {
            a.moveY *= -1;
        }
    }

    public void eaten(Vegetation v) {
        vegetation.remove(v);
        f.remove(v);
    }

    public void growVegetation() {
        VegetationTimer++;

        if (VegetationTimer >= VegetationGrowthTime) {
            double x = Math.random() * 790 + 5;
            double y = Math.random() * 590 + 5;

            if (Math.random() < 0.10) {
                Vegetation poisonPlant = new PoisonousVegetation(new Vegetation(x, y));
                this.f.add(poisonPlant);
                this.vegetation.add(poisonPlant);
            } else {
                newVegetation(x, y);
            }

            VegetationTimer = 0;
        }
    }

    public void printEcosystemDashboard() {
        
        new Thread(() -> {
            System.out.println("\n=== Live Ecological Dashboard  ===");

            synchronized (animals) {
                try {
                    Map<Class<?>, Long> populationBySpecies = animals.stream().parallel()
                        .collect(Collectors.groupingBy(Animal::getClass, Collectors.counting()));

                    Map<Class<?>, DoubleSummaryStatistics> energyStatsBySpecies = animals.stream().parallel()
                        .collect(Collectors.groupingBy(Animal::getClass, 
                                 Collectors.summarizingDouble(Animal::getEnergy)));

                    populationBySpecies.forEach((species, count) -> {
                        System.out.println("Species: " + species.getSimpleName());
                        System.out.println(" - Population : " + count + " alive");
                        
                        DoubleSummaryStatistics stats = energyStatsBySpecies.get(species);
                        if (stats != null && count > 0) {
                            System.out.printf(" - Energy Stats: Avg %.1f | Min %.1f | Max %.1f\n", 
                                              stats.getAverage(), stats.getMin(), stats.getMax());
                        }
                    });
                } catch (Exception e) {
                    System.out.println("ConcurrentModificationException caught and handled!");
                }
            }
            System.out.println("============================================\n");
            
        }).start();
    }
}