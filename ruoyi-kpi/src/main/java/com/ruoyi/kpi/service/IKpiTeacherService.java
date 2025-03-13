package com.ruoyi.kpi.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.kpi.domain.KpiStatistics;
import com.ruoyi.kpi.domain.KpiTeacher;
import org.apache.ibatis.annotations.Param;

/**
 * 老师基本信息Service接口
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public interface IKpiTeacherService 
{
    /**
     * 查询老师基本信息
     * 
     * @param teacherId 老师基本信息主键
     * @return 老师基本信息
     */
    public KpiTeacher selectKpiTeacherByTeacherId(Long teacherId);

    public Long selectCountTeacher();

    public Long selectSumCountByAuditState( String auditState);

    public Long  selectMaxSumTotal(String kpiYear);

    /**
     * 校验手机号码是否唯一
     *
     * @param kpiTeacher 老师信息
     * @return 结果
     */
    public boolean checkPhoneUnique(KpiTeacher kpiTeacher);

    /**
     * kpi查询
     * @param kpiYear
     * @return
     */
    public List<KpiStatistics> selectKpiList( String kpiYear);

    public List<KpiStatistics> selectKpiListTop10( String kpiYear);
    /**
     * 查询老师基本信息列表
     * 
     * @param kpiTeacher 老师基本信息
     * @return 老师基本信息集合
     */
    public List<KpiTeacher> selectKpiTeacherList(KpiTeacher kpiTeacher);

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
     * 批量删除老师基本信息
     * 
     * @param teacherIds 需要删除的老师基本信息主键集合
     * @return 结果
     */
    public int deleteKpiTeacherByTeacherIds(Long[] teacherIds);

    /**
     * 删除老师基本信息信息
     * 
     * @param teacherId 老师基本信息主键
     * @return 结果
     */
    public int deleteKpiTeacherByTeacherId(Long teacherId);

    /**
     * 导入老师数据
     *
     * @param kpiTeachers 老师数据列表
     * @param operName 操作用户
     * @return 结果
     */
    public  String importTeacher(List<KpiTeacher> kpiTeachers, String operName);
}
