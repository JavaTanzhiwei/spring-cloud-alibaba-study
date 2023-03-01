package com.tzw.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tzw.mybatis.core.page.PageQuery;
import com.tzw.system.bean.po.SysConfig;
import com.tzw.system.bean.req.SysConfigReq;
import com.tzw.system.bean.vo.SysConfigVo;

/**
 * 参数配置 服务层
 *
 * @author: tanzhiwei
 * @date: 2023/3/1  15:57
 */
public interface SysConfigService extends IService<SysConfig> {
    /**
     * 分页查询
     *
     * @param configReq {@link SysConfigReq}
     * @param pageQuery {@link PageQuery}
     * @return 分页集合
     */
    IPage<SysConfigVo> queryPageConfigList(SysConfigReq configReq, PageQuery pageQuery);

    /**
     * 新增参数配置
     *
     * @param config {@link SysConfig}
     * @return 新增结果
     */
    String insertConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config {@link SysConfig}
     * @return 修改结果
     */
    String updateConfig(SysConfig config);

    /**
     * 批量删除参数信息
     *
     * @param configIds 主键ID集合
     */
    void deleteConfigByIds(String[] configIds);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 键名
     * @return 参数配置信息
     */
    String selectConfigByKey(String configKey);

    /**
     * 加载参数缓存数据
     */
    void loadingConfigCache();

    /**
     * 清空参数缓存数据
     */
    void clearConfigCache();

    /**
     * 重置参数缓存数据
     */
    void resetConfigCache();

    /**
     * 校验参数键名是否唯一
     *
     * @param config {@link SysConfig}
     * @return 校验结果
     */
    String checkConfigKeyUnique(SysConfig config);
}
