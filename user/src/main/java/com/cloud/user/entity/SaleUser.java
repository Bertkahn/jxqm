package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author sun
 * @since 2019-07-01
 */
@Data
@TableName("sale_user")
public class SaleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    @TableId("userId")
    private Integer userId;

    @TableField("saleId")
    private Long saleId;

    @TableField("nextVisitTime")
    private Integer nextVisitTime;

}
