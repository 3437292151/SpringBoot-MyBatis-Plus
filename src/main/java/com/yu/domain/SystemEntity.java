package com.yu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Auther: yuchanglong
 * @Date: 2018-12-3
 * @Description: 系统实体
 */
@Data
public class SystemEntity extends PrimaryKey {

    private String defUser;

    private String defUserName;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:SS")
    private LocalDateTime defDt;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:SS")
    private LocalDateTime updDt;

    private String updUser;

    private String updUserName;

    @TableField("s_dept")
    private String sdept;

    @TableField("s_cmpy")
    private String scmpy;

    public SystemEntity(String id, String defUser, String defUserName, LocalDateTime defDt, String updUser, String updUserName, LocalDateTime updDt, String sdept, String scmpy) {
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
        super.setId(id);
    }

}
