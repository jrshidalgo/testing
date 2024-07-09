import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/PayrollRunScenario.feature",
        glue = "stepdefinitions",
        plugin = { "pretty" },
        tags = "@downloadPayrollSummary"
)
public class TestRunner {
}