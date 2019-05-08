package gq.dengbo.bos.dao;

import gq.dengbo.bos.dao.base.IBaseDao;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Region;
import gq.dengbo.bos.model.User;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region> {


    void pageQuery(PageBean<Region> pb);
}
