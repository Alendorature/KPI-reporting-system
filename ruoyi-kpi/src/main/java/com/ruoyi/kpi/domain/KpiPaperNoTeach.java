package com.ruoyi.kpi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 非教学类科研论文对象 kpi_paper_no_teach
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public class KpiPaperNoTeach extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 非教学类科研论文id */
    private Long paperNoTeachId;

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

    /** 论文名称 */
    @Excel(name = "论文名称")
    private String paperName;

    /** 期刊/会议名称 */
    @Excel(name = "期刊/会议名称")
    private String periodicalName;

    /** 发表日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发表日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 期卷号 */
    @Excel(name = "期卷号")
    private String issueNumber;

    /** ISSN号 */
    @Excel(name = "ISSN号")
    private String issnNumber;

    /** 起止页码 */
    @Excel(name = "起止页码")
    private String startEndPageNumber;

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

    public void setPaperNoTeachId(Long paperNoTeachId)
    {
        this.paperNoTeachId = paperNoTeachId;
    }

    public Long getPaperNoTeachId() 
    {
        return paperNoTeachId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setPaperName(String paperName) 
    {
        this.paperName = paperName;
    }

    public String getPaperName() 
    {
        return paperName;
    }
    public void setPeriodicalName(String periodicalName) 
    {
        this.periodicalName = periodicalName;
    }

    public String getPeriodicalName() 
    {
        return periodicalName;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }
    public void setIssueNumber(String issueNumber) 
    {
        this.issueNumber = issueNumber;
    }

    public String getIssueNumber() 
    {
        return issueNumber;
    }
    public void setIssnNumber(String issnNumber) 
    {
        this.issnNumber = issnNumber;
    }

    public String getIssnNumber() 
    {
        return issnNumber;
    }
    public void setStartEndPageNumber(String startEndPageNumber) 
    {
        this.startEndPageNumber = startEndPageNumber;
    }

    public String getStartEndPageNumber() 
    {
        return startEndPageNumber;
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
            .append("paperNoTeachId", getPaperNoTeachId())
            .append("teacherId", getTeacherId())
            .append("paperName", getPaperName())
            .append("periodicalName", getPeriodicalName())
            .append("publishTime", getPublishTime())
            .append("issueNumber", getIssueNumber())
            .append("issnNumber", getIssnNumber())
            .append("startEndPageNumber", getStartEndPageNumber())
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
