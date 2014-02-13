package io.jrocket.infra.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

/**
 * An example of AOP in action.
 */
@Aspect
@Named
public class AspectExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectExample.class);

    /**
     * Advice method that surround the call to the domain services.
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* io.jrocket.domain.*Service.*(..))")
    public Object inDomainServicesLayer(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.debug("Example of AOP usage (before method call)");
        return pjp.proceed();
    }

}
