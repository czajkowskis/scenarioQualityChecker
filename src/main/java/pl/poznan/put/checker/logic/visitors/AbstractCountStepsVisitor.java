package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.List;

public abstract class AbstractCountStepsVisitor implements Visitor {

    protected int numberOfSteps = 0;


    public int getNumberOfSteps() {
        return numberOfSteps;
    }

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

    protected abstract AbstractCountStepsVisitor createVisitor();

    protected abstract boolean shouldCountStep(Step step);

}
