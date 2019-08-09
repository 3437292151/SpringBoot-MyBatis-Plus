package com.yu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.dao.McrTDictDao;
import com.yu.domain.McrTDict;
import com.yu.service.BaseMapperService;
import com.yu.service.dto.McrTDictDTO;
import com.yu.service.mapper.McrTDictMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yuchanglong
 * @Date: 2019-8-9
 * @Description:
 */
@Service
@Slf4j
public class BaseMapperServiceImpl extends ServiceImpl<McrTDictDao, McrTDictMapper, McrTDictDTO, McrTDict> implements BaseMapperService{

    public void test(String id){
        McrTDictDTO mcrTDictDTO = new McrTDictDTO();
        mcrTDictDTO.setId(id);
        List<McrTDictDTO> mcrTDict = this.list(Wrappers.query(mcrTDictDTO));
        log.info("result:{} ", mcrTDict);
    }


}
