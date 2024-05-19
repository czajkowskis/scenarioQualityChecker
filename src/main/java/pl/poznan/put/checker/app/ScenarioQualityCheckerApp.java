package pl.poznan.put.checker.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.poznan.put.checker.rest.ScenarioQualityCheckerController;

import java.util.Map;

/***
 * Entry point of the application
 */
@SpringBootApplication(scanBasePackageClasses = {ScenarioQualityCheckerController.class})
public class ScenarioQualityCheckerApp {
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        SpringApplication app = new SpringApplication(ScenarioQualityCheckerApp.class);
        app.setDefaultProperties(Map.of("server.port", port));
        app.run(args);
    }
}