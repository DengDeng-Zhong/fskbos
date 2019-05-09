package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IDecidedzoneDao;
import gq.dengbo.bos.dao.ISubareaDao;
import gq.dengbo.bos.model.Decidedzone;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Subarea;
import gq.dengbo.bos.service.IDecidedzoneService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class DecidedzoneServiceImpl extends BaseServiceImpl<Decidedzone> implements IDecidedzoneService {

    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private ISubareaDao subareaDao;

    public void save(Decidedzone entity) {

    }

    public void update(Decidedzone entity) {

    }

    public void delete(Decidedzone entity) {

    }

    public List<Decidedzone> findAll() {
        return null;
    }

    public Decidedzone findById(Serializable id) {
        return null;
    }

    @Override
    public void save(Decidedzone dz, String[] subareaIds) {
        //添加分区
        decidedzoneDao.save(dz);
        //更新分区的decided_id
        for (String subareaId : subareaIds) {
            Subarea subarea = subareaDao.findById(subareaId);
            subarea.setDecidedzone(dz);
        }
    }

    @Override
    public void pageQuery(PageBean<Decidedzone> pb) {
        decidedzoneDao.pageQuery(pb);
    }
}
