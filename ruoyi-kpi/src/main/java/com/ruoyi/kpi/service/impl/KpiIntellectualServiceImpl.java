package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.domain.KpiPaperNoTeach;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiIntellectualMapper;
import com.ruoyi.kpi.domain.KpiIntellectual;
import com.ruoyi.kpi.service.IKpiIntellectualService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 知识产权Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiIntellectualServiceImpl implements IKpiIntellectualService 
{
    @Autowired
    private KpiIntellectualMapper kpiIntellectualMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;

    /**
     * 查询知识产权
     * 
     * @param intellectualId 知识产权主键
     * @return 知识产权
     */
    @Override
    public KpiIntellectual selectKpiIntellectualByIntellectualId(Long intellectualId)
    {
        return kpiIntellectualMapper.selectKpiIntellectualByIntellectualId(intellectualId);
    }

    /**
     * 查询知识产权列表
     * 
     * @param kpiIntellectual 知识产权
     * @return 知识产权
     */
    @Override
    public List<KpiIntellectual> selectKpiIntellectualList(KpiIntellectual kpiIntellectual)
    {
        return kpiIntellectualMapper.selectKpiIntellectualList(kpiIntellectual);
    }

    /**
     * 新增知识产权
     * 
     * @param kpiIntellectual 知识产权
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiIntellectual(KpiIntellectual kpiIntellectual)
    {   Long magnitudeId = kpiIntellectual.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);
        Long basicScore = kpiMagnitude.getBasicScore();

        kpiIntellectual.setProjectScore(basicScore);
        kpiIntellectual.setAuditState("0");
        kpiIntellectual.setCreateTime(DateUtils.getNowDate());
        return kpiIntellectualMapper.insertKpiIntellectual(kpiIntellectual);
    }

    public  void updateRanking(){
        List<KpiIntellectual> kpiIntellectuals = kpiIntellectualMapper.selectKpiIntellectualListProjectScoreDesc();
        for (int i = 0; i < kpiIntellectuals.size(); i++){
            KpiIntellectual kpiIntellectual = kpiIntellectuals.get(i);
            KpiIntellectual kpiIntellectual1 = new KpiIntellectual();
            kpiIntellectual1.setRanking(Long.valueOf(i+1));
            kpiIntellectual1.setIntellectualId(kpiIntellectual.getIntellectualId());
            kpiIntellectualMapper.updateKpiIntellectual(kpiIntellectual1);
        }
    }
    /**
     * 修改知识产权
     * 
     * @param kpiIntellectual 知识产权
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKpiIntellectual(KpiIntellectual kpiIntellectual)
    {
        kpiIntellectual.setUpdateTime(DateUtils.getNowDate());
        int i= kpiIntellectualMapper.updateKpiIntellectual(kpiIntellectual);
        if(i>0){
            if(kpiIntellectual.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除知识产权
     * 
     * @param intellectualIds 需要删除的知识产权主键
     * @return 结果
     */
    @Override
    public int deleteKpiIntellectualByIntellectualIds(Long[] intellectualIds)
    {
        return kpiIntellectualMapper.deleteKpiIntellectualByIntellectualIds(intellectualIds);
    }

    /**
     * 删除知识产权信息
     * 
     * @param intellectualId 知识产权主键
     * @return 结果
     */
    @Override
    public int deleteKpiIntellectualByIntellectualId(Long intellectualId)
    {
        return kpiIntellectualMapper.deleteKpiIntellectualByIntellectualId(intellectualId);
    }
}
