package Simulation;

public class RestingState implements AnimalState {
    @Override
    public void handleAction(Animal animal, grid a) {
        if (animal.energy < animal.maxEnergy) {
            animal.energy += 5;
        } else {
            animal.energy = animal.maxEnergy;
            animal.setState(new WanderingState());
        }
    }
}