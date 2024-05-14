package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

public interface Visitor {
    void visit(Scenario scenario);
    void visit (Step step);
}
