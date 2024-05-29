package pl.poznan.put.checker.logic.visitors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for CountAllStepsVisitor.
 */
class CountAllStepsVisitorTest {

    private List<Step> listOfSteps;
    private Scenario scenario;
    private List<String> externalActors;
    private List<String> internalActors;
    private CountAllStepsVisitor allStepsVisitor;

    /**
     * Sets up the test environment by initializing listOfSteps,
     * external and internal actors, the scenario and the visitor.
     */
    @BeforeEach
    public void setUp(){

        listOfSteps = new ArrayList<>();
        externalActors = new ArrayList<>(Arrays.asList("extActor1", "extActor2"));
        internalActors = new ArrayList<>(Arrays.asList("intActor1", "intActor2"));

        scenario = new Scenario("Scenario title", externalActors, internalActors, listOfSteps);
        allStepsVisitor = new CountAllStepsVisitor();
    }


    /**
     * Tests the visit method of the CountAllStepsVisitor with an empty scenario.
     * The expected number of steps is 0.
     */
    @Test
    public void visitEmptyScenarioTest(){

        allStepsVisitor.visit(scenario);
        int expectedNumberOfSteps = 0;
        int actualNumberOfSteps = allStepsVisitor.getNumberOfSteps();
        assertEquals(expectedNumberOfSteps, actualNumberOfSteps);

    }

    /**
     * Tests the visit method of the CountAllStepsVisitor with a scenario containing one step.
     * The expected number of steps is 1.
     */
    @Test
    public void visitOneStepScenarioTest(){

        listOfSteps.add(new Step("Step to test OneStep Scenario", null));
        allStepsVisitor.visit(scenario);
        int expectedNumberOfSteps = 1;
        int actualNumberOfSteps = allStepsVisitor.getNumberOfSteps();
        assertEquals(expectedNumberOfSteps, actualNumberOfSteps);

    }

    /**
     * Tests the visit method of the CountAllStepsVisitor with a scenario containing five steps.
     * The expected number of steps is 5.
     */
    @Test
    public void visitFiveStepScenarioTest(){

        for (int i=0; i<5; i++){
            String stepName = i + "step to test FiveStepScenario";
            listOfSteps.add(new Step(stepName, null));
        }

        allStepsVisitor.visit(scenario);
        int expectedNumberOfSteps = 5;
        int actualNumberOfSteps = allStepsVisitor.getNumberOfSteps();
        assertEquals(expectedNumberOfSteps, actualNumberOfSteps);

    }


    /**
     * Tests the visit method of the CountAllStepsVisitor with an empty scenario.
     * Creates scenario using mock object.
     * The expected number of steps is 0.
     */
    @Test
    public void visitEmptyScenarioMockTest(){

        scenario = mock(Scenario.class);
        when(scenario.steps()).thenReturn(new ArrayList<>());

        allStepsVisitor.visit(scenario);
        int expectedNumberOfSteps = 0;
        int actualNumberOfSteps = allStepsVisitor.getNumberOfSteps();
        assertEquals(expectedNumberOfSteps, actualNumberOfSteps);

    }

    /**
     * Tests the visit method of the CountAllStepsVisitor with a scenario containing one step.
     * Creates scenario using mock object.
     * The expected number of steps is 1.
     */
    @Test
    public void visitOneStepScenarioMockTest(){

        listOfSteps.add(new Step("Step to test OneStep Scenario", null));

        scenario = mock(Scenario.class);
        when(scenario.steps()).thenReturn(listOfSteps);

        allStepsVisitor.visit(scenario);
        int expectedNumberOfSteps = 1;
        int actualNumberOfSteps = allStepsVisitor.getNumberOfSteps();
        assertEquals(expectedNumberOfSteps, actualNumberOfSteps);

    }

    /**
     * Tests the visit method of the CountAllStepsVisitor with a scenario containing five steps.
     * Creates scenario using mock object.
     * The expected number of steps is 5.
     */
    @Test
    public void visitFiveStepScenarioMockTest(){

        for (int i=0; i<5; i++){
            String stepName = i + "step to test FiveStepScenario";
            listOfSteps.add(new Step(stepName, null));
        }

        scenario = mock(Scenario.class);
        when(scenario.steps()).thenReturn(listOfSteps);

        allStepsVisitor.visit(scenario);
        int expectedNumberOfSteps = 5;
        int actualNumberOfSteps = allStepsVisitor.getNumberOfSteps();
        assertEquals(expectedNumberOfSteps, actualNumberOfSteps);

    }

}