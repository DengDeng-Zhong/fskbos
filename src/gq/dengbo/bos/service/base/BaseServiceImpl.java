package gq.dengbo.bos.service.base;

import gq.dengbo.bos.dao.IDecidedzoneDao;
import gq.dengbo.bos.dao.INoticebillDao;
import gq.dengbo.bos.dao.IWorkbillDao;
import gq.dengbo.bos.dao.IWorkordermanageDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    protected INoticebillDao noticebillDao;

    @Autowired
    protected IDecidedzoneDao decidedzoneDao;

    @Autowired
    protected IWorkbillDao workbillDao;

    @Autowired
    protected IWorkordermanageDao workordermanageDao;
}
