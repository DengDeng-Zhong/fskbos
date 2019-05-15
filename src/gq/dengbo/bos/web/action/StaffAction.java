package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.IStaffService;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class StaffAction extends BaseAction<Staff> {

    //创建日志对象
    Logger logger = Logger.getLogger(StaffAction.class);


//    @RequiresPermissions("staff")
    @Override
    public String save() {
        System.out.println(getModel());
        staffService.save(getModel());
        return SUCCESS;//回到取派列表的界面
    }

    @Override
    public String update() {
        //【游离/托管状态 session没有缓存 有id】
        System.out.println("表单提交的:" + getModel());

        staffService.update(getModel());
        return null;
    }

    //==================删除取派员==================
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String delete() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("delete");


        //1.获取删除的id

        //2.调用service
        staffService.deleteBatch(ids);
        System.out.println(ids);
        //3.响应
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().write("success");
        return NONE;
    }

    @Override
    public String find() {
        return null;
    }

    public void pageQuery() throws IOException {
        /*
            1.接收参数,page[当前页] rows[每页显示多少条]
            2.调用service,参数里传一个pageBean
            3.返回json数据
         */
        pb.setCurrentPage(page);
        pb.setPageSize(rows);
        staffService.pageQuery(pb);
//        System.out.println(pb);
        //返回json数据
        resopnseJson(pb,new String[]{"currentPage", "pageSize", "detachedCriteria"});

    }

    public void listJson() throws IOException {

        //在职员工
        List<Staff> list = staffService.findAllWithNoDel();
        System.out.println(list.size());
        resopnseJson(list,new String[]{"telephone","haspda","deltag","station","standard"});
    }
}
