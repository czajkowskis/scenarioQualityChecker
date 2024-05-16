package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ScenarioFilterVisitor implements Visitor{

    private ArrayList<Step> includedSteps = new ArrayList<>();
    private final int maxLevel;
    private final int currentLevel;

    public ScenarioFilterVisitor(int maxLevel, int currentLevel){
        this.maxLevel = maxLevel;
        this.currentLevel = currentLevel;
    }
    @Override
    public void visit(Scenario scenario) {
        List<Step> steps = scenario.steps();
        if(!steps.isEmpty()) {
            processSteps(steps);
        }
    }

    @Override
    public void visit(Step step) {
        if(currentLevel < maxLevel) {
            includedSteps.add(step);
            if (step.subSteps() != null)
                    processSteps(step.subSteps());
        }
        if(currentLevel == maxLevel) {
            if(step.subSteps() == null || step.subSteps().isEmpty()) {
                includedSteps.add(step);
            }
        }

    }

    private void processSteps(List<Step> steps){
        for(Step step : steps){
            ScenarioFilterVisitor visitor = new ScenarioFilterVisitor(maxLevel, currentLevel + 1);
            step.accept(visitor);
            includedSteps.addAll(visitor.getIncludedSteps());
        }
    }

    public List<Step> getIncludedSteps(){
        return includedSteps;
    }
}
