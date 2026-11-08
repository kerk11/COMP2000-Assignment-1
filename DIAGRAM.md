```mermaid
classDiagram
    direction TB

    class AnimalState {
        <<interface>>
        +handleAction(animal: Animal, a: grid)
    }

    class WanderingState {
        +handleAction(animal: Animal, a: grid)
    }

    class RestingState {
        +handleAction(animal: Animal, a: grid)
    }

    AnimalState <|.. WanderingState : implements
    AnimalState <|.. RestingState : implements
    Animal o--> AnimalState : has-a (currentState)

    class Vegetation {
        +x: double
        +y: double
        +getFoodValue(): int
    }

    class VegetationDecorator {
        <<abstract>>
        #wrappedVegetation: Vegetation
        +getFoodValue(): int
    }

    class PoisonousVegetation {
        +getFoodValue(): int
    }

    Vegetation <|-- VegetationDecorator : extends
    VegetationDecorator o--> Vegetation : wraps (has-a)
    VegetationDecorator <|-- PoisonousVegetation : extends

    class Animal {
        -currentState: AnimalState
        +energy: double
        +setState(state: AnimalState)
        +wander(a: grid)
    }

    class Kangaroo {
        +wander(a: grid)
        +flee(a: grid)
        +forage(a: grid)
    }

    class Dingo {
        +wander(a: grid)
        +hunt(a: grid)
    }

    Animal <|-- Kangaroo : extends
    Animal <|-- Dingo : extends

    class grid {
        +animals: Container~Animal~
        +vegetation: Container~Vegetation~
        +printEcosystemDashboard()
        +growVegetation()
    }
