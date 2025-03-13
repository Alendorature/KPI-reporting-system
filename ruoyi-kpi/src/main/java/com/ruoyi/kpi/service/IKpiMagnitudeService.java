package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiMagnitude;

/**
 * KPI量值标准Service接口
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public interface IKpiMagnitudeService 
{
    /**
     * 查询KPI量值标准
     * 
     * @param magnitudeId KPI量值标准主键
     * @return KPI量值标准
     */
    public KpiMagnitude selectKpiMagnitudeByMagnitudeId(Long magnitudeId);

    /**
     * 查询KPI量值标准列表
     * 
     * @param kpiMagnitude KPI量值标准
     * @return KPI量值标准集合
     */
    public List<KpiMagnitude> selectKpiMagnitudeList(KpiMagnitude kpiMagnitude);

    /**
     * 新增KPI量值标准
     * 
     * @param kpiMagnitude KPI量值标准
     * @return 结果
     */
    public int insertKpiMagnitude(KpiMagnitude kpiMagnitude);

    /**
     * 修改KPI量值标准
     * 
     * @param kpiMagnitude KPI量值标准
     * @return 结果
     */
    public int updateKpiMagnitude(KpiMagnitude kpiMagnitude);

    /**
     * 批量删除KPI量值标准
     * 
     * @param magnitudeIds 需要删除的KPI量值标准主键集合
     * @return 结果
     */
    public int deleteKpiMagnitudeByMagnitudeIds(Long[] magnitudeIds);

    /**
     * 删除KPI量值标准信息
     * 
     * @param magnitudeId KPI量值标准主键
     * @return 结果
     */
    public int deleteKpiMagnitudeByMagnitudeId(Long magnitudeId);
}
