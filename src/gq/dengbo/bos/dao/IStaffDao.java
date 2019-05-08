package gq.dengbo.bos.dao;

import gq.dengbo.bos.dao.base.IBaseDao;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Staff;

public interface IStaffDao extends IBaseDao<Staff> {


    void pageQuery(PageBean<Staff> pb);
}
