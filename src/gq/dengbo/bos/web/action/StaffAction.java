package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.IStaffService;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
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



}
