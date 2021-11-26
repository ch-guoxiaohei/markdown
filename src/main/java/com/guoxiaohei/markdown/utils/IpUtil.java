package com.guoxiaohei.markdown.utils;


import java.net.InetAddress;
import java.net.UnknownHostException;

public final class IpUtil {

  private IpUtil() {
  }

  public static String getIp() throws UnknownHostException {
    InetAddress addr = InetAddress.getLocalHost();
    return addr.getHostName();
  }

}
