package pl.poznan.put.checker.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.checker.logic.MainScenario;

@RestController
@RequestMapping("/")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @PostMapping(path = "echo", produces = "application/json")
    public MainScenario echo(@RequestBody MainScenario body) {
        logger.info("Received echo request: {}", body);

        return body;
    }
}