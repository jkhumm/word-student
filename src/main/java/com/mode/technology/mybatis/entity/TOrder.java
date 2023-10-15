package com.mode.technology.mybatis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_order
 * @author 
 */
@Data
public class TOrder implements Serializable {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 区域
     */
    private String zone;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}