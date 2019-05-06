package gq.dengbo.bos.service;

import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.base.IBaseService;

public interface IUserService extends IBaseService<User> {
    //特有的业务方法
    User findByTel(String phone);

    User login(String username, String password);


}
