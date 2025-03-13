package com.ruoyi.kpi.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kpi.domain.KpiMagnitude;
import com.ruoyi.kpi.domain.KpiPaperNoTeach;
import com.ruoyi.kpi.service.IKpiMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiProjectMapper;
import com.ruoyi.kpi.domain.KpiProject;
import com.ruoyi.kpi.service.IKpiProjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目信息Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@Service
public class KpiProjectServiceImpl implements IKpiProjectService 
{
    @Autowired
    private KpiProjectMapper kpiProjectMapper;

    @Autowired
    private IKpiMagnitudeService kpiMagnitudeService;

    /**
     * 查询项目信息
     * 
     * @param projectId 项目信息主键
     * @return 项目信息
     */
    @Override
    public KpiProject selectKpiProjectByProjectId(Long projectId)
    {
        return kpiProjectMapper.selectKpiProjectByProjectId(projectId);
    }

    /**
     * 查询项目信息列表
     * 
     * @param kpiProject 项目信息
     * @return 项目信息
     */
    @Override
    public List<KpiProject> selectKpiProjectList(KpiProject kpiProject)
    {
        return kpiProjectMapper.selectKpiProjectList(kpiProject);
    }

    /**
     * 新增项目信息
     * 
     * @param kpiProject 项目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiProject(KpiProject kpiProject)
    {
        Long magnitudeId = kpiProject.getMagnitudeId();
        KpiMagnitude kpiMagnitude = kpiMagnitudeService.selectKpiMagnitudeByMagnitudeId(magnitudeId);

        Long basicScore = kpiMagnitude.getBasicScore();
        Long projectScore=basicScore;
        Long extroScore = kpiMagnitude.getExtroScore();
        if (extroScore!=null&&extroScore>0){
            BigDecimal actualExpenditure = kpiProject.getActualExpenditure();
            BigDecimal divide = actualExpenditure.divide(new BigDecimal(extroScore), 0, RoundingMode.DOWN);
            projectScore+=divide.longValue();
        }


        kpiProject.setProjectScore(projectScore);
        kpiProject.setAuditState("0");
        kpiProject.setCreateTime(DateUtils.getNowDate());
        return kpiProjectMapper.insertKpiProject(kpiProject);
    }

    public  void updateRanking(){
        List<KpiProject> kpiProjects = kpiProjectMapper.selectKpiProjectListProjectScoreDesc();
        for (int i = 0; i < kpiProjects.size(); i++){
            KpiProject kpiProject = kpiProjects.get(i);
            KpiProject kpiProject1 = new KpiProject();
            kpiProject1.setRanking(Long.valueOf(i+1));
            kpiProject1.setProjectId(kpiProject.getProjectId());
            kpiProjectMapper.updateKpiProject(kpiProject1);
        }
    }

    /**
     * 修改项目信息
     * 
     * @param kpiProject 项目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKpiProject(KpiProject kpiProject)
    {
        kpiProject.setUpdateTime(DateUtils.getNowDate());
        int i= kpiProjectMapper.updateKpiProject(kpiProject);
        if(i>0){
            if(kpiProject.getAuditState().equals("1")){
                updateRanking();
            }

        }
        return i;
    }

    /**
     * 批量删除项目信息
     * 
     * @param projectIds 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteKpiProjectByProjectIds(Long[] projectIds)
    {
        return kpiProjectMapper.deleteKpiProjectByProjectIds(projectIds);
    }

    /**
     * 删除项目信息信息
     * 
     * @param projectId 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteKpiProjectByProjectId(Long projectId)
    {
        return kpiProjectMapper.deleteKpiProjectByProjectId(projectId);
    }
}
