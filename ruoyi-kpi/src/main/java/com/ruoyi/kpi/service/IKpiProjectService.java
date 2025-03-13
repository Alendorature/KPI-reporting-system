package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiProject;

/**
 * 项目信息Service接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface IKpiProjectService 
{
    /**
     * 查询项目信息
     * 
     * @param projectId 项目信息主键
     * @return 项目信息
     */
    public KpiProject selectKpiProjectByProjectId(Long projectId);

    /**
     * 查询项目信息列表
     * 
     * @param kpiProject 项目信息
     * @return 项目信息集合
     */
    public List<KpiProject> selectKpiProjectList(KpiProject kpiProject);

    /**
     * 新增项目信息
     * 
     * @param kpiProject 项目信息
     * @return 结果
     */
    public int insertKpiProject(KpiProject kpiProject);

    /**
     * 修改项目信息
     * 
     * @param kpiProject 项目信息
     * @return 结果
     */
    public int updateKpiProject(KpiProject kpiProject);

    /**
     * 批量删除项目信息
     * 
     * @param projectIds 需要删除的项目信息主键集合
     * @return 结果
     */
    public int deleteKpiProjectByProjectIds(Long[] projectIds);

    /**
     * 删除项目信息信息
     * 
     * @param projectId 项目信息主键
     * @return 结果
     */
    public int deleteKpiProjectByProjectId(Long projectId);
}
