package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiTypeMapper;
import com.ruoyi.kpi.domain.KpiType;
import com.ruoyi.kpi.service.IKpiTypeService;

/**
 * kpi分类Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
@Service
public class KpiTypeServiceImpl implements IKpiTypeService 
{
    @Autowired
    private KpiTypeMapper kpiTypeMapper;

    /**
     * 查询kpi分类
     * 
     * @param typeId kpi分类主键
     * @return kpi分类
     */
    @Override
    public KpiType selectKpiTypeByTypeId(Long typeId)
    {
        return kpiTypeMapper.selectKpiTypeByTypeId(typeId);
    }

    /**
     * 查询kpi分类列表
     * 
     * @param kpiType kpi分类
     * @return kpi分类
     */
    @Override
    public List<KpiType> selectKpiTypeList(KpiType kpiType)
    {
        return kpiTypeMapper.selectKpiTypeList(kpiType);
    }

    /**
     * 新增kpi分类
     * 
     * @param kpiType kpi分类
     * @return 结果
     */
    @Override
    public int insertKpiType(KpiType kpiType)
    {
        kpiType.setCreateTime(DateUtils.getNowDate());
        return kpiTypeMapper.insertKpiType(kpiType);
    }

    /**
     * 修改kpi分类
     * 
     * @param kpiType kpi分类
     * @return 结果
     */
    @Override
    public int updateKpiType(KpiType kpiType)
    {
        kpiType.setUpdateTime(DateUtils.getNowDate());
        return kpiTypeMapper.updateKpiType(kpiType);
    }

    /**
     * 批量删除kpi分类
     * 
     * @param typeIds 需要删除的kpi分类主键
     * @return 结果
     */
    @Override
    public int deleteKpiTypeByTypeIds(Long[] typeIds)
    {
        return kpiTypeMapper.deleteKpiTypeByTypeIds(typeIds);
    }

    /**
     * 删除kpi分类信息
     * 
     * @param typeId kpi分类主键
     * @return 结果
     */
    @Override
    public int deleteKpiTypeByTypeId(Long typeId)
    {
        return kpiTypeMapper.deleteKpiTypeByTypeId(typeId);
    }
}
