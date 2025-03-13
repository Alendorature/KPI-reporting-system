package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiPaperNoTeachMapper;
import com.ruoyi.kpi.domain.KpiPaperNoTeach;
import com.ruoyi.kpi.service.IKpiPaperNoTeachService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 非教学类科研论文Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiPaperNoTeachServiceImpl implements IKpiPaperNoTeachService 
{
    @Autowired
    private KpiPaperNoTeachMapper kpiPaperNoTeachMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;



    /**
     * 查询非教学类科研论文
     * 
     * @param paperNoTeachId 非教学类科研论文主键
     * @return 非教学类科研论文
     */
    @Override
    public KpiPaperNoTeach selectKpiPaperNoTeachByPaperNoTeachId(Long paperNoTeachId)
    {
        return kpiPaperNoTeachMapper.selectKpiPaperNoTeachByPaperNoTeachId(paperNoTeachId);
    }

    /**
     * 查询非教学类科研论文列表
     * 
     * @param kpiPaperNoTeach 非教学类科研论文
     * @return 非教学类科研论文
     */
    @Override
    public List<KpiPaperNoTeach> selectKpiPaperNoTeachList(KpiPaperNoTeach kpiPaperNoTeach)
    {
        return kpiPaperNoTeachMapper.selectKpiPaperNoTeachList(kpiPaperNoTeach);
    }

    /**
     * 新增非教学类科研论文
     * 
     * @param kpiPaperNoTeach 非教学类科研论文
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiPaperNoTeach(KpiPaperNoTeach kpiPaperNoTeach)
    {
        Long magnitudeId = kpiPaperNoTeach.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);
        Long basicScore = kpiMagnitude.getBasicScore();

        kpiPaperNoTeach.setProjectScore(basicScore);
        kpiPaperNoTeach.setAuditState("0");
        kpiPaperNoTeach.setCreateTime(DateUtils.getNowDate());
        return kpiPaperNoTeachMapper.insertKpiPaperNoTeach(kpiPaperNoTeach);

    }

    public  void updateRanking(){
        List<KpiPaperNoTeach> kpiPaperNoTeaches = kpiPaperNoTeachMapper.selectKpiPaperNoTeachListProjectScoreDesc();
        for (int i = 0; i < kpiPaperNoTeaches.size(); i++){
            KpiPaperNoTeach kpiPaperNoTeach = kpiPaperNoTeaches.get(i);
            KpiPaperNoTeach kpiPaperNoTeach1 = new KpiPaperNoTeach();
            kpiPaperNoTeach1.setRanking(Long.valueOf(i+1));
            kpiPaperNoTeach1.setPaperNoTeachId(kpiPaperNoTeach.getPaperNoTeachId());
            kpiPaperNoTeachMapper.updateKpiPaperNoTeach(kpiPaperNoTeach1);
        }
    }
    /**
     * 修改非教学类科研论文
     * 
     * @param kpiPaperNoTeach 非教学类科研论文
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKpiPaperNoTeach(KpiPaperNoTeach kpiPaperNoTeach)
    {
        kpiPaperNoTeach.setUpdateTime(DateUtils.getNowDate());

        int i=kpiPaperNoTeachMapper.updateKpiPaperNoTeach(kpiPaperNoTeach);
        if(i>0){
            if(kpiPaperNoTeach.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除非教学类科研论文
     * 
     * @param paperNoTeachIds 需要删除的非教学类科研论文主键
     * @return 结果
     */
    @Override
    public int deleteKpiPaperNoTeachByPaperNoTeachIds(Long[] paperNoTeachIds)
    {
        return kpiPaperNoTeachMapper.deleteKpiPaperNoTeachByPaperNoTeachIds(paperNoTeachIds);
    }

    /**
     * 删除非教学类科研论文信息
     * 
     * @param paperNoTeachId 非教学类科研论文主键
     * @return 结果
     */
    @Override
    public int deleteKpiPaperNoTeachByPaperNoTeachId(Long paperNoTeachId)
    {
        return kpiPaperNoTeachMapper.deleteKpiPaperNoTeachByPaperNoTeachId(paperNoTeachId);
    }
}
