package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiPaperNoTeach;

/**
 * 非教学类科研论文Service接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface IKpiPaperNoTeachService 
{
    /**
     * 查询非教学类科研论文
     * 
     * @param paperNoTeachId 非教学类科研论文主键
     * @return 非教学类科研论文
     */
    public KpiPaperNoTeach selectKpiPaperNoTeachByPaperNoTeachId(Long paperNoTeachId);

    /**
     * 查询非教学类科研论文列表
     * 
     * @param kpiPaperNoTeach 非教学类科研论文
     * @return 非教学类科研论文集合
     */
    public List<KpiPaperNoTeach> selectKpiPaperNoTeachList(KpiPaperNoTeach kpiPaperNoTeach);

    /**
     * 新增非教学类科研论文
     * 
     * @param kpiPaperNoTeach 非教学类科研论文
     * @return 结果
     */
    public int insertKpiPaperNoTeach(KpiPaperNoTeach kpiPaperNoTeach);

    /**
     * 修改非教学类科研论文
     * 
     * @param kpiPaperNoTeach 非教学类科研论文
     * @return 结果
     */
    public int updateKpiPaperNoTeach(KpiPaperNoTeach kpiPaperNoTeach);

    /**
     * 批量删除非教学类科研论文
     * 
     * @param paperNoTeachIds 需要删除的非教学类科研论文主键集合
     * @return 结果
     */
    public int deleteKpiPaperNoTeachByPaperNoTeachIds(Long[] paperNoTeachIds);

    /**
     * 删除非教学类科研论文信息
     * 
     * @param paperNoTeachId 非教学类科研论文主键
     * @return 结果
     */
    public int deleteKpiPaperNoTeachByPaperNoTeachId(Long paperNoTeachId);
}
