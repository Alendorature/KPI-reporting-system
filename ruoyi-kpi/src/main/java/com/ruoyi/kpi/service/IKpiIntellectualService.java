package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiIntellectual;

/**
 * 知识产权Service接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface IKpiIntellectualService 
{
    /**
     * 查询知识产权
     * 
     * @param intellectualId 知识产权主键
     * @return 知识产权
     */
    public KpiIntellectual selectKpiIntellectualByIntellectualId(Long intellectualId);

    /**
     * 查询知识产权列表
     * 
     * @param kpiIntellectual 知识产权
     * @return 知识产权集合
     */
    public List<KpiIntellectual> selectKpiIntellectualList(KpiIntellectual kpiIntellectual);

    /**
     * 新增知识产权
     * 
     * @param kpiIntellectual 知识产权
     * @return 结果
     */
    public int insertKpiIntellectual(KpiIntellectual kpiIntellectual);

    /**
     * 修改知识产权
     * 
     * @param kpiIntellectual 知识产权
     * @return 结果
     */
    public int updateKpiIntellectual(KpiIntellectual kpiIntellectual);

    /**
     * 批量删除知识产权
     * 
     * @param intellectualIds 需要删除的知识产权主键集合
     * @return 结果
     */
    public int deleteKpiIntellectualByIntellectualIds(Long[] intellectualIds);

    /**
     * 删除知识产权信息
     * 
     * @param intellectualId 知识产权主键
     * @return 结果
     */
    public int deleteKpiIntellectualByIntellectualId(Long intellectualId);
}
