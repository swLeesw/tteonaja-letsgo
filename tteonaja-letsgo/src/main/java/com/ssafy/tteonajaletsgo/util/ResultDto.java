package com.ssafy.tteonajaletsgo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResultDto<T> {
	private HttpStatus statusCode;
	private String resultMsg;
	private T resultData;
	private String description;
	
	public ResultDto(final HttpStatus statusCode, final String resultMsg) {
		this.statusCode = statusCode;
		this.resultMsg = resultMsg;
		this.resultData = null;
		this.description = "";
	}
	
	public static<T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg) {
		return res(statusCode, resultMsg, null, "");
	}
	
	public static<T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg, final T t, final String description){
		return ResultDto.<T>builder()
				.resultData(t)
				.statusCode(statusCode)
				.resultMsg(resultMsg)
				.description(description)
				.build();
	}
}
