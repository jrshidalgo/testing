import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/PayrollRun.feature",
        glue = "stepdefinitions",
        plugin = { "pretty" }
)
public class TestRunner {
}