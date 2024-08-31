package com.github.dts.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;


public class CodeUtils {

    private static final String PARENT_DIR = "./";
    /**
     * 基本路径
     */
    private static final String SRC_MAIN_JAVA = "/src/main/java/";
    /**
     * xml路径
     */
    private static final String XML_PATH = PARENT_DIR + "dts-web/src/main/resources/mapper";
    /**
     * entity路径
     */
    private static final String ENTITY_PATH = PARENT_DIR + "dts-dao/src/main/java/com/github/dts/domain";
    /**
     * mapper路径
     */
    private static final String MAPPER_PATH = PARENT_DIR + "dts-dao/src/main/java/com/github/dts/mapper";
    /**
     * service路径
     */
    private static final String SERVICE_PATH = PARENT_DIR + "dts-service/src/main/java/com/github/dts/service/dts";
    /**
     * serviceImpl路径
     */
    private static final String SERVICE_IMPL_PATH = PARENT_DIR + "dts-service/src/main/java/com/github/dts/service/dts/impl";
    /**
     * controller路径
     */
    private static final String CONTROLLER_PATH = PARENT_DIR + "dts-web/src/main/java/com/github/dts/controller";
    /**
     * 数据库url
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dts?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull";
    /**
     * 数据库用户名
     */
    private static final String USERNAME = "root";
    /**
     * 数据库密码
     */
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        codeGenerator();
    }

    private static void codeGenerator() {
        FastAutoGenerator.create(DB_URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("dts")
                        .outputDir("./dts-core/src/main/java/com/github/dts")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("")
                        .entity("com.github.dts.domain")
                        .mapper("com.github.dts.mapper")
                        .service("com.github.dts.service.dts")
                        .serviceImpl("com.github.dts.service.dts.impl")
                        .controller("com.github.dts.controller")
                        .pathInfo(getPathInfo())
                )
                .strategyConfig(builder -> builder
                        .addInclude("task_info") // 设置需要生成的表名
                        .entityBuilder()
                        .enableFileOverride()
                        .enableRemoveIsPrefix()
                        .enableLombok() // 启用 Lombok
                        .enableTableFieldAnnotation() // 启用字段注解
                        .idType(IdType.AUTO)
                        // controller
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController")
//                        .enableFileOverride()
                        // service
                        .serviceBuilder()
//                        .enableFileOverride()
                        .superServiceClass(IService.class)
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        // mapper
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .superClass(BaseMapper.class)
                        .enableMapperAnnotation()
                )
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

    private static Map<OutputFile, String> getPathInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH);
        pathInfo.put(OutputFile.mapper, MAPPER_PATH);
        pathInfo.put(OutputFile.service, SERVICE_PATH);
        pathInfo.put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
        pathInfo.put(OutputFile.controller, CONTROLLER_PATH);
        pathInfo.put(OutputFile.xml, XML_PATH);
        return pathInfo;
    }
}
