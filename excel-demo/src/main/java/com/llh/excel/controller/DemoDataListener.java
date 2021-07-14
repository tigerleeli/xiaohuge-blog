package com.llh.excel.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.llh.excel.bean.DemoData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author llh
 */
@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();
    private Callback callback;

    public DemoDataListener(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data.toString());
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.info("解析失败，但是继续解析下一行:{}", exception.getMessage());
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.info("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
            callback.onError(excelDataConvertException.getRowIndex(), excelDataConvertException.getColumnIndex());
        }
    }

    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
    }

    public interface Callback {
        /**
         * 失败的回调
         *
         * @param errorRow    错误行
         * @param errorColumn 错误列
         */
        void onError(int errorRow, int errorColumn);
    }
}
