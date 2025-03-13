package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.domain.KpiPaperNoTeach;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiStandardMapper;
import com.ruoyi.kpi.domain.KpiStandard;
import com.ruoyi.kpi.service.IKpiStandardService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标准及平台Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiStandardServiceImpl implements IKpiStandardService 
{
    @Autowired
    private KpiStandardMapper kpiStandardMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;

    /**
     * 查询标准及平台
     * 
     * @param standardId 标准及平台主键
     * @return 标准及平台
     */
    @Override
    public KpiStandard selectKpiStandardByStandardId(Long standardId)
    {
        return kpiStandardMapper.selectKpiStandardByStandardId(standardId);
    }

    /**
     * 查询标准及平台列表
     * 
     * @param kpiStandard 标准及平台
     * @return 标准及平台
     */
    @Override
    public List<KpiStandard> selectKpiStandardList(KpiStandard kpiStandard)
    {
        return kpiStandardMapper.selectKpiStandardList(kpiStandard);
    }

    /**
     * 新增标准及平台
     * 
     * @param kpiStandard 标准及平台
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiStandard(KpiStandard kpiStandard)
    {
        Long magnitudeId = kpiStandard.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);
        Long basicScore = kpiMagnitude.getBasicScore();

        kpiStandard.setProjectScore(basicScore);
        kpiStandard.setAuditState("0");
        kpiStandard.setCreateTime(DateUtils.getNowDate());
        return kpiStandardMapper.insertKpiStandard(kpiStandard);
    }

    public  void updateRanking(){
        List<KpiStandard> kpiStandards = kpiStandardMapper.selectKpiStandardListProjectScoreDesc();
        for (int i = 0; i < kpiStandards.size(); i++){
            KpiStandard kpiStandard = kpiStandards.get(i);
            KpiStandard kpiStandard1 = new KpiStandard();
            kpiStandard1.setRanking(Long.valueOf(i+1));
            kpiStandard1.setStandardId(kpiStandard.getStandardId());
            kpiStandardMapper.updateKpiStandard(kpiStandard1);
        }
    }
    /**
     * 修改标准及平台
     * 
     * @param kpiStandard 标准及平台
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKpiStandard(KpiStandard kpiStandard)
    {
        kpiStandard.setUpdateTime(DateUtils.getNowDate());
        int i= kpiStandardMapper.updateKpiStandard(kpiStandard);
        if(i>0){
            if(kpiStandard.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除标准及平台
     * 
     * @param standardIds 需要删除的标准及平台主键
     * @return 结果
     */
    @Override
    public int deleteKpiStandardByStandardIds(Long[] standardIds)
    {
        return kpiStandardMapper.deleteKpiStandardByStandardIds(standardIds);
    }

    /**
     * 删除标准及平台信息
     * 
     * @param standardId 标准及平台主键
     * @return 结果
     */
    @Override
    public int deleteKpiStandardByStandardId(Long standardId)
    {
        return kpiStandardMapper.deleteKpiStandardByStandardId(standardId);
    }
}
