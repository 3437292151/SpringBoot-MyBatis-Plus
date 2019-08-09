package com.yu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: yuchanglong
 * @Date: 2019-8-9
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeGeneratorSeriveTest {

    @Autowired
    private CodeGeneratorSerive codeGeneratorSerive;

    @Test
    public void getAutoGenerator() throws Exception {
        codeGeneratorSerive.getAutoGenerator();
    }

}