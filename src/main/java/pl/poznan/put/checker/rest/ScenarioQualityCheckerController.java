package pl.poznan.put.checker.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.visitors.CountAllStepsVisitor;
import pl.poznan.put.checker.logic.visitors.CountKeywordStepsVisitor;

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
    public IntegerResponse countAllSteps(@RequestBody Scenario scenario) {
        logger.info("Received count-steps request: {}", scenario);
        CountAllStepsVisitor visitor = new CountAllStepsVisitor();
        visitor.visit(scenario);
        IntegerResponse response = new IntegerResponse("step-count", visitor.getNumberOfSteps());
        logger.info("Sending count-steps response: {}", response);
        return response;
    }


    @PostMapping(path = "count-keywords", produces = "application/json")
    public IntegerResponse countKeywordSteps(@RequestBody Scenario scenario) {
        logger.info("Received count-keywords request: {}", scenario);
        CountKeywordStepsVisitor visitor = new CountKeywordStepsVisitor();
        visitor.visit(scenario);
        IntegerResponse response = new IntegerResponse("keyword-count", visitor.getNumberOfSteps());
        logger.info("Sending count-keywords response: {}", response);
        return response;
    }
}