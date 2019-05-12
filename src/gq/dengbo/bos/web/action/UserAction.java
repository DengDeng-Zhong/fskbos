package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IUserService;
import gq.dengbo.bos.utils.MD5Utils;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends BaseAction<User> {

    //创建日志对象
    Logger logger = Logger.getLogger(UserAction.class);


    public String login() {
        //1.获取参数
        String username = getModel().getUsername();
        String password = getModel().getPassword();
        //1_1.获取request
        HttpServletRequest request = ServletActionContext.getRequest();
        String serverCheckcode = (String) request.getSession().getAttribute("key");
        String clientCheckcode = request.getParameter("checkcode");
        logger.info("serverCheckcode:" + serverCheckcode);
        logger.info("clientCheckcode:" + clientCheckcode);
        if (serverCheckcode.equalsIgnoreCase(clientCheckcode)) {
            /**
             * 使用shiro，就不再使用userService的login方法来登录
             * 而是使用Subject的login方法
             */
            //获取一个Subject
            Subject subject = SecurityUtils.getSubject();

            //创建一个Token,这个对象存着用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.text2md5(password));

            try {
                subject.login(token);//内部就会执行Realm的代码

                //登录成功【把用户对象存在session】
                User loginUser = (User) subject.getPrincipal();
                subject.getSession().setAttribute("loginUser",loginUser);

                return "home";
            }catch (AuthenticationException e){
                e.printStackTrace();//登录失败会抛出异常
                addActionError("登录失败，用户名密码不正确");
            }

        } else {
            addActionError("验证码错误");
        }

        return "loginfailure";
    }

    //插入数据
    public String save() {
        System.out.println("请求参数:" + getModel());
        return NONE;
    }

    //修改数据
    public String update() {

        return NONE;
    }

    //删除数据
    public String delete() {

        return NONE;
    }

    //查找数据
    public String find() {

        return NONE;
    }

    public String logout() {
        //把session中数据清除
        ServletActionContext.getRequest().getSession().invalidate();
        return "login";
    }


    public String editPassword() throws IOException {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //1.获取密码
        String newPwd = getModel().getPassword();
        //2.获取用户id
        User user = (User) request.getSession().getAttribute("loginUser");
        String userId = user.getId();
        //3.调用service
        userService.modifyPassword(newPwd, userId);

        //4.返回一个数据

        response.setHeader("content-type", "text/json;charset=utf-8");
        response.getWriter().write("{\"success\":\"1\"}");

        return NONE;
    }


}
