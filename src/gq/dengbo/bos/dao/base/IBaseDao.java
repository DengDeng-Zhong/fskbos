package gq.dengbo.bos.dao.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    /**
     * IBaseDao:公共的添加方法
     * */
    void save(T entity);

    /**
     * IBaseDao:公共的更新方法
     * */
    void update(T entity);

    /**
     * IBaseDao:公共的删除方法
     * */
    void delete(T entity);

    /**
     * IBaseDao:公共的查找方法
     * */
    List<T> findAll();

    /**
     * IBaseDao:公共的查找方法
     * */
    T findById(Serializable id);
}

