package pl.poznan.put.checker.logic.visitors;

import org.springframework.cglib.transform.impl.AccessFieldTransformer;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * A visitor that creates the list of steps that don't start with an actor taking part in the scenario
 */
public class ActorCheckingVisitor implements Visitor {

    private ArrayList<Step> stepsWithoutActor = new ArrayList<>();
    private List<String> externalActors;
    private List<String> internalActors;

    /**
     * Creates a new instace of the ActorCheckingVisitor
     *
     * @param externalActors The list of external actors taking part in the scenario
     * @param internalActors The list of internal actors taking part in the scenario
     */

    public ActorCheckingVisitor(List<String> externalActors, List<String> internalActors){
        this.externalActors = externalActors;
        this.internalActors = internalActors;
    }

    /**
     * Visits the scenario, creates a visitor for each of its steps and updates the list of steps which
     * don't start with an actor taking part in the scenario based on the value provided by the visitor
     *
     * @param scenario The scenario to be visited
     */
    @Override
    public void visit(Scenario scenario) {
        for(Step step : scenario.steps()) {
            ActorCheckingVisitor visitor = new ActorCheckingVisitor(scenario.externalActors(), scenario.internalActors());
            step.accept(visitor);
            stepsWithoutActor.addAll(visitor.getStepsWithoutActor());
        }
    }

    /**
     * Visits the step, checks whether its syntax is valid and creates a new visitor for every substep
     *
     * @param step The step to be visited
     */
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

    /**
     * Returns the list of steps that don't start with an actor taking part in the scenario
     *
     * @return The list of steps that don't start with an actor taking part in the scenario
     */
    public ArrayList<Step> getStepsWithoutActor() {
        return stepsWithoutActor;
    }
}
