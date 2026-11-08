package Simulation;

public class WanderingState implements AnimalState {
    @Override
    public void handleAction(Animal animal, grid a) {
        if (animal.energy <= animal.restEnergy) {
            animal.setState(new RestingState());
            return;
        }
        
        a.checkBounds(animal);
        animal.x += animal.moveX;
        animal.y += animal.moveY;
        animal.setBounds((int) animal.x, (int) animal.y, animal.Animal_Size, animal.Animal_Size);
        
        if (animal.canBreed()) {
            animal.breed(a);
        }
    }
}