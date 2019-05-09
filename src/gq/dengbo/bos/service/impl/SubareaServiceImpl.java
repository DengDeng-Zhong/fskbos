package gq.dengbo.bos.service.impl;

import gq.dengbo.bos.dao.ISubareaDao;
import gq.dengbo.bos.model.Subarea;
import gq.dengbo.bos.service.ISubareaService;
import gq.dengbo.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Service
@Transactional
public class SubareaServiceImpl extends BaseServiceImpl<Subarea> implements ISubareaService {

    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void save(Subarea entity) {
        subareaDao.save(entity);
    }

    @Override
    public void update(Subarea entity) {

    }

    @Override
    public void delete(Subarea entity) {

    }

    @Override
    public List<Subarea> findAll() {
        return null;
    }

    @Override
    public Subarea findById(Serializable id) {
        return null;
    }
}