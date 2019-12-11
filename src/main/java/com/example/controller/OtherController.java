package com.example.controller;

import com.example.entity.RespResultVO;
import com.example.dao.BrandRepository;
import com.example.service.OtherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by wuhao on 2017/10/17.
 */

@Slf4j
@RestController
@Api(tags ="OtherController",description = "其它测试")
@RequestMapping(value = "/other", produces = MediaType.APPLICATION_JSON_VALUE)
public class OtherController {

    @Autowired
    BrandRepository brandRepository;
    @Autowired
    OtherService otherService;

    // 下载execl文档
    @PostMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        Workbook workbook = otherService.getXlsWorkbook();
        workbook.write(response.getOutputStream());
    }

    @ApiOperation(value = "根据起止时间导出订单", notes = "根据起止时间导出订单")
    @GetMapping(value = "/export")
    public void exportXls(HttpServletResponse response) {

        try {
            /*
            Workbook wb = otherService.getXlsWorkbook();
            response.setHeader("Content-disposition", "attachment; filename=test.xls");
            wb.write(response.getOutputStream());
            */

            Date startTime = new Date();
            Date stopTime = new Date();
            //生成 excel 文件
            otherService.exportToXls(response, startTime, stopTime);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @GetMapping("/crumb")
    public RespResultVO<String> testCrumb() {
        try {
            otherService.deleteCredential("identification");
            return RespResultVO.<String>builder().code(0).message("success").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespResultVO.<String>builder().code(-1).message("failed").build();
    }
}
