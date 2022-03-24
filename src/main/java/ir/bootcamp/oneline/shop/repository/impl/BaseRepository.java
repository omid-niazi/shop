package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.common.HibernateSessionUtils;
import ir.bootcamp.oneline.shop.repository.RepositoryInterface;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class BaseRepository<T> implements RepositoryInterface<T> {
    private Class<T> clazz;

    public BaseRepository(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public T save(T t) {
        getCurrentSession().save(t);
        return t;
    }

    @Override
    public Optional<T> find(Long id) {
        return Optional.ofNullable(getCurrentSession().find(clazz, id));
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName(), clazz)
                .getResultList();
    }

    @Override
    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    @Override
    public void update(T t) {
        getCurrentSession().update(t);
    }

    @Override
    public void deleteAll() {
        getCurrentSession().createQuery("delete from " + clazz.getSimpleName())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return HibernateSessionUtils.getCurrentSession();
    }
}
