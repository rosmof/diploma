package ro.rosmof.model.annotations;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Usually when inside an transaction and error is thrown, you must use another
 * transaction in order to save the error details to the database.
 * <p>
 * Another strategy is to raise a {@link RuntimeException} and handle it outside the
 * procedure (probably at controller level)
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRES_NEW)
public @interface DiplomaErrorTransaction {

}
