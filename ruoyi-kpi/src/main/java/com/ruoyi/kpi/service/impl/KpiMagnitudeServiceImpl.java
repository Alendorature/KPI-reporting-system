package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiMagnitudeMapper;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.service.IKpiMagnitudeService;

/**
 * KPI量值标准Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
@Service
public class KpiMagnitudeServiceImpl implements IKpiMagnitudeService 
{
    @Autowired
    private KpiMagnitudeMapper kpiMagnitudeMapper;

    /**
     * 查询KPI量值标准
     * 
     * @param magnitudeId KPI量值标准主键
     * @return KPI量值标准
     */
    @Override
    public KpiMagnitude selectKpiMagnitudeByMagnitudeId(Long magnitudeId)
    {
        return kpiMagnitudeMapper.selectKpiMagnitudeByMagnitudeId(magnitudeId);
    }

    /**
     * 查询KPI量值标准列表
     * 
     * @param kpiMagnitude KPI量值标准
     * @return KPI量值标准
     */
    @Override
    public List<KpiMagnitude> selectKpiMagnitudeList(KpiMagnitude kpiMagnitude)
    {
        return kpiMagnitudeMapper.selectKpiMagnitudeList(kpiMagnitude);
    }

    /**
     * 新增KPI量值标准
     * 
     * @param kpiMagnitude KPI量值标准
     * @return 结果
     */
    @Override
    public int insertKpiMagnitude(KpiMagnitude kpiMagnitude)
    {
        kpiMagnitude.setCreateTime(DateUtils.getNowDate());
        return kpiMagnitudeMapper.insertKpiMagnitude(kpiMagnitude);
    }

    /**
     * 修改KPI量值标准
     * 
     * @param kpiMagnitude KPI量值标准
     * @return 结果
     */
    @Override
    public int updateKpiMagnitude(KpiMagnitude kpiMagnitude)
    {
        kpiMagnitude.setUpdateTime(DateUtils.getNowDate());
        return kpiMagnitudeMapper.updateKpiMagnitude(kpiMagnitude);
    }

    /**
     * 批量删除KPI量值标准
     * 
     * @param magnitudeIds 需要删除的KPI量值标准主键
     * @return 结果
     */
    @Override
    public int deleteKpiMagnitudeByMagnitudeIds(Long[] magnitudeIds)
    {
        return kpiMagnitudeMapper.deleteKpiMagnitudeByMagnitudeIds(magnitudeIds);
    }

    /**
     * 删除KPI量值标准信息
     * 
     * @param magnitudeId KPI量值标准主键
     * @return 结果
     */
    @Override
    public int deleteKpiMagnitudeByMagnitudeId(Long magnitudeId)
    {
        return kpiMagnitudeMapper.deleteKpiMagnitudeByMagnitudeId(magnitudeId);
    }
}
