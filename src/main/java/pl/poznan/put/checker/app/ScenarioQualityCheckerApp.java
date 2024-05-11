package pl.poznan.put.checker.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.poznan.put.checker.rest.ScenarioQualityCheckerController;

@SpringBootApplication(scanBasePackageClasses = {ScenarioQualityCheckerController.class})
public class ScenarioQualityCheckerApp {
    public static void main(String[] args) {
        SpringApplication.run(ScenarioQualityCheckerApp.class, args);
    }
}