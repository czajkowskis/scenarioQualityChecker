package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements Visitor {
    private final String expression;
    private final List<Step> foundSteps = new ArrayList<>();
    public SearchVisitor(String expression) {
        this.expression = expression;
    }

    @Override
    public void visit(Scenario scenario) {
        for(final Step step : scenario.steps()) {
            step.accept(this);
        }
    }

    @Override
    public void visit(Step step) {
        if(step.text().contains(expression)) {
            foundSteps.add(step);
        }
        if(step.subSteps() != null) {
            for(final Step substep : step.subSteps()) {
                substep.accept(this);
            }
        }
    }

    public List<Step> getFoundSteps() {
        return Collections.unmodifiableList(foundSteps);
    }
}
