package gq.dengbo.bos.web.action;

import com.gyf.crm.domain.Customer;
import gq.dengbo.bos.web.action.base.BaseAction;

import java.io.IOException;

public class NoticebillAction extends BaseAction<Object> {
    @Override
    public String save() {
        return null;
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


    private String tel;

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 返回json数据
     */
    public void findCustomerByTel() throws IOException {
        Customer customerByTel = customerService.findCustomerByTel(tel);

        resopnseJson(customerByTel,new String[]{});
    }
}
