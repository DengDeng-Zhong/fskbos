package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IDecidedzoneDao;
import gq.dengbo.bos.dao.INoticebillDao;
import gq.dengbo.bos.dao.ISubareaDao;
import gq.dengbo.bos.model.*;
import gq.dengbo.bos.service.IDecidedzoneService;
import gq.dengbo.bos.service.INoticebillService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import gq.dengbo.bos.utils.BosContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class NoticeBillServiceImpl extends BaseServiceImpl<Noticebill> implements INoticebillService {


    @Override
    public void save(Noticebill entity) {

    }

    public void save(Noticebill entity, String decidedzone) {
        //1.添加业务通知单类型
        entity.setOrdertype("手动");//业务通知单-自动分单-手动分单

        //2.设置客服的id【登录用户】
        entity.setUser(BosContextUtils.loginUser());

        noticebillDao.save(entity);//insert语句

        //3.自动分单【通过定区找到负责人】
        if (!StringUtils.isEmpty(decidedzone)) {
            entity.setOrdertype("自动");//最后-update

            Decidedzone dz = decidedzoneDao.findById(decidedzone);//select
            Staff staff = dz.getStaff();//select
            entity.setStaff(staff);

            //4.往 "工单" 表插入数据
            Workbill workbill = new Workbill();
            workbill.setType("新单");//工单类型：新、追、改、销
            workbill.setPickstate("未取件");//未取件-取件中-收件
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单创建时间
            workbill.setAttachbilltimes(0);//取到快件时间
            workbill.setRemark(entity.getRemark());

            //往 工单 添加 业务通知单的id
            workbill.setNoticebill(entity);

            //往 工单 添加 员工的id
            workbill.setStaff(staff);

            workbillDao.save(workbill);//insert语句
        }
    }
    @Override
    public void update(Noticebill entity) {

    }

    @Override
    public void delete(Noticebill entity) {

    }

    @Override
    public List<Noticebill> findAll() {
        return null;
    }

    @Override
    public Noticebill findById(Serializable id) {
        return null;
    }
}
