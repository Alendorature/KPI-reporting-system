package com.ruoyi.kpi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * kpi分类对象 kpi_type
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public class KpiType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** kpi分类id */
    private Long typeId;

    /** kpi分类名称 */
    @Excel(name = "kpi分类名称")
    private String typeName;

    /** kpi分类状态（0正常 1停用） */
    @Excel(name = "kpi分类状态", readConverterExp = "0=正常,1=停用")
    private String typeStatus;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setTypeStatus(String typeStatus) 
    {
        this.typeStatus = typeStatus;
    }

    public String getTypeStatus() 
    {
        return typeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("typeStatus", getTypeStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
