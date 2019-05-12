package gq.dengbo.bos.web.realm;

import gq.dengbo.bos.dao.IUserDao;
import gq.dengbo.bos.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BosRealm extends AuthorizingRealm {
    /**
     * 权限-与角色权关
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("staff");
        info.addRole("staff");

        return info;
    }

    @Autowired
    private IUserDao userDao;
    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("AuthenticationInfo...");
//查询数据操作放在realm
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //1.获取用户名
        String username = upToken.getUsername();

        //2.根据名字查询用户
        User dbUser = userDao.findByUsername(username);

        if(dbUser != null){

            /**
             * Object principal, 把数据库查询的对象
             * Object credentials,证书：写密码自动验证，查询出来的密码
             * String realmName,当前的类名
             */
            //返回AuthenticationInfo对象,【这个对象会自动验证密码】
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(dbUser,
                            dbUser.getPassword(),
                            this.getClass().getSimpleName());

            return info;
        }
        return null;
    }
}
