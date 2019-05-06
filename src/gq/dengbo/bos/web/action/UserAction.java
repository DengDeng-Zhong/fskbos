package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IUserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class UserAction extends BaseAction<User> {

    //创建日志对象
    Logger logger = Logger.getLogger(UserAction.class);

    @Autowired
    private IUserService userService;

    public String login(){
        //1.获取参数
        String username = getModel().getUsername();
        String password = getModel().getPassword();
        //1_1.获取request
        HttpServletRequest request = ServletActionContext.getRequest();
        String serverCheckcode = (String) request.getSession().getAttribute("key");
        String clientCheckcode = request.getParameter("checkcode");
        logger.info("serverCheckcode:{1}"+serverCheckcode);
        logger.warning("test");
        System.out.println("clientCheckcode:"+clientCheckcode);
        if (serverCheckcode.equalsIgnoreCase(clientCheckcode)){
            //2.调用service
            User user = userService.login(username, password);

            //3.判断登录状态
            if (user!=null){
                System.out.println("登录成功");
                request.getSession().setAttribute("loginUser",user);
                addActionMessage("登录成功");
                return "home";
            }else{
                System.out.println("登录失败,用户名或密码不正确");
                addActionError("登录失败,用户名或密码不正确");
            }
        }else{
            System.out.println("验证码错误");
            addActionError("验证码错误");
        }

        return "loginfailure";
    }

    //插入数据
    public String save(){
        System.out.println("请求参数:"+getModel());
        return NONE;
    }
    //修改数据
    public String update(){

        return NONE;
    }
    //删除数据
    public String delete(){

        return NONE;
    }
    //查找数据
    public String find(){

        return NONE;
    }
}
