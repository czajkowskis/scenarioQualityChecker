package pl.poznan.put.checker.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;
import pl.poznan.put.checker.logic.visitors.*;

import java.util.ArrayList;
import java.util.List;

/***
 * Rest API endpoints
 */
@RestController
@RequestMapping("/")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /***
     * Endpoint that validates and echoes back scenario send in POST
     * @param scenario Scenario converted from the body of the request
     * @return The same scenario that was received
     */
    @PostMapping(path = "echo", produces = "application/json")
    public Scenario echo(@RequestBody Scenario scenario) {
        logger.info("Received echo request: {}", scenario);

        return scenario;
    }

    /***
     * Endpoint that counts the number of steps in the received scenario
     * @param scenario Scenario converted from the body of the request
     * @return Response containing number of steps
     */
    @PostMapping(path = "count-steps", produces = "application/json")
    public Response<Integer> countAllSteps(@RequestBody Scenario scenario) {
        logger.info("Received count-steps request: {}", scenario);
        CountAllStepsVisitor visitor = new CountAllStepsVisitor();
        visitor.visit(scenario);
        Response<Integer> response = new Response<>("step-count", visitor.getNumberOfSteps());
        logger.info("Sending count-steps response: {}", response);
        return response;
    }


    /***
     * Endpoint that counts the number of steps in the received scenario that contain a keyword
     * @param scenario Scenario converted from the body of the request
     * @return Response containing number of steps that contain a keyword
     */
    @PostMapping(path = "count-keywords", produces = "application/json")
    public Response<Integer> countKeywordSteps(@RequestBody Scenario scenario) {
        logger.info("Received count-keywords request: {}", scenario);
        CountKeywordStepsVisitor visitor = new CountKeywordStepsVisitor();
        visitor.visit(scenario);
        Response<Integer> response = new Response<>("keyword-count", visitor.getNumberOfSteps());
        logger.info("Sending count-keywords response: {}", response);
        return response;
    }


    /***
     * Endpoint that lists steps that don't contain a valid actor
     * @param scenario Scenario converted from the body of the request
     * @return Response containing list of steps that don't contain a valid actor
     */
    @PostMapping(path = "check-actors", produces = "application/json")
    public Response<List<Step>> checkActors(@RequestBody Scenario scenario) {
        logger.info("Received check-actors request: {}", scenario);
        ActorCheckingVisitor visitor = new ActorCheckingVisitor(scenario.externalActors(), scenario.internalActors());
        visitor.visit(scenario);
        Response<List<Step>> response = new Response<>("check-actors", visitor.getStepsWithoutActor());
        logger.info("Sending check-actors response: {}", response);
        return response;
    }

    /***
     * Endpoint filters a scenario to a given depth
     * @param scenario Scenario converted from the body of the request
     * @return Response containing a filtered scenario
     */
    @PostMapping(path = "filter-scenario", produces = "application/json")
    public Response<Scenario> filterScenario(@RequestBody Scenario scenario, @RequestParam int maxLevel) {
        logger.info("Received filter-scenario request: {}", scenario);
        ScenarioFilterVisitor visitor = new ScenarioFilterVisitor(maxLevel);
        visitor.visit(scenario);
        Response<Scenario> filteredScenario = new Response<>("filter-scenario", new Scenario(scenario.title(), scenario.externalActors(), scenario.internalActors(), visitor.getIncludedSteps()));
        logger.info("Sending filter-scenario response: {}", filteredScenario);
        return filteredScenario;
    }

    @PostMapping(path = "numerate-steps", produces = "application/json")
    public Response<String> numerateSteps(@RequestBody Scenario scenario) {
        logger.info("Received numerate-steps request: {}", scenario);
        StepNumeratorVisitor visitor = new StepNumeratorVisitor();
        visitor.visit(scenario);
        Response<String> numeratedSteps = new Response<>("numerate-steps", visitor.getNumeratedStepsAsString());
        logger.info("Sending numerate-steps response: {}", numeratedSteps);
        return numeratedSteps;
    }

    @PostMapping(path = "search-expr", produces = "application/json")
    public Response<List<Step>> searchExpression(@RequestBody Scenario scenario, @RequestParam String expression) {
        logger.info("Received search-expr request: {}", scenario);
        SearchVisitor visitor = new SearchVisitor(expression);
        visitor.visit(scenario);
        Response<List<Step>> foundSteps = new Response<>("search-expr", visitor.getFoundSteps());
        logger.info("Sending search-expr response: {}", foundSteps);
        return foundSteps;
    }

}