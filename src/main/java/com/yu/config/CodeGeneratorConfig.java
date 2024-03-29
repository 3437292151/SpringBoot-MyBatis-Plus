package com.yu.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        gc.setAuthor("yuchanglong");
        /*gc.setMapperName("Dao");
        gc.setControllerName("Resource");*/
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
        pc.setModuleName(null);
        pc.setParent("com.yu");
        pc.setEntity("domain");
        pc.setController("web.rest");
        pc.setMapper("dao");
        return pc;
    }

    @Bean
    public InjectionConfig getInjectionConfig(){
        String property = System.getProperty("user.dir");
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("superServiceMapperClass", "EntityMapper");
                map.put("superServiceMapperClassPackage","com.yu.service.mapper");
                map.put("",this.getConfig());

                this.setMap(map);
            }
        };


        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return property + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Dao" + StringPool.DOT_XML;
            }
        });
        templatePath = "/templates/myDao.java.ftl";

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
               String out = property + "/src/main/java/" +
                        getPackageConfig().getParent().replace(".","/")
                        + "/" +
                        getPackageConfig().getMapper().replace(".","/")
                        + "/"
                        + tableInfo.getEntityName() + "Dao" + StringPool.DOT_JAVA;
                System.out.println(out);
                return out;
            }
        });

        templatePath = "/templates/entityDTO.java.ftl";

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String out = property + "/src/main/java/" +
                        getPackageConfig().getParent().replace(".","/")
                        + "/"
                        + getPackageConfig().getService().replace(".","/")
                        + "/dto/"
                        + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
                System.out.println(out);
                return out;
            }
        });

        templatePath = "/templates/mymapper.java.ftl";

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String out = property + "/src/main/java/" +
                        getPackageConfig().getParent().replace(".","/")
                        + "/" +
                        getPackageConfig().getService().replace(".","/")
                        + "/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
                System.out.println(out);
                return out;
            }
        });


        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    @Bean
   public TemplateConfig getTemplateConfig(){
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setService("/templates/myservice.java");
        templateConfig.setServiceImpl("/templates/myserviceImpl.java");
        templateConfig.setMapper(null);
        templateConfig.setXml(null);
        return templateConfig;
   }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

   @Bean
   public StrategyConfig getStrategyConfig(){
       // 策略配置
       StrategyConfig strategy = new StrategyConfig();
       strategy.setNaming(NamingStrategy.underline_to_camel);
       strategy.setColumnNaming(NamingStrategy.underline_to_camel);
       strategy.setSuperEntityClass("com.yu.domain.PrimaryKey");
       strategy.setSuperServiceClass("com.yu.service.IService");
       strategy.setSuperServiceImplClass("com.yu.service.impl.ServiceImpl");
       strategy.setEntityLombokModel(true);
       strategy.setRestControllerStyle(true);
       // 公共父类
       //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
       // 写于父类中的公共字段
       strategy.setSuperEntityColumns("id","def_user", "def_user_name","upd_user","upd_user_name");
       strategy.setInclude("mcr_t_dict_item");
       strategy.setControllerMappingHyphenStyle(true);
       //strategy.setTablePrefix(pc.getModuleName() + "_");
       return  strategy;
   }


}
