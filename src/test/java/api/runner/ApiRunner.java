package api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "api.steps",
        tags = "@api",
        plugin = {"pretty", "html:reports/api-reports.html", "json:reports/api-reports.json"},
        monochrome = true
)

public class ApiRunner {
}
