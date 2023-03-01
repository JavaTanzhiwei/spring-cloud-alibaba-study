package com.tzw.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzw.core.constant.UserConstants;
import com.tzw.core.exception.ServiceException;
import com.tzw.core.utils.StringUtils;
import com.tzw.mybatis.core.page.PageQuery;
import com.tzw.system.bean.po.SysConfig;
import com.tzw.system.bean.req.SysConfigReq;
import com.tzw.system.bean.vo.SysConfigVo;
import com.tzw.system.mapper.SysConfigMapper;
import com.tzw.system.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 参数配置 服务层实现
 *
 * @author: tanzhiwei
 * @date: 2023/3/1  15:57
 */
@RequiredArgsConstructor
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    /**
     * 分页查询
     *
     * @param configReq {@link SysConfigReq}
     * @param pageQuery {@link PageQuery}
     * @return 分页集合
     */
    @Override
    public IPage<SysConfigVo> queryPageConfigList(SysConfigReq configReq, PageQuery pageQuery) {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectVoPage(pageQuery.build(), queryWrapper);
    }

    /**
     * 新增参数配置
     *
     * @param config {@link SysConfig}
     * @return 新增结果
     */
    @Override
    public String insertConfig(SysConfig config) {
        int insert = baseMapper.insert(config);
        if (insert > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败");
    }

    /**
     * 修改参数配置
     *
     * @param config {@link SysConfig}
     * @return 修改结果
     */
    @Override
    public String updateConfig(SysConfig config) {
        int row;
        if (config.getId() != null) {
            row = baseMapper.updateById(config);
        } else {
            row = baseMapper.update(config, new LambdaQueryWrapper<SysConfig>()
                    .eq(SysConfig::getConfigKey, config.getConfigKey()));
        }
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败");
    }

    /**
     * 批量删除参数信息
     *
     * @param configIds 主键ID集合
     */
    @Override
    public void deleteConfigByIds(String[] configIds) {
        baseMapper.deleteBatchIds(Arrays.asList(configIds));
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 键名
     * @return 参数配置信息
     */
    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig retConfig = baseMapper.selectOne(new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getConfigKey, configKey));
        if (ObjectUtil.isNotNull(retConfig)) {
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
    }


    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config {@link SysConfig}
     * @return 校验结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        String configId = config.getId();
        // 校验是否唯一Key
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(config.getConfigKey()), SysConfig::getConfigKey, config.getConfigKey());
        SysConfig sysConfig = baseMapper.selectOne(wrapper);
        if (ObjectUtil.isNotNull(sysConfig) && !Objects.equals(sysConfig.getId(), configId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
