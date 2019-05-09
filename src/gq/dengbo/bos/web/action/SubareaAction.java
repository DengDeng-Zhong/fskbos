package gq.dengbo.bos.web.action;

import gq.dengbo.bos.model.Subarea;
import gq.dengbo.bos.service.ISubareaService;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    @Override
    public String save() {
        System.out.println("数据1:"+getModel());
        System.out.println("数据2:"+getModel().getRegion());

        subareaService.save(getModel());
        return SUCCESS;
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String delete() throws IOException {
        return null;
    }

    @Override
    public String find() {
        return null;
    }

    public void pageQuery() throws IOException {
        pb.setCurrentPage(page);
        pb.setPageSize(rows);

        subareaService.pageQuery(pb);

        // 获取数据时,把分区Subarea的Region的加载方式改成懒加载
        resopnseJson(pb, new String[]{"currentPage", "pageSize", "detachedCriteria","subareas"});

    }
}
