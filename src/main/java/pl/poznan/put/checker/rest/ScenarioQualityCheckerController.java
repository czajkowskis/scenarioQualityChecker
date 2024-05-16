package pl.poznan.put.checker.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.Step;
import pl.poznan.put.checker.logic.visitors.ActorCheckingVisitor;
import pl.poznan.put.checker.logic.visitors.CountAllStepsVisitor;
import pl.poznan.put.checker.logic.visitors.CountKeywordStepsVisitor;
import pl.poznan.put.checker.logic.visitors.ScenarioFilterVisitor;

import java.util.List;

@RestController
@RequestMapping("/")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @PostMapping(path = "echo", produces = "application/json")
    public Scenario echo(@RequestBody Scenario scenario) {
        logger.info("Received echo request: {}", scenario);

        return scenario;
    }

    @PostMapping(path = "count-steps", produces = "application/json")
    public Response<Integer> countAllSteps(@RequestBody Scenario scenario) {
        logger.info("Received count-steps request: {}", scenario);
        CountAllStepsVisitor visitor = new CountAllStepsVisitor();
        visitor.visit(scenario);
        Response<Integer> response = new Response<>("step-count", visitor.getNumberOfSteps());
        logger.info("Sending count-steps response: {}", response);
        return response;
    }


    @PostMapping(path = "count-keywords", produces = "application/json")
    public Response<Integer> countKeywordSteps(@RequestBody Scenario scenario) {
        logger.info("Received count-keywords request: {}", scenario);
        CountKeywordStepsVisitor visitor = new CountKeywordStepsVisitor();
        visitor.visit(scenario);
        Response<Integer> response = new Response<>("keyword-count", visitor.getNumberOfSteps());
        logger.info("Sending count-keywords response: {}", response);
        return response;
    }


    @PostMapping(path = "check-actors", produces = "application/json")
    public Response<List<Step>> checkActors(@RequestBody Scenario scenario) {
        logger.info("Received check-actors request: {}", scenario);
        ActorCheckingVisitor visitor = new ActorCheckingVisitor(scenario.externalActors(), scenario.internalActors());
        visitor.visit(scenario);
        Response<List<Step>> response = new Response<>("check-actors", visitor.getStepsWithoutActor());
        logger.info("Sending check-actors response: {}", response);
        return response;
    }

    @PostMapping(path = "filter-scenario/{maxLevel}", produces = "application/json")
    public Response<Scenario> filterScenario(@RequestBody Scenario scenario, @PathVariable int maxLevel) {
        logger.info("Received filter-scenario request: {}", scenario);
        ScenarioFilterVisitor visitor = new ScenarioFilterVisitor(maxLevel, 0);
        visitor.visit(scenario);
        Response<Scenario> filteredScenario = new Response<>("filter-scenario", new Scenario(scenario.title(), scenario.externalActors(), scenario.internalActors(), visitor.getIncludedSteps()));
        logger.info("Sending filter-scenario response: {}", filteredScenario);
        return filteredScenario;
    }
}