package com.cloud.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.cloud.common.constant.AliConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AliUtil {

    public static String parseOssImg(String img) {
        if (img == null)
            return "";
        if (img.contains("http://") || img.contains("https://"))
            return img;
        return AliConst.ossUrl + "/" + img;
    }

    public static void moveImg(String from, String to) {
        moveImg(from, to, true);
    }
    public static void moveImg(String from, String to, boolean delete) {
        if (from.equals(to))
            return;
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        list.add(map);
        moveImgList(list, delete);
    }
    public static void moveImgList(List<Map<String, String>> list) {
        moveImgList(list, true);
    }
    public static void moveImgList(List<Map<String, String>> list, boolean delete) {
        OSS ossClient = new OSSClientBuilder().build(AliConst.ossEndpoint, AliConst.ossKeyId, AliConst.ossSecret);
        for (Map<String, String> item : list) {
            if (item.get("from").equals(item.get("to")))
                continue;
            boolean exist = ossClient.doesObjectExist(AliConst.ossBucketName, item.get("from"));
            if (exist) {
                ossClient.copyObject(AliConst.ossBucketName, item.get("from"), AliConst.ossBucketName, item.get("to"));
                if (delete)
                    ossClient.deleteObject(AliConst.ossBucketName, item.get("from"));
            }
        }
        ossClient.shutdown();
    }
}
