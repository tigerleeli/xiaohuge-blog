package com.example.i18ndemo.controller;

import com.example.i18ndemo.config.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("i18n")
public class I18nController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("success")
    public String success() {
        return messageSource.getMessage("opr_success", null, Locale.SIMPLIFIED_CHINESE);
    }

    @GetMapping("fail")
    public String fail() {
        return messageSource.getMessage("opr_fail", null, Locale.US);
    }

    @GetMapping("welcome")
    public String welcome(@RequestParam String name) {
        return I18nUtils.getMessage("msg_welcome", new String[]{name});
    }

}
