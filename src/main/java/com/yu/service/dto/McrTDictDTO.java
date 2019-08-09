package com.yu.service.dto;



import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class McrTDictDTO implements Serializable {

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

}
