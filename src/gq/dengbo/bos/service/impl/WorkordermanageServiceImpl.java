package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IDecidedzoneDao;
import gq.dengbo.bos.dao.ISubareaDao;
import gq.dengbo.bos.dao.IWorkordermanageDao;
import gq.dengbo.bos.model.Decidedzone;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Subarea;
import gq.dengbo.bos.model.Workordermanage;
import gq.dengbo.bos.service.IDecidedzoneService;
import gq.dengbo.bos.service.IWorkordermanageService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class WorkordermanageServiceImpl extends BaseServiceImpl<Workordermanage> implements IWorkordermanageService {


    @Override
    public void save(Workordermanage entity) {
        workordermanageDao.save(entity);
    }

    @Override
    public void update(Workordermanage entity) {

    }

    @Override
    public void delete(Workordermanage entity) {

    }

    @Override
    public List<Workordermanage> findAll() {
        return null;
    }

    @Override
    public Workordermanage findById(Serializable id) {
        return null;
    }



}
