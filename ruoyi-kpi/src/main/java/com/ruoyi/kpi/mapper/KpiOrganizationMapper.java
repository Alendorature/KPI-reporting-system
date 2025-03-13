package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiOrganization;

/**
 * 国际学术组织任职Mapper接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface KpiOrganizationMapper 
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

    public List<KpiOrganization> selectKpiOrganizationListProjectScoreDesc();
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
     * 删除国际学术组织任职
     * 
     * @param organizationId 国际学术组织任职主键
     * @return 结果
     */
    public int deleteKpiOrganizationByOrganizationId(Long organizationId);

    /**
     * 批量删除国际学术组织任职
     * 
     * @param organizationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKpiOrganizationByOrganizationIds(Long[] organizationIds);
}
