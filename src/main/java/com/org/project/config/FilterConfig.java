package com.org.project.config;

import com.org.project.filters.CorsLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsLoggingFilter> corsLoggingFilterRegistrationBean() {
        FilterRegistrationBean<CorsLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // Применяем фильтр ко всем URL
        registrationBean.setOrder(0); // Установите порядок выполнения фильтров
        return registrationBean;
    }
}
