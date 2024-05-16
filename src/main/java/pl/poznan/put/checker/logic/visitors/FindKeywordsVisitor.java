package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

public class FindKeywordsVisitor implements Visitor {
    private boolean beginsWithKeyword = false;
    @Override
    public void visit(Scenario scenario) {

    }

    @Override
    public void visit(Step step) {
        for (String keyword : Scenario.KEYWORDS) {
            if (step.text().startsWith(keyword)) {
                beginsWithKeyword = true;
                break;
            }
        }

    }
    public boolean getBeginsWithKeyword() {
        return beginsWithKeyword;
    }
}
