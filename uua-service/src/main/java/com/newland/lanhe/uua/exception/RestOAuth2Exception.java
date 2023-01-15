package com.newland.lanhe.uua.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.newland.lanhe.model.RestResponse;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = RestOAuthExceptionJacksonSerializer.class)
public class RestOAuth2Exception extends OAuth2Exception {
    private RestResponse response;

    public RestOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public RestOAuth2Exception(String msg) {
        super(msg);
    }

    public RestOAuth2Exception(RestResponse response) {
        super(response.getMsg());
        this.response = response;
    }

    public RestResponse getResponse() {
        return response;
    }

}
