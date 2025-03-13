package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiExcellentPaperChai;

/**
 * 指导优秀论文与讲座Mapper接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface KpiExcellentPaperChaiMapper 
{
    /**
     * 查询指导优秀论文与讲座
     * 
     * @param excellentPaperChairId 指导优秀论文与讲座主键
     * @return 指导优秀论文与讲座
     */
    public KpiExcellentPaperChai selectKpiExcellentPaperChaiByExcellentPaperChairId(Long excellentPaperChairId);

    /**
     * 查询指导优秀论文与讲座列表
     * 
     * @param kpiExcellentPaperChai 指导优秀论文与讲座
     * @return 指导优秀论文与讲座集合
     */
    public List<KpiExcellentPaperChai> selectKpiExcellentPaperChaiList(KpiExcellentPaperChai kpiExcellentPaperChai);

    public List<KpiExcellentPaperChai> selectKpiExcellentPaperChaiListProjectScoreDesc();

    /**
     * 新增指导优秀论文与讲座
     * 
     * @param kpiExcellentPaperChai 指导优秀论文与讲座
     * @return 结果
     */
    public int insertKpiExcellentPaperChai(KpiExcellentPaperChai kpiExcellentPaperChai);

    /**
     * 修改指导优秀论文与讲座
     * 
     * @param kpiExcellentPaperChai 指导优秀论文与讲座
     * @return 结果
     */
    public int updateKpiExcellentPaperChai(KpiExcellentPaperChai kpiExcellentPaperChai);

    /**
     * 删除指导优秀论文与讲座
     * 
     * @param excellentPaperChairId 指导优秀论文与讲座主键
     * @return 结果
     */
    public int deleteKpiExcellentPaperChaiByExcellentPaperChairId(Long excellentPaperChairId);

    /**
     * 批量删除指导优秀论文与讲座
     * 
     * @param excellentPaperChairIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKpiExcellentPaperChaiByExcellentPaperChairIds(Long[] excellentPaperChairIds);
}
