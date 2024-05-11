package pl.poznan.put.checker.logic;

public record MainScenario(String title, String[] externalActors, String[] internalActors, Step[] steps) implements Scenario {
    public MainScenario {
        if(title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        if(title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be empty");
        }
        if(externalActors == null) {
            throw new IllegalArgumentException("externalActors cannot be null");
        }
        if(internalActors == null) {
            throw new IllegalArgumentException("internalActors cannot be null");
        }
        if(steps == null) {
            throw new IllegalArgumentException("steps cannot be null");
        }
        if(steps.length == 0) {
            throw new IllegalArgumentException("steps cannot be empty");
        }
    }
}
