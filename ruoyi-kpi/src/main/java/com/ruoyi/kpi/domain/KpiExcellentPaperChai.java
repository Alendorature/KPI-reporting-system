package com.ruoyi.kpi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 指导优秀论文与讲座对象 kpi_excellent_paper_chai
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public class KpiExcellentPaperChai extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 指导优秀论文与讲座id */
    private Long excellentPaperChairId;

    /** 教师id */
    @Excel(name = "教师id")
    private Long teacherId;
    /** 老师姓名 */
    @Excel(name = "老师姓名")
    private String teacherName;

    /** 老师头像 */
    private String teacherAvatar;

    /** 老师电话 */
    @Excel(name = "老师电话")
    private String teacherTel;

    /** 论文/讲座名称 */
    @Excel(name = "论文/讲座名称")
    private String excellentPaperChaiName;

    /** 批准单位 */
    @Excel(name = "批准单位")
    private String approvingUnit;

    /** 批准时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "批准时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvingTime;

    /** 排名 */
    @Excel(name = "排名")
    private Long ranking;

    /** 量值id */
    @Excel(name = "量值id")
    private Long magnitudeId;

    /** 量值名称 */
    @Excel(name = "量值名称")
    private String magnitudeName;

    /** 项目得分 */
    @Excel(name = "项目得分")
    private Long projectScore;

    /** kpi年份 */
    @Excel(name = "kpi年份")
    private String kpiYear;

    /** 审核状态（0待审核 1审核通过 2审核驳回） */
    @Excel(name = "审核状态", readConverterExp = "0=待审核,1=审核通过,2=审核驳回")
    private String auditState;

    /** 驳回原因 */
    @Excel(name = "驳回原因")
    private String rejectCause;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    public String getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

    public String getMagnitudeName() {
        return magnitudeName;
    }

    public void setMagnitudeName(String magnitudeName) {
        this.magnitudeName = magnitudeName;
    }

    public void setExcellentPaperChairId(Long excellentPaperChairId)
    {
        this.excellentPaperChairId = excellentPaperChairId;
    }

    public Long getExcellentPaperChairId() 
    {
        return excellentPaperChairId;
    }
    public void setExcellentPaperChaiName(String excellentPaperChaiName) 
    {
        this.excellentPaperChaiName = excellentPaperChaiName;
    }

    public String getExcellentPaperChaiName() 
    {
        return excellentPaperChaiName;
    }
    public void setApprovingUnit(String approvingUnit) 
    {
        this.approvingUnit = approvingUnit;
    }

    public String getApprovingUnit() 
    {
        return approvingUnit;
    }
    public void setApprovingTime(Date approvingTime) 
    {
        this.approvingTime = approvingTime;
    }

    public Date getApprovingTime() 
    {
        return approvingTime;
    }
    public void setRanking(Long ranking) 
    {
        this.ranking = ranking;
    }

    public Long getRanking() 
    {
        return ranking;
    }
    public void setMagnitudeId(Long magnitudeId) 
    {
        this.magnitudeId = magnitudeId;
    }

    public Long getMagnitudeId() 
    {
        return magnitudeId;
    }
    public void setProjectScore(Long projectScore) 
    {
        this.projectScore = projectScore;
    }

    public Long getProjectScore() 
    {
        return projectScore;
    }
    public void setKpiYear(String kpiYear) 
    {
        this.kpiYear = kpiYear;
    }

    public String getKpiYear() 
    {
        return kpiYear;
    }
    public void setAuditState(String auditState) 
    {
        this.auditState = auditState;
    }

    public String getAuditState() 
    {
        return auditState;
    }
    public void setRejectCause(String rejectCause) 
    {
        this.rejectCause = rejectCause;
    }

    public String getRejectCause() 
    {
        return rejectCause;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("excellentPaperChairId", getExcellentPaperChairId())
            .append("excellentPaperChaiName", getExcellentPaperChaiName())
            .append("approvingUnit", getApprovingUnit())
            .append("approvingTime", getApprovingTime())
            .append("ranking", getRanking())
            .append("magnitudeId", getMagnitudeId())
            .append("projectScore", getProjectScore())
            .append("kpiYear", getKpiYear())
            .append("auditState", getAuditState())
            .append("rejectCause", getRejectCause())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
