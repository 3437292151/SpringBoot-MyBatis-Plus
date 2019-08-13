package com.yu.service.dto;



import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class McrTDictDTO implements Serializable {

    private String id;

    private String dictCode;

    private String dictName;

    private String dictDesc;

    private Integer dictStatus;

    private String defUser;

    private LocalDateTime defDt;

    private String updUser;

    private LocalDateTime updDt;

    private String defUserName;

    private String updUserName;

}
