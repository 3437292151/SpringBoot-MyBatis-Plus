package com.yu.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName(value = "mcr_t_dict")//指定表名
public class McrTDict extends PrimaryKey{
    private String dictCode;

    private String dictName;

    private String dictDesc;

    private Integer dictStatus;

    private String defUser;

    private String defUserName;

    private LocalDateTime defDt;

    private LocalDateTime updDt;

    private String updUser;

    private String updUserName;

}
