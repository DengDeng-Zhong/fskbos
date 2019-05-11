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
import java.util.List;

@Service
@Transactional
public class NoticeBillServiceImpl extends BaseServiceImpl<Noticebill> implements INoticebillService {


    @Override
    public void save(Noticebill entity) {
        entity.setOrdertype("新增");

        entity.setUser(BosContextUtils.loginUser());
        Staff staff = null;
        entity.setStaff(staff);
        noticebillDao.save(entity);
    }

    public void save(Noticebill entity, String decidedzone) {
        entity.setOrdertype("新增");

        entity.setUser(BosContextUtils.loginUser());
        if (!StringUtils.isEmpty(decidedzone)){
            Decidedzone dz = decidedzoneDao.findById(decidedzone);
            entity.setStaff(dz.getStaff());
            noticebillDao.save(entity);
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
