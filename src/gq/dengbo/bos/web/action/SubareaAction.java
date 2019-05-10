package gq.dengbo.bos.web.action;

import gq.dengbo.bos.model.Region;
import gq.dengbo.bos.model.Subarea;
import gq.dengbo.bos.service.ISubareaService;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    @Override
    public String save() {
        System.out.println("数据1:" + getModel());
        System.out.println("数据2:" + getModel().getRegion());

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

        //添加插叙条件
        DetachedCriteria dc = pb.getDetachedCriteria();
        String addresskey = getModel().getAddresskey();
        if (!StringUtils.isEmpty(addresskey)){
            dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
        }
        Region region = getModel().getRegion();
        if (region!=null){
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            //region.province:a
            dc.createAlias("region","r");//创建一个别名

            if(!StringUtils.isEmpty(province)){
                dc.add(Restrictions.like("r.province","%" + province + "%"));
            }

            //region.city:b
            if(!StringUtils.isEmpty(city)){
                dc.add(Restrictions.like("r.city","%" + city + "%"));
            }

            //region.district:c
            if(!StringUtils.isEmpty(district)){
                dc.add(Restrictions.like("r.district","%" + district + "%"));
            }
        }
        subareaService.pageQuery(pb);
        // 获取数据时,把分区Subarea的Region的加载方式改成懒加载
        resopnseJson(pb, new String[]{"currentPage", "pageSize", "detachedCriteria", "subareas"});
    }

    /**
     * 导出表格
     */
    public void exportExcel() throws IOException {
        //1.创建一个xls文件【输入流】
        HSSFWorkbook workbook = new HSSFWorkbook();

        //2.创建一个sheet
        HSSFSheet sheet = workbook.createSheet("分区数据");
        //3.创建标题行
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编码");
        row.createCell(1).setCellValue("区域编码");
        row.createCell(2).setCellValue("关键字");
        row.createCell(3).setCellValue("省市区");

        //4.查询数据
        List<Subarea> list = subareaService.findAll();
        for (Subarea subarea : list) {
            row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(subarea.getId());
            row.createCell(1).setCellValue(subarea.getRegion().getId());
            row.createCell(2).setCellValue(subarea.getAddresskey());
            row.createCell(3).setCellValue(subarea.getRegion().getName());
        }


        //响应【输出流】
        HttpServletResponse response = ServletActionContext.getResponse();
        //设置响应头
        String fileName = URLEncoder.encode("分区数据.xls","utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName);

        String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
        System.out.println(contentType);
        response.setContentType(contentType);
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        os.close();
    }

    public void listJson() throws IOException {
        //未绑定过分区数据
        List<Subarea> list = subareaService.findAllWithUnbind();

        resopnseJson(list,new String[]{"decidedzone","region"});
    }



}
