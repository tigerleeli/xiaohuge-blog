package com.llh.moneykeep.dto.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AccountAddParam {
    @NotBlank(message = "请输入名称")
    @Size(max = 50, message = "名称长度不能超过50")
    private String name;
}
