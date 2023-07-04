package com.llh.moneykeep.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterParam {
    @NotBlank(message = "请输入用户名")
    private String username;

    @NotBlank(message = "请输入密码")
    private String password;
}
