package gq.dengbo.bos.web.action;

import gq.dengbo.bos.model.Region;
import gq.dengbo.bos.service.IRegionService;
import gq.dengbo.bos.utils.PinYin4jUtils;
import gq.dengbo.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
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

            //根据中文生成城市编码
            String citycode = StringUtils.join(PinYin4jUtils.stringToPinyin(city), "");

            //根据中文生成简码
            String cityTmp = city.substring(0, city.length() - 1);
            String districtTmp = district.substring(0, district.length() - 1);
            String[] cityStrs = PinYin4jUtils.getHeadByString(cityTmp);
            String[] districtStrs = PinYin4jUtils.getHeadByString(districtTmp);
            String shortcode = StringUtils.join(cityStrs, "") + StringUtils.join(districtStrs, "");


            //封装成Region对象
            Region region = new Region(id, province, city, district, postcode);
            region.setCitycode(citycode);
            region.setShortcode(shortcode);
            regions.add(region);

        }
        regions.remove(0);
        //调用service
        regionService.saveAll(regions);

        resopnseStr("success");
        return NONE;
    }


    public void pageQuery() throws IOException {
        /*
            1.接收参数,page[当前页] rows[每页显示多少条]
            2.调用service,参数里传一个pageBean
            3.返回json数据
         */
        pb.setCurrentPage(page);
        pb.setPageSize(rows);
        regionService.pageQuery(pb);
//        System.out.println(pb);

        //返回json数据
        resopnseJson(pb,new String[]{"currentPage", "pageSize", "detachedCriteria"});

    }

    public void listJson() throws IOException {
        /*
            1.查询所有区域
            2.返回json数据
         */
        List<Region> regions = regionService.findAll();
        resopnseJson(regions,new String[]{});
    }
}
