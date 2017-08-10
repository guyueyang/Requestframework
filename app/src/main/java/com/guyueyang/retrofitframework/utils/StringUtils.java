package com.guyueyang.retrofitframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明：字符串操作相关工具类
 * 作者：yangxiaoping on 2016/3/29 09:05
 */
public class StringUtils {
    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNullEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 过滤掉空字符串以及前后截取空串
     *
     * @param str
     * @return
     */
    public static String filterNullAndTrim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 请求内容转义
     * (
     * &-----{:chr38:}
     * <p>
     * <p>
     * )
     */
    public static String escParamContent(String content) {
        if (content == null) return "";
        return content.replaceAll("\\&", "{:chr38:}");
    }

    public static String subNickName(String nickName) {
        boolean sub = nickName.length() > 15 ? true : false;
        if (sub) {
            nickName = nickName.substring(0, 15);
            StringBuilder sb = new StringBuilder(nickName);
            return sb.append("...").toString();
        } else {
            return nickName;
        }
    }

    public static String filterDoMainUrl(String str){
        String regEx="^(((http)|(https))\\:\\/\\/[a-zA-Z0-9\\.\\-\\_]+\\/)";
        Pattern p= Pattern.compile(regEx);
        Matcher matcher=p.matcher(str);
        if(matcher.find()){
            String result= matcher.group();
            return result.substring(0,result.lastIndexOf("/"));
        }
        return "";
    }
    public static boolean isMobile(String str) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号;
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
