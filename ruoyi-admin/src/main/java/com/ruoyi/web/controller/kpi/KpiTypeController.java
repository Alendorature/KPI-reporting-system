package com.ruoyi.web.controller.kpi;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kpi.domain.KpiType;
import com.ruoyi.kpi.service.IKpiTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * kpi分类Controller
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
@RestController
@RequestMapping("/kpi/type")
public class KpiTypeController extends BaseController
{
    @Autowired
    private IKpiTypeService kpiTypeService;

    /**
     * 查询kpi分类列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiType kpiType)
    {
        startPage();
        List<KpiType> list = kpiTypeService.selectKpiTypeList(kpiType);
        return getDataTable(list);
    }

    /**
     * 导出kpi分类列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:type:export')")
    @Log(title = "kpi分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiType kpiType)
    {
        List<KpiType> list = kpiTypeService.selectKpiTypeList(kpiType);
        ExcelUtil<KpiType> util = new ExcelUtil<KpiType>(KpiType.class);
        util.exportExcel(response, list, "kpi分类数据");
    }

    /**
     * 获取kpi分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(kpiTypeService.selectKpiTypeByTypeId(typeId));
    }

    /**
     * 新增kpi分类
     */
    @PreAuthorize("@ss.hasPermi('kpi:type:add')")
    @Log(title = "kpi分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiType kpiType)
    {
        return toAjax(kpiTypeService.insertKpiType(kpiType));
    }

    /**
     * 修改kpi分类
     */
    @PreAuthorize("@ss.hasPermi('kpi:type:edit')")
    @Log(title = "kpi分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiType kpiType)
    {
        return toAjax(kpiTypeService.updateKpiType(kpiType));
    }

    /**
     * 删除kpi分类
     */
    @PreAuthorize("@ss.hasPermi('kpi:type:remove')")
    @Log(title = "kpi分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(kpiTypeService.deleteKpiTypeByTypeIds(typeIds));
    }
}
