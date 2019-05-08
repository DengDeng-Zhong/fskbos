package gq.dengbo.bos.service;

import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.base.IBaseService;

public interface IStaffService extends IBaseService<Staff> {

    /**
     * 分页查询
     * @param pb
     */
    void pageQuery(PageBean<Staff> pb);
}
