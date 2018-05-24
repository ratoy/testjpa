package com.example.service.excel;

import com.example.entity.Brand;
import com.example.entity.RespResultVO;
import com.example.dao.BrandRepository;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 2017/11/10.
 */
@Service
@Slf4j
public class ExportInfoService {

    private final static String EXPORT_FAIL = "车辆信息导出失败";
    private final static String VEHICLE_INFO_EXPORT_SUCCESS = "车辆信息导出成功";

    @Autowired
    BrandRepository brandRepository;

    public void exportExcel(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            //读取数据库全部车辆信息
            List<Brand> brandList = new ArrayList<>();
            brandRepository.findAll().forEach(brandList::add);

            String[] fields = {"id", "name", "img"};
            ExcelExport excelExport = new ExcelExport();
            HSSFWorkbook wb = excelExport.generateExcel();
            wb = excelExport.generateSheet(wb, "orderInfo", fields, brandList);
            excelExport.export(wb, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public RespResultVO createExcelForRepair(HttpServletResponse response ) throws Exception {
        try {
            // 创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("维修表单");
            sheet.setDefaultRowHeightInPoints(20);// 设置缺省列高
            // 实现文件下载保存
            exportForRepair(wb, response);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return RespResultVO.builder().message("空模板下载成功")
                .code(1).build();
    }

    public void exportForRepair(HSSFWorkbook wb, HttpServletResponse response) {
        try {
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode("DMS_维修表单--空模板","utf-8") + ".xls");

            @Cleanup OutputStream outputStream = response.getOutputStream();
            @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            outputStream.write(xlsBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
