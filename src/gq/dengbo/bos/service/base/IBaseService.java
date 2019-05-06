package gq.dengbo.bos.service.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(Serializable id);
}
