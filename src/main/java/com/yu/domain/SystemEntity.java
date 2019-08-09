package com.yu.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Auther: yuchanglong
 * @Date: 2018-12-3
 * @Description: 系统实体
 */
@Data
public class SystemEntity {

    private String id;

    private String defUser;

    private String defUserName;

    private LocalDateTime defDt;

    private String updUser;

    private String updUserName;

    private LocalDateTime updDt;

    private String sdept;

    private String scmpy;

    public SystemEntity(String id, String defUser, String defUserName, LocalDateTime defDt, String updUser, String updUserName, LocalDateTime updDt, String sdept, String scmpy) {
        this.id = id;
        this.defUser = defUser;
        this.defUserName = defUserName;
        this.defDt = defDt;
        this.updUser = updUser;
        this.updUserName = updUserName;
        this.updDt = updDt;
        this.sdept = sdept;
        this.scmpy = scmpy;
    }

    public SystemEntity() {
        super();
    }

    public SystemEntity(String id) {
        super();
        this.id = id;
    }

    public SystemEntity setUUID(){
        this.setId(UUID.randomUUID().toString());
        return this;
    }

    public SystemEntity setTime(){
        this.setDefDt(LocalDateTime.now());
        this.setUpdDt(LocalDateTime.now());
        return this;
    }
}
