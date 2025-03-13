package com.ruoyi.kpi.mapper;

import java.util.List;

import com.ruoyi.kpi.domain.KpiStatistics;
import com.ruoyi.kpi.domain.KpiTeacher;
import org.apache.ibatis.annotations.Param;

/**
 * 老师基本信息Mapper接口
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public interface KpiTeacherMapper 
{
    /**
     * 查询老师基本信息
     * 
     * @param teacherId 老师基本信息主键
     * @return 老师基本信息
     */
    public KpiTeacher selectKpiTeacherByTeacherId(Long teacherId);

    /**
     * kpi查询
     * @param kpiYear
     * @return
     */
    public List<KpiStatistics> selectKpiList(@Param("kpiYear") String kpiYear);

    /**
     * 查询老师基本信息列表
     * 
     * @param kpiTeacher 老师基本信息
     * @return 老师基本信息集合
     */
    public List<KpiTeacher> selectKpiTeacherList(KpiTeacher kpiTeacher);

    public List<KpiStatistics> selectKpiListTop10(@Param("kpiYear") String kpiYear);

    public Long selectCountTeacher();

    public Long selectSumCountByAuditState(@Param("auditState") String auditState);

    public Long  selectMaxSumTotal(@Param("kpiYear") String kpiYear);

    public KpiTeacher checkPhoneUnique(String teacherTel);

    /**
     * 新增老师基本信息
     * 
     * @param kpiTeacher 老师基本信息
     * @return 结果
     */
    public int insertKpiTeacher(KpiTeacher kpiTeacher);

    /**
     * 修改老师基本信息
     * 
     * @param kpiTeacher 老师基本信息
     * @return 结果
     */
    public int updateKpiTeacher(KpiTeacher kpiTeacher);

    /**
     * 删除老师基本信息
     * 
     * @param teacherId 老师基本信息主键
     * @return 结果
     */
    public int deleteKpiTeacherByTeacherId(Long teacherId);

    /**
     * 批量删除老师基本信息
     * 
     * @param teacherIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKpiTeacherByTeacherIds(Long[] teacherIds);
}
