package com.example.service.excel;

import com.example.entity.Brand;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by wuhao on 2017/11/10.
 */
public class ExcelExport {
    // 第一步，创建一个webbook，对应一个Excel文件
    public HSSFWorkbook generateExcel() {
        return new HSSFWorkbook();
    }

    public HSSFWorkbook generateSheet(HSSFWorkbook wb, String sheetName, String[] fields,
                                      List<Brand> list) {

        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCell cell;
        int m = 0;
        for (String fieldName : fields) {
            cell = row.createCell(m);
            cell.setCellValue(fieldName);
            cell.setCellStyle(style);
            m++;
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Brand data = list.get(i);
            // 第五步，创建单元格，并设置值
            row.createCell(0).setCellValue(data.getBrandid());
            row.createCell(1).setCellValue(data.getBrandname());
            row.createCell(2).setCellValue(data.getBrandimg());
        }
        return wb;
    }

    public void export(HSSFWorkbook wb, HttpServletResponse response) {
        // 第六步，实现文件下载保存
        try {
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode("车辆信息", "utf-8") + ".xls");

            OutputStream outputStream = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            outputStream.write(xlsBytes);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
