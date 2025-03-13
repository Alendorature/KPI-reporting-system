package com.ruoyi.kpi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 老师基本信息对象 kpi_teacher
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public class KpiTeacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 老师id */
    private Long teacherId;

    /** 老师姓名 */
    @Excel(name = "老师姓名（必填）")
    private String teacherName;

    /** 老师职称 */
    @Excel(name = "老师职称（必填）")
    private String teacherCareer;

    /** 老师所在系 */
    @Excel(name = "老师所在系编号（必填）")
    private Long teacherDepartment;

    /** 老师所在系 */
    @Excel(name = "老师所在系",type = Excel.Type.EXPORT)
    private String deptName;

    /** 老师头像 */
    @Excel(name = "老师头像",type = Excel.Type.EXPORT)
    private String teacherAvatar;

    /** 老师电话 */
    @Excel(name = "老师电话（必填）")
    private String teacherTel;

    /** 老师邮箱 */
    @Excel(name = "老师邮箱")
    private String teacherMail;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setTeacherId(Long teacherId)
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setTeacherCareer(String teacherCareer) 
    {
        this.teacherCareer = teacherCareer;
    }

    public String getTeacherCareer() 
    {
        return teacherCareer;
    }
    public void setTeacherDepartment(Long teacherDepartment)
    {
        this.teacherDepartment = teacherDepartment;
    }

    public Long getTeacherDepartment()
    {
        return teacherDepartment;
    }
    public void setTeacherTel(String teacherTel)
    {
        this.teacherTel = teacherTel;
    }

    public String getTeacherTel()
    {
        return teacherTel;
    }
    public void setTeacherMail(String teacherMail) 
    {
        this.teacherMail = teacherMail;
    }

    public String getTeacherMail() 
    {
        return teacherMail;
    }

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("teacherCareer", getTeacherCareer())
            .append("teacherDepartment", getTeacherDepartment())
            .append("teacherTel", getTeacherTel())
            .append("teacherMail", getTeacherMail())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
