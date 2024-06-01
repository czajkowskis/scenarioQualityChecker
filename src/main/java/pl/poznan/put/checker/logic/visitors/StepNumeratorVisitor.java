package pl.poznan.put.checker.logic.visitors;

import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A visitor which creates a list of numbered steps of the scenario in the text form.
 */
public class StepNumeratorVisitor implements Visitor{

    private ArrayList<String> numeratedSteps = new ArrayList<>();
    private Stack<String> stepNumberStack = new Stack<>();


    public StepNumeratorVisitor() {

    }
    private StepNumeratorVisitor(Stack<String> stepNumberStack){
        this.stepNumberStack = stepNumberStack;
    }
    /**
     * Visits the scenario and processes the steps on the first level
     *
     * @param scenario The scenario to be visited
     */
    @Override
    public void visit(Scenario scenario) {
        List<Step> steps = scenario.steps();
        if(!steps.isEmpty()) {
            processSteps(steps);
        }
    }

    /**
     * Visits the step, numerates it and adds the created string to the list of numerated steps. If the step has any substeps, processes the them.
     *
     *  @param step The step to be visited
     */
    @Override
    public void visit(Step step) {
        numeratedSteps.add(String.join(".", new ArrayList<String>(stepNumberStack)) + ". " + step.text());
        if (step.subSteps() != null){
            processSteps(step.subSteps());
        }
    }

    /**
     * Processes the steps given in the argument.
     * Creates a new visitor for each step to be processed and updates the list of numerated steps based on the value provided by the visitor
     *
     * @param steps The list of steps to be processed
     */
    private void processSteps(List<Step> steps){
        for(int i = 0; i < steps.size(); i++){
            stepNumberStack.push(Integer.toString(i + 1));
            StepNumeratorVisitor visitor = new StepNumeratorVisitor(stepNumberStack);
            steps.get(i).accept(visitor);
            numeratedSteps.addAll(visitor.getNumeratedSteps());
            stepNumberStack.pop();
        }
    }
    /**
     * Returns the list of numerated steps in the form of text
     *
     * @return The list of numerated steps in the form of text
     */
    public List<String> getNumeratedSteps(){
        return numeratedSteps;
    }

    public String getNumeratedStepsAsString(){ return String.join(" ", numeratedSteps);}
}
