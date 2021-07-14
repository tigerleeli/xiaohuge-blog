package com.llh.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.llh.excel.bean.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author llh
 */
@RestController
@Slf4j
public class TestController {
    private static final String TEMP_DIR = "temp/";

    @PostMapping("upload")
    public void upload(@RequestParam(name = "file", required = false) MultipartFile multipartFile, HttpServletResponse response) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            // 读excel
            EasyExcel.read(inputStream, DemoData.class, new DemoDataListener())
                    .headRowNumber(1).sheet().doRead();

            // 写excel
            File tempDir = new File(TEMP_DIR);
            if (!tempDir.exists()) {
                boolean res = tempDir.mkdir();
                log.info("创建文件目录");
            }

            log.info("生成excel表格并返回");
            List<DemoData> dataList = new ArrayList<>();
            dataList.add(new DemoData("李四", 20, new Date(), 1000.6d));
            dataList.add(new DemoData("李四", 20, new Date(), 1000.6d));
            dataList.add(new DemoData("李四", 20, new Date(), 1000.6d));
            dataList.add(new DemoData("李四", 20, new Date(), 1000.6d));
            String fileName = URLEncoder.encode("导出数据", "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("sheet1").doWrite(dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
