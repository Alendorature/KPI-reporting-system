package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiType;

/**
 * kpi分类Mapper接口
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public interface KpiTypeMapper 
{
    /**
     * 查询kpi分类
     * 
     * @param typeId kpi分类主键
     * @return kpi分类
     */
    public KpiType selectKpiTypeByTypeId(Long typeId);

    /**
     * 查询kpi分类列表
     * 
     * @param kpiType kpi分类
     * @return kpi分类集合
     */
    public List<KpiType> selectKpiTypeList(KpiType kpiType);

    /**
     * 新增kpi分类
     * 
     * @param kpiType kpi分类
     * @return 结果
     */
    public int insertKpiType(KpiType kpiType);

    /**
     * 修改kpi分类
     * 
     * @param kpiType kpi分类
     * @return 结果
     */
    public int updateKpiType(KpiType kpiType);

    /**
     * 删除kpi分类
     * 
     * @param typeId kpi分类主键
     * @return 结果
     */
    public int deleteKpiTypeByTypeId(Long typeId);

    /**
     * 批量删除kpi分类
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKpiTypeByTypeIds(Long[] typeIds);
}
