package ir.bootcamp.oneline.shop.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;

@Aspect
public class ServiceAspect {
    @Around("@annotation(ir.bootcamp.oneline.shop.common.Transaction) && execution(public * *(..))")
    public Object makeItTransactional(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Session session = HibernateSessionUtils.openSession();
        session.beginTransaction();
        try {
            Object result = proceedingJoinPoint.proceed();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }

    @Around("@annotation(ir.bootcamp.oneline.shop.common.NonTransactionQuery) && execution(public * *(..))")
    public Object makeNonTransactionalQuery(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Session session = HibernateSessionUtils.openSession();
        Object proceed = proceedingJoinPoint.proceed();
        session.close();

        return proceed;

    }

}
