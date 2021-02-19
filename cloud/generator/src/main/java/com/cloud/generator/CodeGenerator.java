package com.cloud.generator;

import com.cloud.generator.config.WebGeneratorConfig;
import com.cloud.generator.model.GenQo;

public class CodeGenerator {


//    private static final String dbName = "lyx_" + name;




    private static final String name = "activity";// 服务名
    private static final String dbName = "wanshua";// 数据库
    private static final String tableName = "activity_files";// 表名



    private static final String username = "root";
    private static final String password = "root";


    // 配置
    private static final String prefix = "";
    private static final String path = "/" + name + "/";
    private static final String packageName = "com.cloud." + name;

    private static final String author = "sun";
//    private static final String url = "jdbc:mysql://rm-bp18ka3x8xzx9uvs3.mysql.rds.aliyuncs.com:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
//    private static final String url = "jdbc:mysql://rm-bp10d27rlb58bmnupio.mysql.rds.aliyuncs.com:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private static final String url = "127.0.0.1:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";




    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        GenQo genQo = new GenQo();
        genQo.setAuthor(author);
        genQo.setTableName(tableName);
        genQo.setProjectPath(rootPath + path);
        genQo.setUrl(url);
        genQo.setUserName(username);
        genQo.setPassword(password);
        genQo.setDaoSwitch(true);
        genQo.setEntitySwitch(true);
        genQo.setIgnoreTabelPrefix(prefix);
        genQo.setProjectPackage(packageName);

        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(genQo);
        webGeneratorConfig.doMpGeneration();
    }
}