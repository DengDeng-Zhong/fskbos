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

    /**
     * 批量删除取派员
     *
     * @param ids 【001,002】 以逗号隔开
     */
    void deleteBatch(String ids);
}
