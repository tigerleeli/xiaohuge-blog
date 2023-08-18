package com.llh.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.util.Date;

/**
 * @author llh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@HeadRowHeight(40)
@ContentRowHeight(30)
public class DemoData {
    @ExcelProperty("姓名")
    @ColumnWidth(10)
    private String name;

    @ExcelProperty("年龄")
    @ColumnWidth(6)
    private Integer age;

    @ExcelProperty("生日")
    @ColumnWidth(24)
    private Date birthday;

    @ExcelProperty("余额")
    @ColumnWidth(10)
    private Double money;
}
