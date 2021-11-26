package com.guoxiaohei.markdown.utils;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UuIdUtil {

  private UuIdUtil() {
  }

  private final Logger log = LoggerFactory.getLogger(UuIdUtil.class);

  public static String uuid(){
    return UUID.randomUUID().toString().replace("-", "");
  }

}
