import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleInitializationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleInitializationTest.class);

    @Test
    public void testConfiguration() {
        LOGGER.info("Config module is configured");
    }
}
