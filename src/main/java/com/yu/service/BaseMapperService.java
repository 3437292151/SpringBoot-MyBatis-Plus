package com.yu.service;


import com.yu.domain.McrTDict;
import com.yu.service.dto.McrTDictDTO;
import org.springframework.stereotype.Service;

/**
 * @Auther: yuchanglong
 * @Date: 2019-8-5
 * @Description:
 */
public interface BaseMapperService extends IService<McrTDictDTO, McrTDict> {

    void test(String id);
}
