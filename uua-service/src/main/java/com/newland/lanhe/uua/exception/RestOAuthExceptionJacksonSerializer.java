package com.newland.lanhe.uua.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

public class RestOAuthExceptionJacksonSerializer extends StdSerializer<RestOAuth2Exception> {

    protected RestOAuthExceptionJacksonSerializer() {
        super(RestOAuth2Exception.class);
    }

    @Override
    public void serialize(RestOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("code", value.getHttpErrorCode());
        jgen.writeStringField("msg", value.getMessage());
        String summary = value.getSummary();
        jgen.writeStringField("result", summary);

        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }
}
