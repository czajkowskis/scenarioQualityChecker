package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.List;

/**
 * A visitor that checks whether step text follows the correct syntax rule i.e. starts with an actor taking part in the scenario or correct keyword.
 */
public class StepSyntaxVisitor implements Visitor{

    private final List<String> externalActors;
    private final List<String> internalActors;
    private boolean stepSyntaxIsValid;

    /**
     * @param externalActors The list of external actors taking part in the scenario
     * @param internalActors The list of internal actors taking part in the scenario
     */
    public StepSyntaxVisitor(List<String> externalActors, List<String> internalActors) {
        this.externalActors = externalActors;
        this.internalActors = internalActors;
    }

    /**
     * Checks if the step text starts with the actor taking part in the scenario or correct keyword.
     *
     * @param stepText The description of the step to be checked
     * @return Boolean value describing the syntax correctness (true - correct, false - incorrect)
     */
    private boolean checkSyntax(String stepText){
        if(stepText.startsWith("FOR EACH:")){
            return true;
        }

        if(stepText.startsWith("IF:") || stepText.startsWith("ELSE:")){
            String[] words = stepText.split(" ");
            if(words.length > 1){
                String secondWord = words[1];
                for(String actor : externalActors){
                    if(secondWord.equals(actor)){
                        return true;
                    }
                }
                for(String actor : internalActors){
                    if(secondWord.equals(actor)){
                        return true;
                    }
                }
            }
            return false;
        }
        for(String actor : externalActors){
            if(stepText.startsWith(actor)){
                return true;
            }
        }
        for (String actor : internalActors){
            if(stepText.startsWith(actor)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void visit(Scenario scenario) {

    }

    /**
     * Visits the given step and checks the correctness of its syntax.
     *
     * @param step The step to be visited
     */
    @Override
    public void visit(Step step) {
        StepSyntaxVisitor syntaxVisitor = new StepSyntaxVisitor(externalActors, internalActors);
        stepSyntaxIsValid = checkSyntax(step.text());
    }

    /**
     * Returns the boolean value describing the correctness of the step's syntax (true - correct, false - incorrect)
     *
     * @return The boolean value describing the correctness of the step's syntax (true - correct, false - incorrect)
     */

    public boolean getStepSyntaxIsValid(){
        return stepSyntaxIsValid;
    }
}
