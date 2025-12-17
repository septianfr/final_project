package web.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = "web.steps",
        tags = "@web",
        plugin = {"pretty", "html:reports/web-reports.html", "json:reports/web-reports.json"},
        monochrome = true
)
public class WebRunner {}

