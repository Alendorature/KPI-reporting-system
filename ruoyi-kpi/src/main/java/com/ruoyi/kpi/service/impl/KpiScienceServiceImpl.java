package com.ruoyi.kpi.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.domain.KpiProject;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiScienceMapper;
import com.ruoyi.kpi.domain.KpiScience;
import com.ruoyi.kpi.service.IKpiScienceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 科技成果Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiScienceServiceImpl implements IKpiScienceService 
{
    @Autowired
    private KpiScienceMapper kpiScienceMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;

    /**
     * 查询科技成果
     * 
     * @param scienceId 科技成果主键
     * @return 科技成果
     */
    @Override
    public KpiScience selectKpiScienceByScienceId(Long scienceId)
    {
        return kpiScienceMapper.selectKpiScienceByScienceId(scienceId);
    }

    /**
     * 查询科技成果列表
     * 
     * @param kpiScience 科技成果
     * @return 科技成果
     */
    @Override
    public List<KpiScience> selectKpiScienceList(KpiScience kpiScience)
    {
        return kpiScienceMapper.selectKpiScienceList(kpiScience);
    }

    /**
     * 新增科技成果
     * 
     * @param kpiScience 科技成果
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiScience(KpiScience kpiScience)
    {
        Long magnitudeId = kpiScience.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);

        Long basicScore = kpiMagnitude.getBasicScore();
        Long projectScore=basicScore;
        Long extroScore = kpiMagnitude.getExtroScore();
        if (extroScore!=null&&extroScore>0){
            BigDecimal scienceMoney = kpiScience.getScienceMoney();
            BigDecimal divide = scienceMoney.divide(new BigDecimal(extroScore), 0, RoundingMode.DOWN);
            projectScore+=divide.longValue();
        }


        kpiScience.setProjectScore(projectScore);
        kpiScience.setAuditState("0");
        kpiScience.setCreateTime(DateUtils.getNowDate());
        return kpiScienceMapper.insertKpiScience(kpiScience);
    }

    public  void updateRanking(){
        List<KpiScience> kpiScienceList = kpiScienceMapper.selectKpiScienceListProjectScoreDesc();
        for (int i = 0; i < kpiScienceList.size(); i++){
            KpiScience kpiScience = kpiScienceList.get(i);
            KpiScience kpiScience1 = new KpiScience();
            kpiScience1.setRanking(Long.valueOf(i+1));
            kpiScience1.setScienceId(kpiScience.getScienceId());
            kpiScienceMapper.updateKpiScience(kpiScience1);
        }
    }

    /**
     * 修改科技成果
     * 
     * @param kpiScience 科技成果
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKpiScience(KpiScience kpiScience)
    {
        kpiScience.setUpdateTime(DateUtils.getNowDate());
        int i= kpiScienceMapper.updateKpiScience(kpiScience);
        if(i>0){
            if(kpiScience.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除科技成果
     * 
     * @param scienceIds 需要删除的科技成果主键
     * @return 结果
     */
    @Override
    public int deleteKpiScienceByScienceIds(Long[] scienceIds)
    {
        return kpiScienceMapper.deleteKpiScienceByScienceIds(scienceIds);
    }

    /**
     * 删除科技成果信息
     * 
     * @param scienceId 科技成果主键
     * @return 结果
     */
    @Override
    public int deleteKpiScienceByScienceId(Long scienceId)
    {
        return kpiScienceMapper.deleteKpiScienceByScienceId(scienceId);
    }
}
