package com.yu.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Auther: yuchanglong
 * @Date: 2019-3-29
 * @Description: 主键实体（主要用于接口显示参数）
 */
@Data
public class PrimaryKey {

    @TableId
    private String id;

    private LocalDateTime defDt;

    private LocalDateTime updDt;

    public PrimaryKey setUUID(){
        this.setId(UUID.randomUUID().toString());
        return this;
    }

    public PrimaryKey setTime(){
        this.setDefDt(LocalDateTime.now());
        this.setUpdDt(LocalDateTime.now());
        return this;
    }
}
