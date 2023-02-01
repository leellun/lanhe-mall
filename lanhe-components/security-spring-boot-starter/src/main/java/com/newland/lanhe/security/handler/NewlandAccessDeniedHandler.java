package com.newland.lanhe.security.handler;

import com.alibaba.fastjson2.JSONObject;
import com.newland.lanhe.enumeration.ResultCode;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewlandAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
					   HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException{
		response.setStatus(ResultCode.UNAUTHORIZED.getCode());
		JSONObject json=new JSONObject();
		json.put("code", ResultCode.UNAUTHORIZED.getCode());
		json.put("msg",ResultCode.UNAUTHORIZED.getDesc());
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(JSONObject.toJSONString(json));
	}

}
 

