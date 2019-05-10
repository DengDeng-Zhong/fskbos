package gq.dengbo.bos.web.action;

import gq.dengbo.bos.model.Decidedzone;
import gq.dengbo.bos.service.IDecidedzoneService;
import gq.dengbo.bos.web.action.base.BaseAction;
import gq.dengbo.crm.domain.Customer;
import gq.dengbo.crm.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class DecidedzoneAction extends BaseAction<Decidedzone> {


    private String[] subareaId;

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }

    @Autowired
    private IDecidedzoneService decidedzoneService;

    @Override
    public String save() {
        System.out.println("参数1:"+getModel());
        System.out.println("参数2:"+getModel().getStaff());
        System.out.println("参数3:"+ StringUtils.join(subareaId,","));
        decidedzoneService.save(getModel(),subareaId);

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
        /*
            1.接收参数,page[当前页] rows[每页显示多少条]
            2.调用service,参数里传一个pageBean
            3.返回json数据
         */
        System.out.println("111");
        pb.setCurrentPage(page);
        pb.setPageSize(rows);
        System.out.println(page+","+rows);
        decidedzoneService.pageQuery(pb);
//        System.out.println(pb);

        //返回json数据
        resopnseJson(pb,new String[]{"currentPage", "pageSize", "detachedCriteria"});

    }

    //===============未关联定区的客户信息===========

    @Autowired
    private CustomerService customerService;
    public void findnoassociationCustomers() throws IOException {
        List<Customer> customers = customerService.findnoassociationCustomers();
        resopnseJson(customers,new String[]{});
    }
}
