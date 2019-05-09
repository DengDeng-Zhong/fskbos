package gq.dengbo.bos.service;

import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Subarea;
import gq.dengbo.bos.service.base.IBaseService;

import java.util.List;

public interface ISubareaService extends IBaseService<Subarea> {
    void pageQuery(PageBean<Subarea> pb);

    List<Subarea> findAllWithUnbind();
}
