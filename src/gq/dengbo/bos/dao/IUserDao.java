package gq.dengbo.bos.dao;

import gq.dengbo.bos.dao.base.IBaseDao;
import gq.dengbo.bos.model.User;

public interface IUserDao extends IBaseDao<User> {
    //用户特有的数据库操作方法,这里什么都不写,后面有需求在添加

    User findUserByEmail(String email);

    User find(String username, String password);

    User findByUsername(String username);
}
