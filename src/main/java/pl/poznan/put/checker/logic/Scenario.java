package pl.poznan.put.checker.logic;

import pl.poznan.put.checker.logic.visitors.Visitable;
import pl.poznan.put.checker.logic.visitors.Visitor;

import java.util.Arrays;
import java.util.List;

public record Scenario(String title, List<String> externalActors, List<String> internalActors, List<Step> steps) implements Visitable {
    public static List<String> KEYWORDS = Arrays.asList("ELSE:", "FOR EACH:", "IF:");

    public Scenario {
        if(title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if(title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if(externalActors == null) {
            throw new IllegalArgumentException("ExternalActors cannot be null");
        }
        if(internalActors == null) {
            throw new IllegalArgumentException("InternalActors cannot be null");
        }
        if(steps == null) {
            throw new IllegalArgumentException("Steps cannot be null");
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
