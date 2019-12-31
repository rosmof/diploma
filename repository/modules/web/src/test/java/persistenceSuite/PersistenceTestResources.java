package persistenceSuite;

import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class PersistenceTestResources extends ExternalResource {
    @Override
    public Statement apply(Statement base, Description description) {
        System.out.println("called apply");
        return super.apply(base, description);
    }

    @Override
    protected void before() throws Throwable {
        System.out.println("called before");
        super.before();
    }

    @Override
    protected void after() {
        System.out.println("called after");
        super.after();
    }
}
