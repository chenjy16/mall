package com.meiduimall.core.util;

import com.google.common.base.CharMatcher;

public class ExceptionUtils {


	
  /**
   * 功能描述:  将异常栈转成json
   * Author: 陈建宇
   * Date:   2017年4月25日 上午10:28:04 
   * param   @param throwable
   * return  String
   */
  public static String getFullStackTrace(Throwable throwable) {
    StackTraceElement[] stackElements = throwable.getStackTrace();
    StringBuilder sb = new StringBuilder("[");
    if (stackElements != null) {
      for (int i = 0; i < stackElements.length; i++) {
        sb.append("[");
        sb.append("\"" + stackElements[i].getClassName()).append("\",");
        sb.append("\"" + stackElements[i].getFileName()).append("\",");
        sb.append("\"" + stackElements[i].getLineNumber()).append("\",");
        sb.append("\"" + stackElements[i].getMethodName()).append("\"],");
      }
    }
    String str = CharMatcher.anyOf(",").trimTrailingFrom(sb.toString());
    return str + "]";
  }

}
