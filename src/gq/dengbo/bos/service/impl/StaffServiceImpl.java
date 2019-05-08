package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IStaffDao;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.IStaffService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements IStaffService {


    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff entity) {
        staffDao.save(entity);
    }

    @Override
    public void update(Staff entity) {
        staffDao.update(entity);
    }

    @Override
    public void delete(Staff entity) {
        staffDao.delete(entity);
    }

    @Override
    public List<Staff> findAll() {
        return staffDao.findAll();
    }

    @Override
    public Staff findById(Serializable id) {
        return staffDao.findById(id);
    }

    /**
     * 分页查询
     *
     * @param pb
     */
    public void pageQuery(PageBean<Staff> pb) {
        staffDao.pageQuery(pb);
    }
}
