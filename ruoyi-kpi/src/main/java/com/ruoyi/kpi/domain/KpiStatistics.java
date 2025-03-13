package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * kpi统计
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public class KpiStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 老师id */
    private Long teacherId;

    /** 老师姓名 */
    @Excel(name = "老师姓名")
    private String teacherName;

    /** 老师头像 */
    private String teacherAvatar;

    /** 老师电话 */
    @Excel(name = "老师电话")
    private String teacherTel;

    /** 非教学类科研论文得分 */
    @Excel(name = "非教学类科研论文得分")
    private BigDecimal sumPntProjectScore;

    /** 纵向横向项目得分 */
    @Excel(name = "纵向横向项目得分")
    private BigDecimal sumProProjectScore;

    /** 知识产权得分 */
    @Excel(name = "知识产权得分")
    private BigDecimal sumInteProjectScore;

    /** 行业标准得分 */
    @Excel(name = "行业标准得分")
    private BigDecimal sumStaProjectScore;

    /** 奖项得分 */
    @Excel(name = "奖项得分")
    private BigDecimal sumAwaProjectScore;

    /** 优秀论文得分 */
    @Excel(name = "优秀论文得分")
    private BigDecimal sumEpcProjectScore;

    /** 国际组织得分 */
    @Excel(name = "国际组织得分")
    private BigDecimal sumOrgProjectScore;

    /** 科技成果转化得分 */
    @Excel(name = "科技成果转化得分")
    private BigDecimal sumSciProjectScore;

    /** 合计 */
    @Excel(name = "合计")
    private BigDecimal sumTotal;

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

    public BigDecimal getSumPntProjectScore() {
        return sumPntProjectScore;
    }

    public void setSumPntProjectScore(BigDecimal sumPntProjectScore) {
        this.sumPntProjectScore = sumPntProjectScore;
    }

    public BigDecimal getSumProProjectScore() {
        return sumProProjectScore;
    }

    public void setSumProProjectScore(BigDecimal sumProProjectScore) {
        this.sumProProjectScore = sumProProjectScore;
    }

    public BigDecimal getSumInteProjectScore() {
        return sumInteProjectScore;
    }

    public void setSumInteProjectScore(BigDecimal sumInteProjectScore) {
        this.sumInteProjectScore = sumInteProjectScore;
    }

    public BigDecimal getSumStaProjectScore() {
        return sumStaProjectScore;
    }

    public void setSumStaProjectScore(BigDecimal sumStaProjectScore) {
        this.sumStaProjectScore = sumStaProjectScore;
    }

    public BigDecimal getSumAwaProjectScore() {
        return sumAwaProjectScore;
    }

    public void setSumAwaProjectScore(BigDecimal sumAwaProjectScore) {
        this.sumAwaProjectScore = sumAwaProjectScore;
    }

    public BigDecimal getSumEpcProjectScore() {
        return sumEpcProjectScore;
    }

    public void setSumEpcProjectScore(BigDecimal sumEpcProjectScore) {
        this.sumEpcProjectScore = sumEpcProjectScore;
    }

    public BigDecimal getSumOrgProjectScore() {
        return sumOrgProjectScore;
    }

    public void setSumOrgProjectScore(BigDecimal sumOrgProjectScore) {
        this.sumOrgProjectScore = sumOrgProjectScore;
    }

    public BigDecimal getSumSciProjectScore() {
        return sumSciProjectScore;
    }

    public void setSumSciProjectScore(BigDecimal sumSciProjectScore) {
        this.sumSciProjectScore = sumSciProjectScore;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }
}
