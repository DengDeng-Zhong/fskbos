package gq.dengbo.bos.dao.base;

import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Region;

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

    /**
     * 公共的更新方法
     * @param hql
     * @param objects
     */
    void executeUpdate(String hql, Object... objects );

    /**
     *
     * @param queryName 在映射文件中配置的名字
     * @param objects
     */
    void executeUpdateByQueryName(String queryName, Object... objects );

    void saveAll(List<T> list);

    /**
     * 公共的分页
     * @param pb
     */
    void pageQuery(PageBean<T> pb);
}

