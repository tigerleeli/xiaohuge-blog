package com.llh.shardingjdbc.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 配置
 *
 * @author llh
 * @date 2021-03-23 下午8:17
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    /**
     * 序列化配置
     * Long -> String
     * LocalDateTime -> yyyy-MM-dd HH:mm:ss
     * LocalDate -> yyyy-MM-dd
     *
     * @author llh
     * @since 2021/3/22 下午11:06
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(simpleModule);

        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(0, jackson2HttpMessageConverter);
    }

    /**
     * localDateTime序列化
     *
     * @author llh
     * @since 2020/12/16 12:54
     */
    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (localDateTime != null) {
                String timeStr = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_PATTERN);
                jsonGenerator.writeString(timeStr);
            }
        }
    }

    /**
     * localDateTime反序列化
     *
     * @author llh
     * @since 2020/12/16 12:54
     */
    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return LocalDateTimeUtil.parse(jsonParser.getValueAsString());
        }
    }

    /**
     * localDate序列化
     *
     * @author llh
     * @since 2020/12/16 12:54
     */
    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (localDate != null) {
                String timeStr = LocalDateTimeUtil.format(localDate, DatePattern.NORM_DATE_FORMATTER);
                jsonGenerator.writeString(timeStr);
            }
        }
    }

    /**
     * localDate反序列化
     *
     * @author llh
     * @since 2020/12/16 12:54
     */
    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return LocalDateTimeUtil.parseDate(jsonParser.getValueAsString());
        }
    }
}
