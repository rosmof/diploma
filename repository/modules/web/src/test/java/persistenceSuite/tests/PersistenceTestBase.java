package persistenceSuite.tests;

import org.junit.ClassRule;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import persistenceSuite.StopwatchMonitorRule;

import java.util.Objects;

/**
 * This is the base class for persistence tests.
 * <p>
 * The classic {@link org.junit.runner.RunWith}
 * {@link org.springframework.test.context.junit4.SpringJUnit4ClassRunner}
 * can be replaced by the 2 {@Rule} implemented recently in
 * Spring, {@link SpringClassRule} and {@link SpringMethodRule}.
 * </p>
 *
 * <p>
 * By using this approach you can overcome the {@link org.junit.runner.RunWith}
 * single declaration limitation and thus
 * can use the spring context in conjunction with other
 * runners such as {@link org.junit.runners.Parameterized} or
 * some Mock4J runners
 * </p>
 *
 * <p>
 * The rules are included in the base class so they will only
 * be declared once.
 * </p>
 */
public class PersistenceTestBase {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule SPRING_METHOD_RULE = new SpringMethodRule();

    @ClassRule
    public static final StopwatchMonitorRule monitoringRule = new StopwatchMonitorRule();

    Logger logger;

    PersistenceTestBase(String loggerName) {
        logger = LoggerFactory.getLogger(Objects.isNull(loggerName) ? "default_logger" : loggerName);
    }


}
