package gq.dengbo.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import gq.dengbo.bos.model.User;
import org.apache.struts2.ServletActionContext;

public class BOSLoginInterceptor extends MethodFilterInterceptor {
    /**
     *
     * @param actionInvocation
     * @return action的方法返回值
     * @throws Exception
     */
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("拦截了:"+ServletActionContext.getRequest().getRequestURL());
        //是否直接登录
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        System.out.println(user);
        if (user!=null){
            return actionInvocation.invoke();
        }
        return "login";
    }
    /**
     * 1.写个拦截器
     * 2.在struts.xml配置一个登录拦截器
     * 3.配置一个拦截组,把登录拦截器和struts配置拦截器添加进组
     * 4.修改struts默认拦截器
     * 5.在登录拦截器中排出一些不需要拦截的方法
     */
}
