package com.tzw.mybatis.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tzw.core.base.BasePo;
import com.tzw.core.exception.ServiceException;
import com.tzw.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MP注入处理器
 *
 * @author Lion Li
 * @date 2021/4/25
 */
@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BasePo) {
                BasePo basePo = (BasePo) metaObject.getOriginalObject();
                Date current = ObjectUtil.isNotNull(basePo.getCreateTime())
                        ? basePo.getCreateTime() : new Date();
                basePo.setCreateTime(current);
                basePo.setUpdateTime(current);
                String username = StringUtils.isNotBlank(basePo.getCreateBy())
                        ? basePo.getCreateBy() : "测试谭志伟";
                // 当前已登录 且 创建人为空 则填充
                basePo.setCreateBy(username);
                // 当前已登录 且 更新人为空 则填充
                basePo.setUpdateBy(username);
            }
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BasePo) {
                BasePo basePo = (BasePo) metaObject.getOriginalObject();
                Date current = new Date();
                // 更新时间填充(不管为不为空)
                basePo.setUpdateTime(current);
                // TODO 未完成
                String username = "测试谭志伟";
                // 当前已登录 更新人填充(不管为不为空)
                if (StringUtils.isNotBlank(username)) {
                    basePo.setUpdateBy(username);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }
}
