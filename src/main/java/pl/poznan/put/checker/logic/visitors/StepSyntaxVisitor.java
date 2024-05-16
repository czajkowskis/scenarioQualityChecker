package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.List;

public class StepSyntaxVisitor implements Visitor{

    private final List<String> externalActors;
    private final List<String> internalActors;
    private boolean stepSyntaxIsValid;

    public StepSyntaxVisitor(List<String> externalActors, List<String> internalActors) {
        this.externalActors = externalActors;
        this.internalActors = internalActors;
    }

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

    @Override
    public void visit(Step step) {
        StepSyntaxVisitor syntaxVisitor = new StepSyntaxVisitor(externalActors, internalActors);
        stepSyntaxIsValid = checkSyntax(step.text());
    }

    public boolean getStepSyntaxIsValid(){
        return stepSyntaxIsValid; }
}
