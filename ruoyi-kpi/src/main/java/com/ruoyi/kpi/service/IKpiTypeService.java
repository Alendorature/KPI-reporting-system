package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiType;

/**
 * kpi分类Service接口
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public interface IKpiTypeService 
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
     * 批量删除kpi分类
     * 
     * @param typeIds 需要删除的kpi分类主键集合
     * @return 结果
     */
    public int deleteKpiTypeByTypeIds(Long[] typeIds);

    /**
     * 删除kpi分类信息
     * 
     * @param typeId kpi分类主键
     * @return 结果
     */
    public int deleteKpiTypeByTypeId(Long typeId);
}
