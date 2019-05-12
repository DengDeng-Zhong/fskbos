package gq.dengbo.bos.dao.impl;

import gq.dengbo.bos.dao.IUserDao;
import gq.dengbo.bos.dao.base.BaseDaoImpl;
import gq.dengbo.bos.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    @Override
    public User find(String username, String password) {

        String hql ="FROM User WHERE username = ? AND password = ?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, username, password);
        if (list.size() == 1){
            return list.get(0);
        }

        return null;

    }

    @Override
    public User findByUsername(String username) {
        String hql ="FROM User WHERE username = ?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
        if (list.size() == 1){
            return list.get(0);
        }

        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }



}
