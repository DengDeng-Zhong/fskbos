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
}
