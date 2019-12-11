package com.example.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import sun.security.krb5.Credentials;

/**
 * Created by wuhao on 2017/11/10.
 */
@Service
public class OtherService {

    String errorMsg = "";
    private Workbook xlsWorkbook;

    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 将订单内容导出为excel文件
     *
     * @param response
     * @param startTime
     * @param stopTime
     * @return
     */
    public Boolean exportToXls(HttpServletResponse response, Date startTime, Date stopTime) {

        int rowNum = 0;
        //XSSFWorkbook workbook = new XSSFWorkbook();
        //XSSFSheet sheet = workbook.createSheet("订单列表");
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("订单列表");

        //标题
        String[] fields = {
                "订单编号", "用车人", "会员卡号", "车牌号", "订单来源", "订单状态",
                "取车位置", "还车位置", "取车时间", "还车时间"
        };
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        for (String field : fields) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(field);
        }
        //内容
            row = sheet.createRow(rowNum++);
            colNum = 0;
            row.createCell(colNum++).setCellValue(1);
            row.createCell(colNum++).setCellValue(2);
            row.createCell(colNum++).setCellValue(3);
            row.createCell(colNum++).setCellValue(4);
            row.createCell(colNum++).setCellValue(5);
            row.createCell(colNum++).setCellValue(6);
            row.createCell(colNum++).setCellValue(7);
            row.createCell(colNum++).setCellValue(8);
            row.createCell(colNum++).setCellValue(9);
            row.createCell(colNum++).setCellValue(0);
        try {
            /*
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");

            //String filename=URLEncoder.encode("订单信息", "UTF-8");
            //String filename = "orderinfo";
            //response.setHeader("charset","utf-8");
            //response.setHeader("content-disposition", "attachment;filename=\"orderinfo.xlsx\"");
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Content-Disposition",
                    "attachment; filename=report.xls");
            //"attachment; filename=report.xlsx");

            //FileOutputStream fileOutputStream= new FileOutputStream("a.xlsx");
            //workbook.write(fileOutputStream);

            OutputStream outputStream = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            outputStream.write(xlsBytes);
            outputStream.close();
            //workbook=getWorkbook();
            //workbook.write(outputStream);
            //outputStream.close();

            workbook.close();
            */

            FileOutputStream fileOutputStream= new FileOutputStream("a.xlsx");
            workbook.write(fileOutputStream);

            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=testxls.xls");

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            workbook.write(outByteStream);

            byte[] outArray = outByteStream.toByteArray();
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            return true;
        } catch (Exception e) {
            errorMsg = e.getLocalizedMessage();
            return false;
        }
    }

    public Workbook getXlsWorkbook() {
        int rowNum = 0;
        HSSFWorkbook workbook=new HSSFWorkbook();
        //HSSFSheet sheet=workbook.createSheet("订单列表");
        HSSFSheet sheet=workbook.createSheet("test");

        //标题
        String[] fields = {
                "订单编号", "用车人", "会员卡号", "车牌号", "订单来源", "订单状态",
                "取车位置", "还车位置", "取车时间", "还车时间"
        };
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        /*
        for (String field : fields) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(field);
        }
        */
        //内容
        //row = sheet.createRow(rowNum++);
        colNum = 0;
        row.createCell(colNum++).setCellValue(1);
        row.createCell(colNum++).setCellValue(2);
        row.createCell(colNum++).setCellValue(3);
        row.createCell(colNum++).setCellValue(4);
        row.createCell(colNum++).setCellValue(5);
        row.createCell(colNum++).setCellValue(6);
        row.createCell(colNum++).setCellValue(7);
        row.createCell(colNum++).setCellValue(8);
        row.createCell(colNum++).setCellValue(9);
        row.createCell(colNum++).setCellValue(0);
        return workbook;
    }


    public String PostWithHeader(String url, Map<String, String> mapHeaders, String strBody, Boolean jsonMode) throws IOException {
        RequestBody body = RequestBody.create(XFORM, strBody);
        if (jsonMode) {
            body = RequestBody.create(JSON, strBody);
        }
        Headers headers = Headers.of(mapHeaders);
        //log.info("headers: " + headers.toString());
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public void deleteCredential(String jenkinsGUID) throws Exception {
        //curl -X POST "https://<user>:<APItoken>@<jenkinsurl>/credentials/store/system/domain/_/credential/<credetntial>/doDelete"
        String jenkinsUrl, userName, password;
        JenkinsHttpClient jenkinsHttpClient = new JenkinsHttpClient(new URI(jenkinsUrl), userName, password);
        JenkinsServer jenkins = new JenkinsServer(jenkinsHttpClient);
        String url = jenkinsUrl + "/credentials/store/system/domain/_/credential/"+jenkinsGUID+"/doDelete";
        //log.info("url: " + url);
        String crumb = getCUMB();
        String[] crumbDetails = crumb.split(":");

        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put(crumbDetails[0], crumbDetails[1]);
        //log.info("crumb: " + crumbDetails[0] + " , " + crumbDetails[1]);

        //user and pass
        mapHeaders.put("Authorization", Credentials.basic(userName, password));

        String resInString = PostWithHeader(url, mapHeaders, "", false);
        //log.info(resInString);
    }
}
