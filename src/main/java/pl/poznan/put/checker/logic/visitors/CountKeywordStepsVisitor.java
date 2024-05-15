package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Step;

public class CountKeywordStepsVisitor extends AbstractCountStepsVisitor{

    @Override
    protected CountKeywordStepsVisitor createVisitor() {
        return new CountKeywordStepsVisitor();
    }

    @Override
    protected boolean shouldCountStep(Step step) {
        FindKeywordsVisitor findKeywordsVisitor = new FindKeywordsVisitor();
        step.accept(findKeywordsVisitor);
        return findKeywordsVisitor.getBeginsWithKeyword();
    }
}