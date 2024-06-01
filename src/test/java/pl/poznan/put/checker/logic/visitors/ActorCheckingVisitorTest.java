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
 * Test class for ActorCheckingVisitor
 */
class ActorCheckingVisitorTest {

    private List<String> externalActors;
    private List<String> internalActors;
    private List<Step> listOfSteps;
    private List<Step> listOfSubsteps;
    private ActorCheckingVisitor actorCheckingVisitor;
    private Scenario scenario;

    /**
     * Sets up the test environment by initializing external and internal actors,
     * listOfSteps, listOfSubsteps, the scenario and the visitor.
     */
    @BeforeEach
    public void setUp(){

        externalActors = new ArrayList<>();
        internalActors = new ArrayList<>();
        listOfSteps = new ArrayList<>();
        listOfSubsteps = new ArrayList<>();

        scenario = new Scenario("Scenario title", externalActors, internalActors, listOfSteps);
        actorCheckingVisitor = new ActorCheckingVisitor(externalActors, internalActors);

    }

    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 0 errors, no KEYWORDS, 0 external actors, 1 internal actor
     * The expected list of wrong steps is empty.
     */
    @Test
    public void visitScenarioTest1(){

        internalActors.add("intActor1");
        for(int i=0; i<5; i++){
            listOfSubsteps.add(new Step(("intActor1 substep" + i + "for testing"), null));
        }

        listOfSteps.add(new Step("intActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }


    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 0 errors, WITH KEYWORDS, 0 external actors, 1 internal actor
     * The expected list of wrong steps is empty.
     */
    @Test
    public void visitScenarioTest2(){

        internalActors.add("intActor1");
        listOfSubsteps.add(new Step(("IF: intActor1 substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("FOR EACH: intActor1 substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("ELSE: intActor1 substep 3 for testing"), null));

        listOfSteps.add(new Step("intActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }

    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 0 errors, no KEYWORDS, 1 external actors, 1 internal actor
     * The expected list of wrong steps is empty.
     */
    @Test
    public void visitScenarioTest3(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");
        for(int i=0; i<3; i++){
            listOfSubsteps.add(new Step(("intActor1 substep" + i + "for testing"), null));
        }
        for(int i=0; i<3; i++){
            listOfSubsteps.add(new Step(("extActor1 substep" + i + "for testing"), null));
        }

        listOfSteps.add(new Step("intActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }


    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 0 errors, WITH KEYWORDS, 1 external actors, 1 internal actor
     * The expected list of wrong steps is empty.
     */
    @Test
    public void visitScenarioTest4(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");

        listOfSubsteps.add(new Step(("IF: intActor1 substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("FOR EACH: extActor1 substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("ELSE: intActor1 substep 3 for testing"), null));

        listOfSteps.add(new Step("extActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }

    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 1 error, no KEYWORDS, 0 external actors, 1 internal actor
     * The expected list of wrong steps has one step.
     */
    @Test
    public void visitScenarioTest5(){

        internalActors.add("intActor1");
        for(int i=0; i<5; i++){
            listOfSubsteps.add(new Step(("intActor1 substep" + i + "for testing"), null));
        }
        listOfSubsteps.add(new Step(("wrongActor error step"), null));
        listOfSteps.add(new Step("intActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step("wrongActor error step", null));
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }


    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 1 error, WITH KEYWORDS, 0 external actors, 1 internal actor
     * The expected list of wrong steps has one step.
     */
    @Test
    public void visitScenarioTest6(){

        internalActors.add("intActor1");
        listOfSubsteps.add(new Step(("IF: intActor1 substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("intActor1 substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("FOR EACH: substep 3 for testing"), null));
        listOfSubsteps.add(new Step(("IF: wrongActor error step"), null));

        listOfSteps.add(new Step("intActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step(("IF: wrongActor error step"), null));
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }

    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 1 error, no KEYWORDS, 1 external actors, 1 internal actor
     * The expected list of wrong steps has one step.
     */
    @Test
    public void visitScenarioTest7(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");
        for(int i=0; i<3; i++){
            listOfSubsteps.add(new Step(("intActor1 substep" + i + "for testing"), null));
        }
        for(int i=0; i<3; i++){
            listOfSubsteps.add(new Step(("extActor1 substep" + i + "for testing"), null));
        }
        listOfSubsteps.add(new Step(("wrongActor error step"), null));

        listOfSteps.add(new Step("intActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step(("wrongActor error step"), null));
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }


    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 1 error, WITH KEYWORDS, 1 external actors, 1 internal actor
     * The expected list of wrong steps has one step.
     */
    @Test
    public void visitScenarioTest8(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");

        listOfSubsteps.add(new Step(("IF: intActor1 substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("FOR EACH: substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("ELSE: intActor1 substep 3 for testing"), null));
        listOfSubsteps.add(new Step(("IF: wrongActor error step"), null));

        listOfSteps.add(new Step("extActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);
        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step(("IF: wrongActor error step"), null));
        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }

    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 5 errors, no KEYWORDS, 1 external actors, 1 internal actor
     * The expected list of wrong steps has five step.
     */
    @Test
    public void visitScenarioTest9(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");
        for(int i=0; i<3; i++){
            listOfSubsteps.add(new Step(("intActor1 substep" + i + "for testing"), null));
        }
        for(int i=0; i<3; i++){
            listOfSubsteps.add(new Step(("extActor1 substep" + i + "for testing"), null));
        }
        listOfSubsteps.add(new Step(("wrongActor error step 1"), null));
        listOfSubsteps.add(new Step(("noActor error step 2"), null));
        listOfSubsteps.add(new Step(("actor99 error step 3"), null));
        listOfSubsteps.add(new Step(("actor77 error step 4"), null));

        listOfSteps.add(new Step("actor12345 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);

        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step(("actor12345 main step"), listOfSubsteps));
        expectedListOfWrongSteps.add(new Step(("wrongActor error step 1"), null));
        expectedListOfWrongSteps.add(new Step(("noActor error step 2"), null));
        expectedListOfWrongSteps.add(new Step(("actor99 error step 3"), null));
        expectedListOfWrongSteps.add(new Step(("actor77 error step 4"), null));

        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }


    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Case: 5 errors, WITH KEYWORDS, 1 external actors, 1 internal actor
     * The expected list of wrong steps has five step.
     */
    @Test
    public void visitScenarioTest10(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");

        listOfSubsteps.add(new Step(("FOR EACH: substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("intActor1 substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("IF: wrongActor error step 1"), null));
        listOfSubsteps.add(new Step(("ELSE: error step 2"), null));
        listOfSubsteps.add(new Step(("IF: noActor error step 3"), null));
        listOfSubsteps.add(new Step(("actor99 error step 4"), null));
        listOfSubsteps.add(new Step(("ELSE: actor77 error step 5"), null));

        listOfSteps.add(new Step("extActor1 main step", listOfSubsteps));
        actorCheckingVisitor.visit(scenario);

        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step(("IF: wrongActor error step 1"), null));
        expectedListOfWrongSteps.add(new Step(("ELSE: error step 2"), null));
        expectedListOfWrongSteps.add(new Step(("IF: noActor error step 3"), null));
        expectedListOfWrongSteps.add(new Step(("actor99 error step 4"), null));
        expectedListOfWrongSteps.add(new Step(("ELSE: actor77 error step 5"), null));

        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }


    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Creates scenario using mock object.
     * Case: 3 errors, WITH KEYWORDS, 2 external actors, 2 internal actor
     * The expected list of wrong steps has three step.
     */
    @Test
    public void visitScenarioMockTest1(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");
        internalActors.add("intActor2");
        externalActors.add("extActor2");

        listOfSubsteps.add(new Step(("FOR EACH: substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("intActor1 substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("IF: wrongActor error step 1"), null));
        listOfSubsteps.add(new Step(("ELSE: actor12345 step 2"), null));
        listOfSubsteps.add(new Step(("IF: noActor error step 3"), null));

        listOfSteps.add(new Step("extActor1 main step", listOfSubsteps));

        scenario = mock(Scenario.class);
        when(scenario.externalActors()).thenReturn(externalActors);
        when(scenario.internalActors()).thenReturn(internalActors);
        when(scenario.steps()).thenReturn(listOfSteps);

        actorCheckingVisitor.visit(scenario);

        List<Step> expectedListOfWrongSteps = new ArrayList<>();
        expectedListOfWrongSteps.add(new Step(("IF: wrongActor error step 1"), null));
        expectedListOfWrongSteps.add(new Step(("ELSE: actor12345 step 2"), null));
        expectedListOfWrongSteps.add(new Step(("IF: noActor error step 3"), null));

        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }

    /**
     * Tests the visit method of the ActorCheckingVisitor.
     * Tests if steps starting with wrong actors are correctly identified.
     * Creates scenario using mock object.
     * Case: 0 error, no KEYWORDS, 2 external actors, 2 internal actor
     * The expected list of wrong steps is empty.
     */
    @Test
    public void visitScenarioMockTest2(){

        internalActors.add("intActor1");
        externalActors.add("extActor1");
        internalActors.add("intActor2");
        externalActors.add("extActor2");

        listOfSubsteps.add(new Step(("intActor1 substep 1 for testing"), null));
        listOfSubsteps.add(new Step(("intActor1 substep 2 for testing"), null));
        listOfSubsteps.add(new Step(("extActor2 error step 1"), null));
        listOfSubsteps.add(new Step(("intActor2 step 2"), null));
        listOfSubsteps.add(new Step(("extActor2 error step 3"), null));

        listOfSteps.add(new Step("extActor1 main step", listOfSubsteps));

        scenario = mock(Scenario.class);
        when(scenario.externalActors()).thenReturn(externalActors);
        when(scenario.internalActors()).thenReturn(internalActors);
        when(scenario.steps()).thenReturn(listOfSteps);

        actorCheckingVisitor.visit(scenario);

        List<Step> expectedListOfWrongSteps = new ArrayList<>();

        List<Step> actualListOfWrongSteps = actorCheckingVisitor.getStepsWithoutActor();
        assertEquals(expectedListOfWrongSteps, actualListOfWrongSteps);

    }
    
}