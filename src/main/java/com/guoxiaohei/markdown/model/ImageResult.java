package com.guoxiaohei.markdown.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author guoyupeng
 * @since 2021/11/24
 */
public class ImageResult {

    public ImageResult(Integer success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public ImageResult() {
    }

    private Integer success;

    private String message;

    private String url;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
