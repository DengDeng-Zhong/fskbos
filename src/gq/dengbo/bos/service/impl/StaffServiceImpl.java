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
        //实际可以不写
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

    /**
     * 批量删除取派员
     *
     * @param ids 【001,002】 以逗号隔开
     */
    @Override
    public void deleteBatch(String ids) {
        String hql = "UPDATE Staff SET deltag = ? WHERE id = ?";

        //拆分id
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            staffDao.executeUpdate(hql, "1", id);
        }
    }
}
