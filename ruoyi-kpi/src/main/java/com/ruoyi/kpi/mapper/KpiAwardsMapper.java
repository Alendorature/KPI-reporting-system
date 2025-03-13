package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiAwards;

/**
 * 奖项信息Mapper接口
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public interface KpiAwardsMapper 
{
    /**
     * 查询奖项信息
     * 
     * @param awardsId 奖项信息主键
     * @return 奖项信息
     */
    public KpiAwards selectKpiAwardsByAwardsId(Long awardsId);

    /**
     * 查询奖项信息列表
     * 
     * @param kpiAwards 奖项信息
     * @return 奖项信息集合
     */
    public List<KpiAwards> selectKpiAwardsList(KpiAwards kpiAwards);

    public List<KpiAwards> selectKpiAwardsListProjectScoreDesc();

    /**
     * 新增奖项信息
     * 
     * @param kpiAwards 奖项信息
     * @return 结果
     */
    public int insertKpiAwards(KpiAwards kpiAwards);

    /**
     * 修改奖项信息
     * 
     * @param kpiAwards 奖项信息
     * @return 结果
     */
    public int updateKpiAwards(KpiAwards kpiAwards);

    /**
     * 删除奖项信息
     * 
     * @param awardsId 奖项信息主键
     * @return 结果
     */
    public int deleteKpiAwardsByAwardsId(Long awardsId);

    /**
     * 批量删除奖项信息
     * 
     * @param awardsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKpiAwardsByAwardsIds(Long[] awardsIds);
}
