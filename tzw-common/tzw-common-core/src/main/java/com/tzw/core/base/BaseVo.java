package com.tzw.core.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类 Vo
 *
 * @author: tanzhiwei
 * @date: 2023/3/1  15:57
 */
@Data
public class BaseVo implements Serializable {
    private static final long serialVersionUID = -4054642125394206451L;

    /**
     * 属性描述: 主键
     * 作   者: 谭志伟
     * 时   间: 2022/9/21 10:23
     */
    private String id;
    /**
     * 属性描述: 创建者
     * 作   者: 谭志伟
     * 时   间: 2022/9/22 16:35
     */
    private String createBy;
    /**
     * 属性描述: 创建时间
     * 作   者: 谭志伟
     * 时   间: 2022/9/22 16:35
     */
    private Date createTime;
    /**
     * 属性描述: 更新者
     * 作   者: 谭志伟
     * 时   间: 2022/9/22 16:35
     */
    private String updateBy;
    /**
     * 属性描述: 更新时间
     * 作   者: 谭志伟
     * 时   间: 2022/9/22 16:35
     */
    private Date updateTime;
}
