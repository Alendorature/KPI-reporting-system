package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiAwards;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiExcellentPaperChaiMapper;
import com.ruoyi.kpi.domain.KpiExcellentPaperChai;
import com.ruoyi.kpi.service.IKpiExcellentPaperChaiService;

/**
 * 指导优秀论文与讲座Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiExcellentPaperChaiServiceImpl implements IKpiExcellentPaperChaiService 
{
    @Autowired
    private KpiExcellentPaperChaiMapper kpiExcellentPaperChaiMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;
    /**
     * 查询指导优秀论文与讲座
     * 
     * @param excellentPaperChairId 指导优秀论文与讲座主键
     * @return 指导优秀论文与讲座
     */
    @Override
    public KpiExcellentPaperChai selectKpiExcellentPaperChaiByExcellentPaperChairId(Long excellentPaperChairId)
    {
        return kpiExcellentPaperChaiMapper.selectKpiExcellentPaperChaiByExcellentPaperChairId(excellentPaperChairId);
    }

    /**
     * 查询指导优秀论文与讲座列表
     * 
     * @param kpiExcellentPaperChai 指导优秀论文与讲座
     * @return 指导优秀论文与讲座
     */
    @Override
    public List<KpiExcellentPaperChai> selectKpiExcellentPaperChaiList(KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        return kpiExcellentPaperChaiMapper.selectKpiExcellentPaperChaiList(kpiExcellentPaperChai);
    }

    /**
     * 新增指导优秀论文与讲座
     * 
     * @param kpiExcellentPaperChai 指导优秀论文与讲座
     * @return 结果
     */
    @Override
    public int insertKpiExcellentPaperChai(KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        Long magnitudeId = kpiExcellentPaperChai.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);
        Long basicScore = kpiMagnitude.getBasicScore();

        kpiExcellentPaperChai.setProjectScore(basicScore);
        kpiExcellentPaperChai.setAuditState("0");
        kpiExcellentPaperChai.setCreateTime(DateUtils.getNowDate());
        return kpiExcellentPaperChaiMapper.insertKpiExcellentPaperChai(kpiExcellentPaperChai);
    }


    public  void updateRanking(){
        List<KpiExcellentPaperChai> kpiExcellentPaperChais = kpiExcellentPaperChaiMapper.selectKpiExcellentPaperChaiListProjectScoreDesc();
        for (int i = 0; i < kpiExcellentPaperChais.size(); i++){
            KpiExcellentPaperChai kpiExcellentPaperChai = kpiExcellentPaperChais.get(i);
            KpiExcellentPaperChai kpiExcellentPaperChai1 = new KpiExcellentPaperChai();
            kpiExcellentPaperChai1.setRanking(Long.valueOf(i+1));
            kpiExcellentPaperChai1.setExcellentPaperChairId(kpiExcellentPaperChai.getExcellentPaperChairId());
            kpiExcellentPaperChaiMapper.updateKpiExcellentPaperChai(kpiExcellentPaperChai1);
        }
    }

    /**
     * 修改指导优秀论文与讲座
     * 
     * @param kpiExcellentPaperChai 指导优秀论文与讲座
     * @return 结果
     */
    @Override
    public int updateKpiExcellentPaperChai(KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        kpiExcellentPaperChai.setUpdateTime(DateUtils.getNowDate());
        int i= kpiExcellentPaperChaiMapper.updateKpiExcellentPaperChai(kpiExcellentPaperChai);
        if(i>0){
            if(kpiExcellentPaperChai.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除指导优秀论文与讲座
     * 
     * @param excellentPaperChairIds 需要删除的指导优秀论文与讲座主键
     * @return 结果
     */
    @Override
    public int deleteKpiExcellentPaperChaiByExcellentPaperChairIds(Long[] excellentPaperChairIds)
    {
        return kpiExcellentPaperChaiMapper.deleteKpiExcellentPaperChaiByExcellentPaperChairIds(excellentPaperChairIds);
    }

    /**
     * 删除指导优秀论文与讲座信息
     * 
     * @param excellentPaperChairId 指导优秀论文与讲座主键
     * @return 结果
     */
    @Override
    public int deleteKpiExcellentPaperChaiByExcellentPaperChairId(Long excellentPaperChairId)
    {
        return kpiExcellentPaperChaiMapper.deleteKpiExcellentPaperChaiByExcellentPaperChairId(excellentPaperChairId);
    }
}
