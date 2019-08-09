package com.yu.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName(value = "mcr_t_dict")//指定表名
public class McrTDict {
    private String id;

    private String dictCode;

    private String dictName;

    private String dictDesc;

    private Integer dictStatus;

    private String defUser;

    private LocalDate defDt;

    private String updUser;

    private LocalDate updDt;

    private String defUserName;

    private String updUserName;

    /*public McrTDict(String id, String dictCode, String dictName, String dictDesc, Integer dictStatus, String defUser, LocalDate defDt, String updUser, LocalDate updDt, String defUserName, String updUserName) {
        this.id = id;
        this.dictCode = dictCode;
        this.dictName = dictName;
        this.dictDesc = dictDesc;
        this.dictStatus = dictStatus;
        this.defUser = defUser;
        this.defDt = defDt;
        this.updUser = updUser;
        this.updDt = updDt;
        this.defUserName = defUserName;
        this.updUserName = updUserName;
    }*/

}
