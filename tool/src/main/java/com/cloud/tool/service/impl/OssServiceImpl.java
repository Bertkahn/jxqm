package com.cloud.tool.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.cloud.common.constant.AliConst;
import com.cloud.common.util.TimeUtil;
import com.cloud.tool.service.OssService;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Service
public class OssServiceImpl implements OssService {

    @Override
    public Map<String, String> getUploadParam() {
        OSS ossClient = new OSSClientBuilder().build(AliConst.ossEndpoint, AliConst.ossKeyId, AliConst.ossSecret);
        Integer dayEnd = TimeUtil.getTodayEndTimeStamp();
        Date expiration = new Date(dayEnd.longValue() * 1000);
        String dir = "temp/" + TimeUtil.formatTime("YMD") + "/";//设置用户上传指定的前缀，必须以斜线结尾
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);//上传目录
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 10 * 1024 * 1024);// 10M
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = new byte[0];
        try {
            binaryData = postPolicy.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String policy = BinaryUtil.toBase64String(binaryData);
        String signature = ossClient.calculatePostSignature(postPolicy);
        Map<String, String> result = new HashMap<>();
        result.put("url", AliConst.ossUrl);
        result.put("accessKeyId", AliConst.ossKeyId);
        result.put("policy", policy);
        result.put("signature", signature);
        result.put("expire", dayEnd.toString());
        return result;
    }
}
