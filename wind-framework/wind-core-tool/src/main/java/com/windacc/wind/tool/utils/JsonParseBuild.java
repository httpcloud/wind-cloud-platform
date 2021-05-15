package com.windacc.wind.tool.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windacc.wind.tool.jackson.JacksonObjectMapper;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:27
 */
public class JsonParseBuild {

    public static Builder builder(String jsonStr) throws JsonProcessingException {
        return new Builder(jsonStr);
    }

    private static ObjectMapper getObjectMapper() {
        return new JacksonObjectMapper();
    }

    public static class Builder {
        private JsonNode jsonNode;

        private Builder(String jsonStr) throws JsonProcessingException {
            ObjectMapper mapper = getObjectMapper();
            jsonNode = mapper.readTree(jsonStr);
        }

        public Builder get(String key) {
            if (jsonNode != null) {
                jsonNode = jsonNode.findValue(key);
            }
            return this;
        }

        public String asString(String defaultValue) {
            if (jsonNode == null) {
                return defaultValue;
            }
            if (jsonNode.isTextual()) {
                return jsonNode.asText(defaultValue);
            } else {
                return defaultValue;
            }
        }


    }

}
