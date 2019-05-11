package gq.dengbo.bos.service;

import gq.dengbo.bos.model.Decidedzone;
import gq.dengbo.bos.model.Noticebill;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.service.base.IBaseService;

public interface INoticebillService extends IBaseService<Noticebill> {
    /**
     *
     * @param entity 业务通知单的模型
     * @param decidedzone 定区id,用于自动分单
     */
    void save(Noticebill entity, String decidedzone);
}
