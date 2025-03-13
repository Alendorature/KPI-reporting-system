package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiScience;

/**
 * 科技成果Mapper接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface KpiScienceMapper 
{
    /**
     * 查询科技成果
     * 
     * @param scienceId 科技成果主键
     * @return 科技成果
     */
    public KpiScience selectKpiScienceByScienceId(Long scienceId);

    /**
     * 查询科技成果列表
     * 
     * @param kpiScience 科技成果
     * @return 科技成果集合
     */
    public List<KpiScience> selectKpiScienceList(KpiScience kpiScience);

    public List<KpiScience> selectKpiScienceListProjectScoreDesc();
    /**
     * 新增科技成果
     * 
     * @param kpiScience 科技成果
     * @return 结果
     */
    public int insertKpiScience(KpiScience kpiScience);

    /**
     * 修改科技成果
     * 
     * @param kpiScience 科技成果
     * @return 结果
     */
    public int updateKpiScience(KpiScience kpiScience);

    /**
     * 删除科技成果
     * 
     * @param scienceId 科技成果主键
     * @return 结果
     */
    public int deleteKpiScienceByScienceId(Long scienceId);

    /**
     * 批量删除科技成果
     * 
     * @param scienceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKpiScienceByScienceIds(Long[] scienceIds);
}
