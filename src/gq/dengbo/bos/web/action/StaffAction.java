package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.IStaffService;
import gq.dengbo.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StaffAction extends BaseAction<Staff> {

    //创建日志对象
    Logger logger = Logger.getLogger(StaffAction.class);

    @Autowired
    private IStaffService staffService;

    @Override
    public String save() {
        System.out.println(getModel());
        staffService.save(getModel());
        return SUCCESS;//回到取派列表的界面
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String find() {
        return null;
    }

    //=========================分页查询返回json数据
    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void pageQuery() throws IOException {
        /*
            1.接收参数,page[当前页] rows[每页显示多少条]
            2.调用service,参数里传一个pageBean
            3.返回json数据
         */
        PageBean<Staff> pb = new PageBean<Staff>();
        pb.setCurrentPage(page);
        pb.setPageSize(rows);
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        pb.setDetachedCriteria(dc);

        staffService.pageQuery(pb);

        System.out.println(pb);

        //返回json数据

        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});

        JSONObject jsonObject =JSONObject.fromObject(pb,config);
        jsonObject.toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("content-type","text/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }


}
