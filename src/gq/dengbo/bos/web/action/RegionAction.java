package gq.dengbo.bos.web.action;

import com.sun.istack.internal.logging.Logger;
import gq.dengbo.bos.model.PageBean;
import gq.dengbo.bos.model.Region;
import gq.dengbo.bos.model.Staff;
import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IRegionService;
import gq.dengbo.bos.service.IUserService;
import gq.dengbo.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegionAction extends BaseAction<Region>{
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

    @Autowired
    private IRegionService regionService;

    //==================上传回的文件==========
    private File excelFile;

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }

    public String importExcel() throws IOException {
        System.out.println(excelFile.getAbsolutePath());

        HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(excelFile));

        HSSFSheet sheet = wk.getSheetAt(0);

        //遍历表格中的数据
        List<Region> regions = new ArrayList<Region>();
        for (Row row : sheet) {
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            //封装成Region对象
            Region region = new Region(id, province, city, district, postcode);
            regions.add(region);

        }
        regions.remove(0);
        //调用service
        regionService.saveAll(regions);

        return SUCCESS;
    }

    //=============分页查询返回json数据=========
    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void pageQuery() throws IOException {
        /*
            1.接收参数,page[当前页] rows[每页显示多少条]
            2.调用service,参数里传一个pageBean
            3.返回json数据
         */
        PageBean<Region> pb = new PageBean<Region>();
        pb.setCurrentPage(page);
        pb.setPageSize(rows);
        DetachedCriteria dc = DetachedCriteria.forClass(Region.class);
        pb.setDetachedCriteria(dc);

        regionService.pageQuery(pb);

        System.out.println(pb);

        //返回json数据

        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"currentPage", "pageSize", "detachedCriteria"});

        JSONObject jsonObject = JSONObject.fromObject(pb, config);
        jsonObject.toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("content-type", "text/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }
}
