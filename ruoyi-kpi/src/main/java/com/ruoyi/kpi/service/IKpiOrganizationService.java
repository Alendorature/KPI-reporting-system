package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiOrganization;

/**
 * 国际学术组织任职Service接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface IKpiOrganizationService 
{
    /**
     * 查询国际学术组织任职
     * 
     * @param organizationId 国际学术组织任职主键
     * @return 国际学术组织任职
     */
    public KpiOrganization selectKpiOrganizationByOrganizationId(Long organizationId);

    /**
     * 查询国际学术组织任职列表
     * 
     * @param kpiOrganization 国际学术组织任职
     * @return 国际学术组织任职集合
     */
    public List<KpiOrganization> selectKpiOrganizationList(KpiOrganization kpiOrganization);

    /**
     * 新增国际学术组织任职
     * 
     * @param kpiOrganization 国际学术组织任职
     * @return 结果
     */
    public int insertKpiOrganization(KpiOrganization kpiOrganization);

    /**
     * 修改国际学术组织任职
     * 
     * @param kpiOrganization 国际学术组织任职
     * @return 结果
     */
    public int updateKpiOrganization(KpiOrganization kpiOrganization);

    /**
     * 批量删除国际学术组织任职
     * 
     * @param organizationIds 需要删除的国际学术组织任职主键集合
     * @return 结果
     */
    public int deleteKpiOrganizationByOrganizationIds(Long[] organizationIds);

    /**
     * 删除国际学术组织任职信息
     * 
     * @param organizationId 国际学术组织任职主键
     * @return 结果
     */
    public int deleteKpiOrganizationByOrganizationId(Long organizationId);
}
