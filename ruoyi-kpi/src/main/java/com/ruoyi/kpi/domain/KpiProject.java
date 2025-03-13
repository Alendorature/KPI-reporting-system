package com.ruoyi.kpi.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息对象 kpi_project
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
public class KpiProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long projectId;

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

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目来源 */
    @Excel(name = "项目来源")
    private String projectSource;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectNumber;

    /** 财务资金账户编号 */
    @Excel(name = "财务资金账户编号")
    private String financeAccountNumber;

    /** 项目起始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectStartTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectEndTime;

    /** 到账经费 */
    @Excel(name = "到账经费")
    private BigDecimal accountExpenditure;

    /** 外拨经费 */
    @Excel(name = "外拨经费")
    private BigDecimal outExpenditure;

    /** 实际经费 */
    @Excel(name = "实际经费")
    private BigDecimal actualExpenditure;

    /** 是否首次拨款（0否 1是） */
    @Excel(name = "是否首次拨款", readConverterExp = "0=否,1=是")
    private String isFirstAppropriation;

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

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectSource(String projectSource) 
    {
        this.projectSource = projectSource;
    }

    public String getProjectSource() 
    {
        return projectSource;
    }
    public void setProjectNumber(String projectNumber) 
    {
        this.projectNumber = projectNumber;
    }

    public String getProjectNumber() 
    {
        return projectNumber;
    }
    public void setFinanceAccountNumber(String financeAccountNumber) 
    {
        this.financeAccountNumber = financeAccountNumber;
    }

    public String getFinanceAccountNumber() 
    {
        return financeAccountNumber;
    }
    public void setProjectStartTime(Date projectStartTime) 
    {
        this.projectStartTime = projectStartTime;
    }

    public Date getProjectStartTime() 
    {
        return projectStartTime;
    }
    public void setProjectEndTime(Date projectEndTime) 
    {
        this.projectEndTime = projectEndTime;
    }

    public Date getProjectEndTime() 
    {
        return projectEndTime;
    }
    public void setAccountExpenditure(BigDecimal accountExpenditure) 
    {
        this.accountExpenditure = accountExpenditure;
    }

    public BigDecimal getAccountExpenditure() 
    {
        return accountExpenditure;
    }
    public void setOutExpenditure(BigDecimal outExpenditure) 
    {
        this.outExpenditure = outExpenditure;
    }

    public BigDecimal getOutExpenditure() 
    {
        return outExpenditure;
    }
    public void setActualExpenditure(BigDecimal actualExpenditure) 
    {
        this.actualExpenditure = actualExpenditure;
    }

    public BigDecimal getActualExpenditure() 
    {
        return actualExpenditure;
    }
    public void setIsFirstAppropriation(String isFirstAppropriation) 
    {
        this.isFirstAppropriation = isFirstAppropriation;
    }

    public String getIsFirstAppropriation() 
    {
        return isFirstAppropriation;
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
            .append("projectId", getProjectId())
            .append("teacherId", getTeacherId())
            .append("projectName", getProjectName())
            .append("projectSource", getProjectSource())
            .append("projectNumber", getProjectNumber())
            .append("financeAccountNumber", getFinanceAccountNumber())
            .append("projectStartTime", getProjectStartTime())
            .append("projectEndTime", getProjectEndTime())
            .append("accountExpenditure", getAccountExpenditure())
            .append("outExpenditure", getOutExpenditure())
            .append("actualExpenditure", getActualExpenditure())
            .append("isFirstAppropriation", getIsFirstAppropriation())
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
