package ir.bootcamp.oneline.shop.service.impl;

import ir.bootcamp.oneline.shop.common.HibernateSessionUtils;
import org.hibernate.Session;

public class BaseService {
    protected Session openSession() {
        return HibernateSessionUtils.openSession();
    }
}
