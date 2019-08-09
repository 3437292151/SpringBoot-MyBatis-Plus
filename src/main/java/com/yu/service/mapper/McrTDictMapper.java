package com.yu.service.mapper;


import com.yu.domain.McrTDict;
import com.yu.service.dto.McrTDictDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface McrTDictMapper extends EntityMapper<McrTDictDTO, McrTDict> {

    default McrTDict fromId(String id) {
        if (id == null) {
            return null;
        }
        McrTDict mcrTDict = new McrTDict();
        mcrTDict.setId(id);
        return mcrTDict;
    }
}
