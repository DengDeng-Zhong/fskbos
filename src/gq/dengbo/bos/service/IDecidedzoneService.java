package gq.dengbo.bos.service;

import gq.dengbo.bos.model.Decidedzone;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.service.base.IBaseService;

public interface IDecidedzoneService extends IBaseService<Decidedzone> {

    /**
     *
     * @param dz    定区数据
     * @param subareaIds    分区的ids
     */
    void save(Decidedzone dz,String[] subareaIds);

    void pageQuery(PageBean<Decidedzone> pb);
}
