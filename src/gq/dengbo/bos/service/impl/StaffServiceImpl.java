package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IStaffDao;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.IStaffService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

        //1.根据id从数据库获取数据【持久状态 session有缓存，有id】
        Staff staff = staffDao.findById(entity.getId());
        //2.更新数据库的数据
        staff.setName(entity.getName());
        staff.setTelephone(entity.getTelephone());
        staff.setStation(entity.getStation());
        staff.setHaspda(entity.getHaspda());
        staff.setStandard(entity.getStandard());
        System.out.println("数据库的:"+staff);

        //实际可以不写
        //staffDao.update(entity);
    }

    @Override
    public void delete(Staff entity) {
        staffDao.delete(entity);
    }

    public List<Staff> findAll() {
        return staffDao.findAll();
    }

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
    public void deleteBatch(String ids) {
        String hql = "UPDATE Staff SET deltag = ? WHERE id = ?";

        //拆分id
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            staffDao.executeUpdate(hql, "1", id);
        }
    }

    /**
     * 查找所有在职的员工
     * @return 集合
     */
    public List<Staff> findAllWithNoDel() {
        //离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        dc.add(Restrictions.eq("deltag","0"));
        return staffDao.findAllByDetachedCriteria(dc);
    }
}
