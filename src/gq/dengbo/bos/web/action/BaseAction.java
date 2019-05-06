package gq.dengbo.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    /**ModelDriven用于存储form表单请求参数*/

    //模型对象
    protected T model;

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
    public abstract String delete();
    //查找数据
    public abstract String find();



}
