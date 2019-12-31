package persistenceSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import persistenceSuite.tests.PasswordCheckClass;
import persistenceSuite.tests.UserTestClass;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserTestClass.class, PasswordCheckClass.class})
public class PersistenceSuite extends PersistenceBaseSuite {

}
