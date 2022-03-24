package ir.bootcamp.oneline.shop.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    T save(T t);

    Optional<T> find(Long id);

    List<T> findAll();

    void delete(T t);

    void update(T t);

    void deleteAll();
}
