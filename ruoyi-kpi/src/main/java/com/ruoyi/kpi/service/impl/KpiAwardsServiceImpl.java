package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.domain.KpiStandard;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiAwardsMapper;
import com.ruoyi.kpi.domain.KpiAwards;
import com.ruoyi.kpi.service.IKpiAwardsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 奖项信息Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiAwardsServiceImpl implements IKpiAwardsService 
{
    @Autowired
    private KpiAwardsMapper kpiAwardsMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;

    /**
     * 查询奖项信息
     * 
     * @param awardsId 奖项信息主键
     * @return 奖项信息
     */
    @Override
    public KpiAwards selectKpiAwardsByAwardsId(Long awardsId)
    {
        return kpiAwardsMapper.selectKpiAwardsByAwardsId(awardsId);
    }

    /**
     * 查询奖项信息列表
     * 
     * @param kpiAwards 奖项信息
     * @return 奖项信息
     */
    @Override
    public List<KpiAwards> selectKpiAwardsList(KpiAwards kpiAwards)
    {
        return kpiAwardsMapper.selectKpiAwardsList(kpiAwards);
    }

    /**
     * 新增奖项信息
     * 
     * @param kpiAwards 奖项信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiAwards(KpiAwards kpiAwards)
    {
        Long magnitudeId = kpiAwards.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);
        Long basicScore = kpiMagnitude.getBasicScore();

        kpiAwards.setProjectScore(basicScore);
        kpiAwards.setAuditState("0");
        kpiAwards.setCreateTime(DateUtils.getNowDate());
        return kpiAwardsMapper.insertKpiAwards(kpiAwards);
    }


    public  void updateRanking(){
        List<KpiAwards> kpiAwardsList = kpiAwardsMapper.selectKpiAwardsListProjectScoreDesc();
        for (int i = 0; i < kpiAwardsList.size(); i++){
            KpiAwards kpiAwards = kpiAwardsList.get(i);
            KpiAwards kpiAwards1 = new KpiAwards();
            kpiAwards1.setRanking(Long.valueOf(i+1));
            kpiAwards1.setAwardsId(kpiAwards.getAwardsId());
            kpiAwardsMapper.updateKpiAwards(kpiAwards1);
        }
    }

    /**
     * 修改奖项信息
     * 
     * @param kpiAwards 奖项信息
     * @return 结果
     */
    @Override
    public int updateKpiAwards(KpiAwards kpiAwards)
    {
        kpiAwards.setUpdateTime(DateUtils.getNowDate());
        int i= kpiAwardsMapper.updateKpiAwards(kpiAwards);
        if(i>0){
            if(kpiAwards.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除奖项信息
     * 
     * @param awardsIds 需要删除的奖项信息主键
     * @return 结果
     */
    @Override
    public int deleteKpiAwardsByAwardsIds(Long[] awardsIds)
    {
        return kpiAwardsMapper.deleteKpiAwardsByAwardsIds(awardsIds);
    }

    /**
     * 删除奖项信息信息
     * 
     * @param awardsId 奖项信息主键
     * @return 结果
     */
    @Override
    public int deleteKpiAwardsByAwardsId(Long awardsId)
    {
        return kpiAwardsMapper.deleteKpiAwardsByAwardsId(awardsId);
    }
}
