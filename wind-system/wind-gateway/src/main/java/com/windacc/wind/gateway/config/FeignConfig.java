package com.windacc.wind.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windacc.wind.toolkit.jackson.JacksonObjectMapper;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@Configuration
public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        ObjectMapper objectMapper = new JacksonObjectMapper();
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GateWayMappingJackson2HttpMessageConverter(objectMapper));
        return () -> httpMessageConverters;
    }

    public static class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

        GateWayMappingJackson2HttpMessageConverter(ObjectMapper objectMapper){
            super(objectMapper);
            //List<MediaType> mediaTypes = new ArrayList<>();
            //mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
            //setSupportedMediaTypes(mediaTypes);
        }
    }
}
