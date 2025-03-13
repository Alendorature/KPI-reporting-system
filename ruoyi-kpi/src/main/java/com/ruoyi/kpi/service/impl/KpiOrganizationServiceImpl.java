package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.domain.KpiStandard;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiOrganizationMapper;
import com.ruoyi.kpi.domain.KpiOrganization;
import com.ruoyi.kpi.service.IKpiOrganizationService;

/**
 * 国际学术组织任职Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiOrganizationServiceImpl implements IKpiOrganizationService 
{
    @Autowired
    private KpiOrganizationMapper kpiOrganizationMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;

    /**
     * 查询国际学术组织任职
     * 
     * @param organizationId 国际学术组织任职主键
     * @return 国际学术组织任职
     */
    @Override
    public KpiOrganization selectKpiOrganizationByOrganizationId(Long organizationId)
    {
        return kpiOrganizationMapper.selectKpiOrganizationByOrganizationId(organizationId);
    }

    /**
     * 查询国际学术组织任职列表
     * 
     * @param kpiOrganization 国际学术组织任职
     * @return 国际学术组织任职
     */
    @Override
    public List<KpiOrganization> selectKpiOrganizationList(KpiOrganization kpiOrganization)
    {
        return kpiOrganizationMapper.selectKpiOrganizationList(kpiOrganization);
    }

    /**
     * 新增国际学术组织任职
     * 
     * @param kpiOrganization 国际学术组织任职
     * @return 结果
     */
    @Override
    public int insertKpiOrganization(KpiOrganization kpiOrganization)
    {
        Long magnitudeId = kpiOrganization.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);
        Long basicScore = kpiMagnitude.getBasicScore();

        kpiOrganization.setProjectScore(basicScore);
        kpiOrganization.setAuditState("0");
        kpiOrganization.setCreateTime(DateUtils.getNowDate());
        return kpiOrganizationMapper.insertKpiOrganization(kpiOrganization);
    }



    /**
     * 修改国际学术组织任职
     * 
     * @param kpiOrganization 国际学术组织任职
     * @return 结果
     */
    @Override
    public int updateKpiOrganization(KpiOrganization kpiOrganization)
    {
        kpiOrganization.setUpdateTime(DateUtils.getNowDate());
        return kpiOrganizationMapper.updateKpiOrganization(kpiOrganization);
    }

    /**
     * 批量删除国际学术组织任职
     * 
     * @param organizationIds 需要删除的国际学术组织任职主键
     * @return 结果
     */
    @Override
    public int deleteKpiOrganizationByOrganizationIds(Long[] organizationIds)
    {
        return kpiOrganizationMapper.deleteKpiOrganizationByOrganizationIds(organizationIds);
    }

    /**
     * 删除国际学术组织任职信息
     * 
     * @param organizationId 国际学术组织任职主键
     * @return 结果
     */
    @Override
    public int deleteKpiOrganizationByOrganizationId(Long organizationId)
    {
        return kpiOrganizationMapper.deleteKpiOrganizationByOrganizationId(organizationId);
    }
}
