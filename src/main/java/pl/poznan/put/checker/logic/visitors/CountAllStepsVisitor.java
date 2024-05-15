package pl.poznan.put.checker.logic.visitors;


import pl.poznan.put.checker.logic.Step;



public class CountAllStepsVisitor extends AbstractCountStepsVisitor{

    @Override
    protected CountAllStepsVisitor createVisitor() {
        return new CountAllStepsVisitor();
    }

    @Override
    protected boolean shouldCountStep(Step step) {
        return true;
    }
}