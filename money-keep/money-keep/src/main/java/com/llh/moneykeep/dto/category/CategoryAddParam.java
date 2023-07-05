package com.llh.moneykeep.dto.category;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryAddParam {
    @NotBlank(message = "请输入名称")
    @Size(max = 50, message = "名称长度不能超过50")
    private String name;

    /**
     * 类型，1：支出，2：收入
     */
    @NotNull(message = "请选择类型")
    @Range(min = 1, max = 2, message = "类型选择错误")
    private Integer type;
}
