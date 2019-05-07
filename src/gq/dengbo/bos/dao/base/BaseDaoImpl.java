package gq.dengbo.bos.dao.base;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> implements IBaseDao<T> {
    private Class<T> entityClass;

    public BaseDaoImpl() {
        System.out.println("公共Dao实现类的空参构造方法");
        //获取泛型的真实类型
        /**
         * 1.this.getClass().getGenericSuperclass() 获取泛型父类
         * 2.ParameterizedType 参数类型
         */
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取真实类型
        Type[] types = pt.getActualTypeArguments();
        //把type类型赋值给Class类型
        entityClass = (Class<T>) types[0];

    }

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(T entity) {
        hibernateTemplate.save(entity);
    }

    @Override
    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    @Override
    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

    @Override
    public List<T> findAll() {
        //entityClass.getSimpleName() 获取类名
        String hql = "from " + entityClass.getSimpleName();
        System.out.println("hql:" + hql);
        return (List<T>) this.hibernateTemplate.find(hql);
    }

    @Override
    public T findById(Serializable id) {
        return this.hibernateTemplate.get(entityClass, id);
    }

    @Override
    public void executeUpdate(String hql, Object... objects) {
        //获取session
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        //获取查询
        Query query = session.createQuery(hql);
        //设置参数
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        //执行
        query.executeUpdate();
    }

    @Override
    public void executeUpdateByQueryName(String queryName, Object... objects) {
        //获取session
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        //从映射文件中查找hql,并返回query对象
        Query query = session.getNamedQuery(queryName);
        //设置参数
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        //执行
        query.executeUpdate();
    }
}
