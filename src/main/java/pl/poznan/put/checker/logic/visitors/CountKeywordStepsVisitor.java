package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Step;

/**
 * A visitor that counts the steps in a process that begin with a specific keyword.
 * This class extends AbstractCountStepsVisitor and overrides the necessary
 * methods to provide the functionality of counting only those steps that meet
 * the criteria defined by FindKeywordsVisitor.
 */
public class CountKeywordStepsVisitor extends AbstractCountStepsVisitor{

    /**
     * Creates a new instance of CountKeywordStepsVisitor.
     * This method is used by the superclass to create a new visitor instance when required.
     *
     * @return A new instance of CountKeywordStepsVisitor.
     */
    @Override
    protected CountKeywordStepsVisitor createVisitor() {
        return new CountKeywordStepsVisitor();
    }

    /**
     * Determines whether the given step should be counted.
     * A step is counted if it begins with a keyword as determined by FindKeywordsVisitor.
     *
     * @param step The step to check.
     * @return true if the step should be counted, false otherwise.
     */
    @Override
    protected boolean shouldCountStep(Step step) {
        FindKeywordsVisitor findKeywordsVisitor = new FindKeywordsVisitor();
        step.accept(findKeywordsVisitor);
        return findKeywordsVisitor.getBeginsWithKeyword();
    }
}