package com.yu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisplusApplicationTests {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Autowired
	private ApplicationContext appContext;

	@Before
	public void setUp(){
		appContext.getAutowireCapableBeanFactory();
	}


	@Test
	public void contextLoads() {
		// 代码生成器
		/*AutoGenerator mpg = new AutoGenerator();
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("jobob");
		gc.setOpen(false);
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		mpg.setGlobalConfig(gc);*/
		/*DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDriverName(dataSourceConfig.getDriverName());
		dataSourceConfig.setUrl(dataSourceConfig.getUrl());
		dataSourceConfig.setPassword(dataSourceConfig.getPassword());*/
		System.out.printf("成功");
	}

}
