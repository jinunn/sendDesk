package com.jinunn.send;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * 类生成器
 *
 * @author : Jin
 * @date : 2022/6/15 15:58
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/send?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        FastAutoGenerator.create(
                        //数据源配置，url需要修改
                        new DataSourceConfig.Builder(url, userName, password)
                                .dbQuery(new MySqlQuery())
                                .schema("mybatis-plus")
                                .typeConvert(new MySqlTypeConvert())
                                .keyWordsHandler(new MySqlKeyWordsHandler())
                ).globalConfig(builder -> {
                    //全局配置
                    builder.author("jinunn")   // 设置作者
                            .disableOpenDir()  //禁止打开输出目录
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            //.outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                            .outputDir("D:/JavaWork/javaProjects/sendDesk/send-mapper/src/main/java"); // 指定输出目录
                }).packageConfig(builder -> {
                    //包配置
                    builder.parent("com.jinunn") // 设置父包名，根据实制项目路径修改
                            //.moduleName("sys")
                            .entity("entity")
                            //.service("service")
                            //.serviceImpl("service.impl")
                            //.mapper("mapper")
                            //.xml("mapper.xml")
                            //.controller("controller")
                            //.other("other")
                            //.pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:/JavaWork/javaProjects/sendDesk/send-mapper/src/main/resources/mapper"));
                }).strategyConfig(builder -> {
                    //策略配置
                    builder.addInclude("sms_record", "message_template") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder() //实体类配置
                            .enableLombok() //使用lombok
                            //.enableTableFieldAnnotation()//实体类字段注解
                            //.controllerBuilder()//controller配置
                            //.enableRestStyle()//开启restcontroller
                            .mapperBuilder()
                            .enableMapperAnnotation();//开启mapper注解
                    //.enableBaseResultMap()//启用 BaseResultMap 生成
                    //.enableBaseColumnList();//启用 BaseColumnList
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
