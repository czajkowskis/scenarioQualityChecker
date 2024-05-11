package pl.poznan.put.checker.logic;

public record Step(String text, SubScenario subScenario) {
    public Step {
        if (text == null) {
            throw new IllegalArgumentException("Step text cannot be null");
        }
        if(text.isEmpty()) {
            throw new IllegalArgumentException("Step text cannot be empty");
        }
    }
}
