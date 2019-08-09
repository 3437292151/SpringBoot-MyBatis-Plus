package com.yu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: yuchanglong
 * @Date: 2019-8-5
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseMapperServiceTest {

    @Autowired
    private BaseMapperService baseMapperService;



    @Test
    public void test1() throws Exception {
        baseMapperService.test("25e288fa-a212-11e9-aa60-005056b5e9da");
    }

}