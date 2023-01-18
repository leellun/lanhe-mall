package com.newland.lanhe.gateway.filter;

import com.newland.lanhe.model.RestResponse;
import com.sun.jdi.InvocationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 网关异常捕获
 * Date: 2023/1/17 00:35:04
 *
 * @author leell
 */
@Slf4j
public class GatewayExceptionHandler extends DefaultErrorWebExceptionHandler {

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     */
    public GatewayExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Exception ex = (Exception) request.exchange().getAttributes().get("org.springframework.boot.web.reactive.error.DefaultErrorAttributes.ERROR");
        if (ex != null) {
            if (ex instanceof InvalidTokenException) {
                InvalidTokenException e = (InvalidTokenException) ex;
                return ServerResponse.status(e.getHttpErrorCode()).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(RestResponse.error(e.getHttpErrorCode(), e.getMessage())));
            }
        }
        Map<String, Object> error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        int code = getHttpStatus(error);
        HttpStatus httpStatus = HttpStatus.valueOf(code);
        String msg = httpStatus.getReasonPhrase();
        if (httpStatus == HttpStatus.NOT_FOUND) {
            msg = "请求未找到";
        }
        return ServerResponse.status(code).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(RestResponse.error(code, msg)));
    }
}