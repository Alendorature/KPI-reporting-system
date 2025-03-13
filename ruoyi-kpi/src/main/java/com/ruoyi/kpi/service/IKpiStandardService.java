package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiStandard;

/**
 * 标准及平台Service接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface IKpiStandardService 
{
    /**
     * 查询标准及平台
     * 
     * @param standardId 标准及平台主键
     * @return 标准及平台
     */
    public KpiStandard selectKpiStandardByStandardId(Long standardId);

    /**
     * 查询标准及平台列表
     * 
     * @param kpiStandard 标准及平台
     * @return 标准及平台集合
     */
    public List<KpiStandard> selectKpiStandardList(KpiStandard kpiStandard);

    /**
     * 新增标准及平台
     * 
     * @param kpiStandard 标准及平台
     * @return 结果
     */
    public int insertKpiStandard(KpiStandard kpiStandard);

    /**
     * 修改标准及平台
     * 
     * @param kpiStandard 标准及平台
     * @return 结果
     */
    public int updateKpiStandard(KpiStandard kpiStandard);

    /**
     * 批量删除标准及平台
     * 
     * @param standardIds 需要删除的标准及平台主键集合
     * @return 结果
     */
    public int deleteKpiStandardByStandardIds(Long[] standardIds);

    /**
     * 删除标准及平台信息
     * 
     * @param standardId 标准及平台主键
     * @return 结果
     */
    public int deleteKpiStandardByStandardId(Long standardId);
}
