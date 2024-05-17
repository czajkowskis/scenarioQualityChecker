package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Step;


/**
 * A visitor that counts all steps in a process.
 * This class extends AbstractCountStepsVisitor and overrides the necessary
 * methods to provide the functionality of counting every step encountered.
 */
public class CountAllStepsVisitor extends AbstractCountStepsVisitor{


    /**
     * Creates a new instance of CountAllStepsVisitor.
     * This method is used by the superclass to create a new visitor instance when required.
     *
     * @return A new instance of CountAllStepsVisitor.
     */
    @Override
    protected CountAllStepsVisitor createVisitor() {
        return new CountAllStepsVisitor();
    }

    /**
     * Determines whether the given step should be counted.
     * In this implementation, every step is counted.
     *
     * @param step The step to check.
     * @return true, indicating that the step should always be counted.
     */
    @Override
    protected boolean shouldCountStep(Step step) {
        return true;
    }
}