package com.cloud.common.util;

public class GlobUtil {

    private static final String OSS_DOMAIN = "https://lyxclean.oss-cn-shanghai.aliyuncs.com";

    public static String parseOssImg(String img) {
        if (img == null)
            return "";
        if (img.contains("http://") || img.contains("https://"))
            return img;
        return OSS_DOMAIN + "/" + img;
    }

}
