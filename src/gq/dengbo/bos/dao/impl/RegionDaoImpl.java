package gq.dengbo.bos.dao.impl;

import gq.dengbo.bos.dao.IRegionDao;
import gq.dengbo.bos.dao.base.BaseDaoImpl;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Region;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    @Override
    public void pageQuery(PageBean<Region> pb) {
        //执行查询

        //1.查询总记录数
        //获取离线的查询对象
        DetachedCriteria dc = pb.getDetachedCriteria();
        //设置查询总记录数条件
        dc.setProjection(Projections.rowCount());

        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        Long total = list.get(0);

        pb.setTotal(total.intValue());

        // 2.查询分页数据
        dc.setProjection(null);
        int start = (pb.getCurrentPage() - 1) * pb.getPageSize();
        int length = pb.getPageSize();
        List<Region> rows = (List<Region>) getHibernateTemplate().findByCriteria(dc, start, length);
        pb.setRows(rows);



    }
}
