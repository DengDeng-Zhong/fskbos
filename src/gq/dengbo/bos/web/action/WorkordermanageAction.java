package gq.dengbo.bos.web.action;

import com.gyf.crm.domain.Customer;
import gq.dengbo.bos.model.Decidedzone;
import gq.dengbo.bos.model.Workordermanage;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;

public class WorkordermanageAction extends BaseAction<Workordermanage> {


    @Override
    public String save() {
        workordermanageService.save(getModel());
        try {
            resopnseStr("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
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
