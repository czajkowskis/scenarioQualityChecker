package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A visitor which creates a filtered scenario containing only subscenarios up to the specified level of nesting.
 */
public class ScenarioFilterVisitor implements Visitor{

    private ArrayList<Step> includedSteps = new ArrayList<>();
    private final int maxLevel;
    private int currentLevel = 0;

    /**
     * @param maxLevel The maximum allowed level of nesting
     * @param currentLevel The level of nesting reached before the construction of the visitor
     */
    private ScenarioFilterVisitor(int maxLevel, int currentLevel){
        this.maxLevel = maxLevel;
        this.currentLevel = currentLevel;
    }

    /**
     * @param maxLevel The maximum allowed level of nesting
     */
    public ScenarioFilterVisitor(int maxLevel){
        this.maxLevel = maxLevel;
    }

    /**
     * Visits the scenario and processes the steps on the first level
     * 
     * @param scenario The scenario to be visited
     */
    @Override
    public void visit(Scenario scenario) {
        List<Step> steps = scenario.steps();
        if(!steps.isEmpty()) {
            processSteps(steps);
        }
    }

    /**
     * Visits the step and adds it to the list of included steps. If the substeps of the step are on the level lower than the maximum, processes the substeps.
     *
     *  @param step The step to be visited
     */
    @Override
    public void visit(Step step) {
        List<Step> includedSubsteps;
        System.out.println(step.text());
        System.out.println(currentLevel);
        if(currentLevel <= maxLevel) {
            if (step.subSteps() != null) {
                includedSubsteps = processSubsteps(step.subSteps());
                includedSteps.add(new Step(step.text(), includedSubsteps));
            } else{
                includedSteps.add(new Step(step.text(), Collections.emptyList()));
            }
        }
    }

    /**
     * Processes the steps given in the argument.
     * Creates a new visitor for each step to be processed and updates the list of included steps based on the value provided by the visitor
     *
     * @param steps The list of steps to be processed
     */
    private void processSteps(List<Step> steps){
        for(Step step : steps){
            ScenarioFilterVisitor visitor = new ScenarioFilterVisitor(maxLevel, currentLevel + 1);
            step.accept(visitor);
            includedSteps.addAll(visitor.getIncludedSteps());
        }
    }

    /**
     * Processes the substeps to prepare a list of substeps that should be included in the step of lower nesting level
     *
     * @param substeps The list of substeps to be processed
     * @return The list of substeps to be included in the step of lower nesting level
     */

    private List<Step> processSubsteps(List<Step> substeps){
        List<Step> includedSubsteps = new ArrayList<>();
        for(Step step : substeps){
            ScenarioFilterVisitor visitor = new ScenarioFilterVisitor(maxLevel, currentLevel + 1);
            step.accept(visitor);
            includedSubsteps.addAll(visitor.getIncludedSteps());
        }
        return includedSubsteps;
    }

    /**
     * Returns the list of steps included in the filtered scenario
     *
     * @return The list of steps included in the filtered scenario
     */
    public List<Step> getIncludedSteps(){
        return includedSteps;
    }
}
