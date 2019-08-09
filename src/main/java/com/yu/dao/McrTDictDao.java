package com.yu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.domain.McrTDict;
import com.yu.service.dto.McrTDictDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface McrTDictDao extends BaseMapper<McrTDict>{
    int deleteByPrimaryKey(String id);

    int insertSelective(McrTDict record);

    McrTDict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(McrTDict record);

    int updateByPrimaryKey(McrTDict record);


    //新建数据字典
    int insert(List<McrTDict> mcrTDicts);
    //批量删除
    int batchDelete(List<McrTDict> idList);
    //批量更新
    int updateBatch(List<McrTDict> mcrTDicts);
    //查询全部
    List<McrTDict> selectAll();
    //根据条件查询
    List<McrTDict> selectByCols(McrTDict mcrTDict);
    //根据ID查询
    List<McrTDict> selectById(List<McrTDict> mcrTDicts);
}
