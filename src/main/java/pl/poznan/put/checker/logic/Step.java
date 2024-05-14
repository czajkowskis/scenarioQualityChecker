package pl.poznan.put.checker.logic;

import pl.poznan.put.checker.logic.visitors.Visitable;
import pl.poznan.put.checker.logic.visitors.Visitor;

import java.util.List;

public record Step(String text, List<Step> subSteps) implements Visitable {
    public Step  {
        if (text == null) {
            throw new IllegalArgumentException("Step text cannot be null");
        }
        if(text.isEmpty()) {
            throw new IllegalArgumentException("Step text cannot be empty");
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
