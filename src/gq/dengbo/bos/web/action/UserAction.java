package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IUserService;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends BaseAction<User> {

    //创建日志对象
    Logger logger = Logger.getLogger(UserAction.class);

    @Autowired
    private IUserService userService;

    public String login() {
        //1.获取参数
        String username = getModel().getUsername();
        String password = getModel().getPassword();
        //1_1.获取request
        HttpServletRequest request = ServletActionContext.getRequest();
        String serverCheckcode = (String) request.getSession().getAttribute("key");
        String clientCheckcode = request.getParameter("checkcode");
        logger.info("serverCheckcode:{1}" + serverCheckcode);
        logger.warning("test");
        System.out.println("clientCheckcode:" + clientCheckcode);
        if (serverCheckcode.equalsIgnoreCase(clientCheckcode)) {
            //2.调用service
            User user = userService.login(username, password);

            //3.判断登录状态
            if (user != null) {
                System.out.println("登录成功");
                request.getSession().setAttribute("loginUser", user);
                addActionMessage("登录成功");
                return "home";
            } else {
                System.out.println("登录失败,用户名或密码不正确");
                addActionError("登录失败,用户名或密码不正确");
            }
        } else {
            System.out.println("验证码错误");
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

        HttpServletRequest  request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //1.获取密码
        String newPwd = getModel().getPassword();
        //2.获取用户id
        User user = (User) request.getSession().getAttribute("loginUser");
        String userId = user.getId();
        //3.调用service
        userService.modifyPassword(newPwd, userId);

        //4.返回一个数据

        response.setHeader("content-type","text/json;charset=utf-8");
        response.getWriter().write("{\"success\":\"1\"}");

        return NONE;
    }


}
