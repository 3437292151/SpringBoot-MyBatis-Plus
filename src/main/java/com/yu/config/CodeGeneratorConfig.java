package com.yu.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Auther: yuchanglong
 * @Date: 2019-8-9
 * @Description:
 */
@Component
public class CodeGeneratorConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public GlobalConfig getGlobalConfig(){
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("jobob");
        gc.setOpen(false);
        return gc;
    }

    @Bean
    public DataSourceConfig getDataSourceConfig(){
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceProperties.getUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dataSourceProperties.getUsername());
        dsc.setPassword(dataSourceProperties.getPassword());
        return dsc;
    }

    @Bean
    public PackageConfig getPackageConfig(){
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("com.yu");
        return pc;
    }

    @Bean
   public TemplateConfig getTemplateConfig(){
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        return templateConfig;
   }

   @Bean
   public StrategyConfig getStrategyConfig(){
       // 策略配置
       StrategyConfig strategy = new StrategyConfig();
       strategy.setNaming(NamingStrategy.underline_to_camel);
       strategy.setColumnNaming(NamingStrategy.underline_to_camel);
       strategy.setSuperEntityClass("com.yu.domain.SystemEntity");
       strategy.setEntityLombokModel(true);
       strategy.setRestControllerStyle(true);
       // 公共父类
       //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
       // 写于父类中的公共字段
       strategy.setSuperEntityColumns("id");
       strategy.setInclude("mcr_t_dict_item");
       strategy.setControllerMappingHyphenStyle(true);
       //strategy.setTablePrefix(pc.getModuleName() + "_");
       return  strategy;
   }


}
