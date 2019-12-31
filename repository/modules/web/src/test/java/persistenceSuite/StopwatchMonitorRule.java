package persistenceSuite;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopwatchMonitorRule extends Stopwatch {

    private Logger logger = LoggerFactory.getLogger(StopwatchMonitorRule.class.getSimpleName());

    @Override
    protected void succeeded(long nanos, Description description) {
        logResult(description, "success", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logResult(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logResult(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logResult(description, "finished", nanos);
    }

    private void logResult(Description description, String status, long nanos) {
        String mtdName = description.getMethodName();
        String clsName = description.getClassName();
        logger.info(String.format("Test [ %s##%s ] finished with { %s } in %(,.2f) millis",
                clsName, mtdName, status.toUpperCase(), (double) (nanos / 1000)));
    }
}
