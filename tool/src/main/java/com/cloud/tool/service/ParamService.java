package com.cloud.tool.service;

import java.util.Map;

public interface ParamService {
    String encodeParam (Map param);

    Map decodeParam (String token);
}
