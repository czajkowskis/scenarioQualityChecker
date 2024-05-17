package pl.poznan.put.checker.logic.visitors;

import org.springframework.cglib.transform.impl.AccessFieldTransformer;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.List;

public class ActorCheckingVisitor implements Visitor {

    private ArrayList<Step> stepsWithoutActor = new ArrayList<>();
    private List<String> externalActors;
    private List<String> internalActors;

    public ActorCheckingVisitor(List<String> externalActors, List<String> internalActors){
        this.externalActors = externalActors;
        this.internalActors = internalActors;
    }

    @Override
    public void visit(Scenario scenario) {
        for(Step step : scenario.steps()) {
            ActorCheckingVisitor visitor = new ActorCheckingVisitor(scenario.externalActors(), scenario.internalActors());
            step.accept(visitor);
            stepsWithoutActor.addAll(visitor.getStepsWithoutActor());
        }
    }

    @Override
    public void visit(Step step) {
        StepSyntaxVisitor syntaxVisitor = new StepSyntaxVisitor(externalActors, internalActors);
        step.accept(syntaxVisitor);
        if (!syntaxVisitor.getStepSyntaxIsValid())
            stepsWithoutActor.add(step);
        if (step.subSteps() != null)
            for (Step substep: step.subSteps()) {
                ActorCheckingVisitor visitor = new ActorCheckingVisitor(externalActors, internalActors);
                substep.accept(visitor);
                stepsWithoutActor.addAll(visitor.getStepsWithoutActor());
            }
    }

    public ArrayList<Step> getStepsWithoutActor() {
        return stepsWithoutActor;
    }
}