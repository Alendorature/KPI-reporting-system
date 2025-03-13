package com.ruoyi.kpi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖项信息对象 kpi_awards
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public class KpiAwards extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 奖项id */
    private Long awardsId;

    /** 奖项名称 */
    @Excel(name = "奖项名称")
    private String awardsName;

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

    /** 获得日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "获得日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acquireTime;

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

    public void setAwardsId(Long awardsId)
    {
        this.awardsId = awardsId;
    }

    public Long getAwardsId() 
    {
        return awardsId;
    }
    public void setAwardsName(String awardsName) 
    {
        this.awardsName = awardsName;
    }

    public String getAwardsName() 
    {
        return awardsName;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setAcquireTime(Date acquireTime) 
    {
        this.acquireTime = acquireTime;
    }

    public Date getAcquireTime() 
    {
        return acquireTime;
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
            .append("awardsId", getAwardsId())
            .append("awardsName", getAwardsName())
            .append("teacherId", getTeacherId())
            .append("acquireTime", getAcquireTime())
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
