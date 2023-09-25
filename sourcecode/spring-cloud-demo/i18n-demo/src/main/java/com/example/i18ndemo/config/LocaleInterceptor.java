package com.example.i18ndemo.config;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String lang = request.getParameter("lang");
        Locale locale = Locale.SIMPLIFIED_CHINESE;

        if (lang != null && !lang.trim().isEmpty()) {
            String[] langParts = lang.split("_");
            if (langParts.length == 2) {
                locale = new Locale(langParts[0], langParts[1]);
            }
        }

        LocaleContextHolder.setLocale(locale);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LocaleContextHolder.resetLocaleContext();
    }
}
