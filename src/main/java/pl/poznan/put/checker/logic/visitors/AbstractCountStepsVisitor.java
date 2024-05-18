package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.List;

/**
 * An abstract class that defines the mechanism to count the number of steps
 * within a given Scenario or a Step. It implements the Visitor interface and
 * provides a template for counting steps through the visitor pattern.
 */
public abstract class AbstractCountStepsVisitor implements Visitor {

    /**
     * The counter for the number of steps encountered.
     */
    protected int numberOfSteps = 0;


    /**
     * Returns the total number of steps counted.
     *
     * @return the number of steps counted.
     */
    public int getNumberOfSteps() {

        return numberOfSteps;
    }


    /**
     * Visits a Scenario and counts the steps within it.
     * This method iterates through each Step in the Scenario,
     * creates a new visitor for each step, and accumulates the step counts.
     *
     * @param scenario the scenario to visit and count steps within.
     */
    @Override
    public void visit(Scenario scenario){

        List<Step> steps = scenario.steps();
        if (!steps.isEmpty()){
            for (Step step : steps){
                var stepsVisitor = createVisitor();
                step.accept(stepsVisitor);
                numberOfSteps += stepsVisitor.getNumberOfSteps();
            }
        }
    }


    /**
     * Visits a Step and counts it if it meets certain conditions
     * defined by shouldCountStep. It also recursively counts
     * substeps within the step.
     *
     * @param step the step to visit and potentially count.
     */
    @Override
    public void visit(Step step) {
        if (shouldCountStep(step)) {
            numberOfSteps += 1;
        }
        List<Step> substeps = step.subSteps();
        if (substeps != null) {
            for (Step substep : substeps) {
                var substepsVisitor = createVisitor();
                substep.accept(substepsVisitor);
                numberOfSteps += substepsVisitor.getNumberOfSteps();
            }
        }

    }

    /**
     * Creates a new instance of a visitor for counting steps.
     *
     * @return a new instance of AbstractCountStepsVisitor.
     */
    protected abstract AbstractCountStepsVisitor createVisitor();

    /**
     * Determines whether a given step should be counted.
     *
     * @param step the step to evaluate.
     * @return true if the step should be counted, false otherwise.
     */

    protected abstract boolean shouldCountStep(Step step);

}
