package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;


/**
 * A visitor implementation that checks whether any step in a scenario
 * begins with a specified keyword. Implements the Visitor interface.
 */
public class FindKeywordsVisitor implements Visitor {
    private boolean beginsWithKeyword = false;


    /**
     * Visits a Scenario. 
     *
     * @param scenario the scenario to visit.
     */
    @Override
    public void visit(Scenario scenario) {

    }


    /**
     * Visits a Step and checks if the step's text begins with any of the
     * specified keywords. If a keyword match is found, the beginsWithKeyword
     * is set to true.
     *
     * @param step the step to visit and check for keywords.
     */
    @Override
    public void visit(Step step) {
        for (String keyword : Scenario.KEYWORDS) {
            if (step.text().startsWith(keyword)) {
                beginsWithKeyword = true;
                break;
            }
        }

    }


    /**
     * Returns if any step text begins with a specified keyword.
     *
     * @return true if a step text begins with a keyword, false otherwise.
     */
    public boolean getBeginsWithKeyword() {
        return beginsWithKeyword;
    }
}
