package Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation {
    
    private static int tickCount = 0;

    public static void main(String[] args) {
        JFrame f = new JFrame("Ecosystem Simulation");
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.getContentPane().setBackground(new Color(163, 46, 7));

        Container<Animal> animals = new Container<>();
        Container<Vegetation> vegetation = new Container<>();

        grid g = new grid();
        g.set(animals, vegetation, f);

        for (int i = 0; i < 300; i++) {
            double x = (int) (Math.random() * 800);
            double y = (int) (Math.random() * 600);
            g.newVegetation(x, y);
        }
        for (int i = 0; i < 2; i++) {
            double x = (int) (Math.random() * 750) + 25;
            double y = (int) (Math.random() * 550) + 25;
            g.newDingo(x, y);
        }
        for (int i = 0; i < 10; i++) {
            double x = (int) (Math.random() * 750) + 25;
            double y = (int) (Math.random() * 550) + 25;
            g.newKangaroo(x, y);
        }

        Timer timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    for (Animal animal : animals) {
                        animal.wander(g);
                    }
                    
                    g.growVegetation();
                    f.repaint();

                    tickCount++;
                    
                    if (tickCount % 100 == 0) {
                        g.printEcosystemDashboard();
                    }
                    
                } catch (Exception ex) {
                    System.out.println("Warning: Collection modified during iteration.");
                }
            }
        });

        f.setVisible(true);
        timer.start();
    }
}