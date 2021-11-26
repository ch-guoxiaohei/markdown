package com.guoxiaohei.markdown.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author guoyupeng
 * @since 2021/11/24
 */
@Builder
@Data
@ToString
public class ImageResult {

  private Integer success;

  private String message;

  private String url;
}
