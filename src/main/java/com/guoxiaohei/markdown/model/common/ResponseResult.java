package com.guoxiaohei.markdown.model.common;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ResponseResult<T> {

  private int code;

  private String message;

  private T data;

}
