package pl.poznan.put.checker.logic;

public record SubScenario(Step[] steps) implements Scenario {
    public SubScenario {
        if(steps == null) {
            throw new IllegalArgumentException("steps cannot be null");
        }
        if(steps.length == 0) {
            throw new IllegalArgumentException("steps cannot be empty");
        }
    }
}
