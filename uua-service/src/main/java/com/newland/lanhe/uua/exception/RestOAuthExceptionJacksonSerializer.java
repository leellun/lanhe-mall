package com.newland.lanhe.uua.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 异常信息序列化
 * @author leell
 */
public class RestOAuthExceptionJacksonSerializer extends StdSerializer<RestOAuth2Exception> {

    protected RestOAuthExceptionJacksonSerializer() {
        super(RestOAuth2Exception.class);
    }

    @Override
    public void serialize(RestOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        if (value.getResponse() != null) {
            jgen.writeObjectField("code",value.getResponse().getCode());
            jgen.writeObjectField("msg",value.getResponse().getMsg());
            jgen.writeObjectField("result",value.getResponse().getResult());
        }else{
            jgen.writeObjectField("code", value.getHttpErrorCode());
            jgen.writeStringField("msg", value.getMessage());
        }
        jgen.writeEndObject();
    }
}
