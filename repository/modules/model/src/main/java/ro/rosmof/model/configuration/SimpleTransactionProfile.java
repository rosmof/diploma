package ro.rosmof.model.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StopWatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class SimpleTransactionProfile implements Ordered {

    static final Logger logger = LoggerFactory.getLogger(SimpleTransactionProfile.class);

    private int order;

    @Autowired
    private EntityManagerFactory emFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public int getOrder() {
        return 0;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object profile(ProceedingJoinPoint call) throws Throwable {
        Object returnValue;
        StopWatch sw = new StopWatch(getClass().getName());
        try {
            EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(emFactory);
            if (Optional.ofNullable(em).isPresent()) {
                EntityTransaction transaction = em.getTransaction();
                if (transaction.isActive()) {
                    System.out.println("so now the transaction is active");
                } else {
                    System.out.println("The transaction is not active");
                }
            }
            sw.start(call.toShortString());
            returnValue = call.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }

        return returnValue;
    }
}
