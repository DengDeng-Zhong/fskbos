package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.IRegionDao;
import gq.dengbo.bos.dao.IStaffDao;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Region;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.service.IRegionService;
import gq.dengbo.bos.service.IStaffService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class RegionServiceImpl extends BaseServiceImpl<Region> implements IRegionService {


    @Autowired
    private IRegionDao regionDao;

    @Override
    public void save(Region entity) {
        regionDao.save(entity);
    }

    @Override
    public void update(Region entity) {

    }

    @Override
    public void delete(Region entity) {

    }

    @Override
    public List<Region> findAll() {
        return null;
    }

    @Override
    public Region findById(Serializable id) {
        return null;
    }

    @Override
    public void saveAll(List<Region> regions) {
        regionDao.saveAll(regions);
    }

    @Override
    public void pageQuery(PageBean<Region> pb) {
        regionDao.pageQuery(pb);
    }
}
