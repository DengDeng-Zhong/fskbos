package gq.dengbo.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import gq.dengbo.bos.model.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;


public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    /**ModelDriven用于存储form表单请求参数*/

    //模型对象
    protected T model;

    //=============分页查询返回json数据=========
    protected int page;
    protected int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    //分页的模型
    protected PageBean<T> pb = new PageBean<T>();


    @Override
    public T getModel() {
        return model;
    }

    public BaseAction() {
        //1.获得父类【BaseAction<T> 】的参数化类型【<T>、<T,V>】
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();

        //2.获得类的参数化类型的数组
        Type[] types = genericSuperclass.getActualTypeArguments();

        //3.获取泛型类第一个参数的类型
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) types[0];
        //设置离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
        pb.setDetachedCriteria(dc);
        try {
            model = entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //插入数据
    public abstract String save();
    //修改数据
    public abstract String update();
    //删除数据
    public abstract String delete() throws IOException;
    //查找数据
    public abstract String find();

    /**
     * 返回json数据给客户端
     * @param obj 转json的对象
     * @param excludes 排除的属性
     * @throws IOException
     */
    public void resopnseJson(Object obj, String[] excludes) throws IOException {
        JsonConfig config = new JsonConfig();
        config.setExcludes(excludes);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("content-type", "text/json;charset=utf-8");
        //如果是集合,使用JSONArray来进行转换
        if (obj instanceof Collection){
            JSONArray jsonArray = JSONArray.fromObject(obj,config);
            response.getWriter().write(jsonArray.toString());
        }else {
            JSONObject jsonObject = JSONObject.fromObject(obj, config);
            response.getWriter().write(jsonObject.toString());
        }

    }

    /**
     * 返回一个字符串
     * @param message
     * @throws IOException
     */
    public void resopnseStr(String message) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("content-type", "text/json;charset=utf-8");
        response.getWriter().write(message);

    }

}
