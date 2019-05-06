package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IUserDao;
import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IUserService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import gq.dengbo.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {


    @Autowired
    private IUserDao userDao;

    @Override
    public User findByTel(String phone) {
        return null;
    }

    @Override
    public void save(User entity) {
        userDao.save(entity);
    }

    @Override
    public void update(User entity) {
userDao.update(entity);
    }

    @Override
    public void delete(User entity) {
userDao.delete(entity);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Serializable id) {
        return userDao.findById(id);
    }

    @Override
    public User login(String username, String password) {
        return userDao.find(username, MD5Utils.text2md5(password));
    }
}
