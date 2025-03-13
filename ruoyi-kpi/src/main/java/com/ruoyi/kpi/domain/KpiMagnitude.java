package com.ruoyi.kpi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * KPI量值标准对象 kpi_magnitude
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
public class KpiMagnitude extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 量值id */
    private Long magnitudeId;

    /** 量值名称 */
    @Excel(name = "量值名称")
    private String magnitudeName;

    /** 分类id */
    @Excel(name = "分类id")
    private Long typeId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String typeName;


    /** 基础KPI分数 */
    @Excel(name = "基础KPI分数")
    private Long basicScore;

    /** 额外KPI分数（万元/分） */
    @Excel(name = "额外KPI分数", readConverterExp = "万=元/分")
    private Long extroScore;

    private List<Long> typeIds;

    public List<Long> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Long> typeIds) {
        this.typeIds = typeIds;
    }

    public void setMagnitudeId(Long magnitudeId)
    {
        this.magnitudeId = magnitudeId;
    }

    public Long getMagnitudeId() 
    {
        return magnitudeId;
    }
    public void setMagnitudeName(String magnitudeName) 
    {
        this.magnitudeName = magnitudeName;
    }

    public String getMagnitudeName() 
    {
        return magnitudeName;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setBasicScore(Long basicScore) 
    {
        this.basicScore = basicScore;
    }

    public Long getBasicScore() 
    {
        return basicScore;
    }
    public void setExtroScore(Long extroScore) 
    {
        this.extroScore = extroScore;
    }

    public Long getExtroScore() 
    {
        return extroScore;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("magnitudeId", getMagnitudeId())
            .append("magnitudeName", getMagnitudeName())
            .append("typeId", getTypeId())
            .append("basicScore", getBasicScore())
            .append("extroScore", getExtroScore())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
