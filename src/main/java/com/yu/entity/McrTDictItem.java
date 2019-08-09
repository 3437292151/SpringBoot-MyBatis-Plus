package com.yu.entity;

import com.yu.domain.SystemEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * MCR_T_DICT_ITEM表
 * </p>
 *
 * @author jobob
 * @since 2019-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class McrTDictItem extends SystemEntity {

    private static final long serialVersionUID = 1L;

    private String pid;

    private String itemCode;

    private String itemName;

    private String itemDesc;

    private Integer itemStatus;

    private Integer sortNumber;

    private String defUser;

    private LocalDateTime defDt;

    private String updUser;

    private LocalDateTime updDt;

    private String defUserName;

    private String updUserName;


}
