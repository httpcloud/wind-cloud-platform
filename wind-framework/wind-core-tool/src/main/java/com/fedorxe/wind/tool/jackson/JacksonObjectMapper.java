package com.fedorxe.wind.tool.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fedorxe.wind.tool.constants.CommonConstant;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:27
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -6172840400986781943L;

    public JacksonObjectMapper() {
        super();
        super.setLocale(Locale.CHINA);
        super.setTimeZone(TimeZone.getTimeZone(CommonConstant.TIME_ZONE_GMT8));
        super.setDateFormat(new SimpleDateFormat(CommonConstant.PATTERN_DATETIME, Locale.CHINA));
        // 允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
        super.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        super.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
        //super.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        //    @Override
        //    public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        //        arg1.writeString("");
        //    }
        //});
        super.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        super.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        super.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        super.configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);
        super.configure(MapperFeature.USE_STD_BEAN_NAMING, true);
        super.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        super.registerModule(getSerializerModule());
        super.findAndRegisterModules();
    }

    public JacksonObjectMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    private JavaTimeModule getSerializerModule() {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(CommonConstant.DATETIME_FORMAT));
        timeModule
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CommonConstant.DATETIME_FORMAT));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(CommonConstant.DATE_FORMAT));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(CommonConstant.DATE_FORMAT));
        timeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(CommonConstant.TIME_FORMAT));
        timeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(CommonConstant.TIME_FORMAT));
        return timeModule;
    }

    @Override
    public ObjectMapper copy() {
        return super.copy();
    }

}
