package Simulation;

public abstract class VegetationDecorator extends Vegetation {
    protected Vegetation wrappedVegetation;

    public VegetationDecorator(Vegetation vegetation) {
        super(vegetation.x, vegetation.y);
        this.wrappedVegetation = vegetation;
    }

    @Override
    public int getFoodValue() {
        return wrappedVegetation.getFoodValue();
    }
}