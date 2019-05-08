package gq.dengbo.bos.service;

import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Region;
import gq.dengbo.bos.service.base.IBaseService;

import java.util.List;

public interface IRegionService extends IBaseService<Region> {


    void saveAll(List<Region> regions);

    void pageQuery(PageBean<Region> pb);
}
