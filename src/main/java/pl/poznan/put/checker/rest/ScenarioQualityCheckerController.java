package pl.poznan.put.checker.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.checker.logic.Scenario;
import pl.poznan.put.checker.logic.visitors.CountAllStepsVisitor;

@RestController
@RequestMapping("/")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @PostMapping(path = "echo", produces = "application/json")
    public Scenario echo(@RequestBody Scenario body) {
        logger.info("Received echo request: {}", body);

        return body;
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
}