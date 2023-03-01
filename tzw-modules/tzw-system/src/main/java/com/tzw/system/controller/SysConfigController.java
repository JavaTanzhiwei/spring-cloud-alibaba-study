package com.tzw.system.controller;

import com.tzw.core.constant.UserConstants;
import com.tzw.core.domain.AjaxResult;
import com.tzw.mybatis.core.page.PageQuery;
import com.tzw.system.bean.po.SysConfig;
import com.tzw.system.bean.req.SysConfigReq;
import com.tzw.system.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 参数配置 控制层
 *
 * @author: tanzhiwei
 * @date: 2023/3/1  15:57
 */
@RefreshScope
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {
    private final SysConfigService configService;

    /**
     * 方法描述: 获取参数配置列表
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 14:56
     */
    @GetMapping("/queryPageConfigList")
    public AjaxResult queryPageConfigList(SysConfigReq configReq, PageQuery pageQuery) {
        return AjaxResult.success(configService.queryPageConfigList(configReq, pageQuery));
    }

    /**
     * 方法描述: 根据参数编号获取详细信息
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 15:04
     */
    @GetMapping("/{configId}")
    public AjaxResult getDetailConfig(@PathVariable String configId) {
        return AjaxResult.success(configService.getById(configId));
    }

    /**
     * 方法描述: 根据参数键名查询参数值
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 15:04
     */
    @GetMapping(value = "/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey) {
        return AjaxResult.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 方法描述: 新增参数配置
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 15:05
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return AjaxResult.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return AjaxResult.success(configService.insertConfig(config));
    }

    /**
     * 方法描述: 修改参数配置
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 15:07
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return AjaxResult.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy("TODO");
        return AjaxResult.success(configService.updateConfig(config));
    }

    /**
     * 方法描述: 删除参数配置
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 15:08
     */
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable String[] configIds) {
        configService.deleteConfigByIds(configIds);
        return AjaxResult.success();
    }

    /**
     * 方法描述: 刷新参数缓存
     * 作   者: 谭志伟
     * 时   间: 2022/10/17 15:09
     */
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return AjaxResult.success();
    }
}
