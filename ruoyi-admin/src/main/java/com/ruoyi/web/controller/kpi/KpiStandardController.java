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
import com.ruoyi.kpi.domain.KpiStandard;
import com.ruoyi.kpi.service.IKpiStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标准及平台Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/standard")
public class KpiStandardController extends BaseController
{
    @Autowired
    private IKpiStandardService kpiStandardService;

    /**
     * 查询标准及平台列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiStandard kpiStandard)
    {
        startPage();
        List<KpiStandard> list = kpiStandardService.selectKpiStandardList(kpiStandard);
        return getDataTable(list);
    }

    /**
     * 查询我的标准及平台列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiStandard kpiStandard)
    {
        kpiStandard.setTeacherId(getTeacherId());
        startPage();
        List<KpiStandard> list = kpiStandardService.selectKpiStandardList(kpiStandard);
        return getDataTable(list);
    }

    /**
     * 导出标准及平台列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:export')")
    @Log(title = "标准及平台", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiStandard kpiStandard)
    {
        List<KpiStandard> list = kpiStandardService.selectKpiStandardList(kpiStandard);
        ExcelUtil<KpiStandard> util = new ExcelUtil<KpiStandard>(KpiStandard.class);
        util.exportExcel(response, list, "标准及平台数据");
    }

    /**
     * 获取标准及平台详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:query')")
    @GetMapping(value = "/{standardId}")
    public AjaxResult getInfo(@PathVariable("standardId") Long standardId)
    {
        return success(kpiStandardService.selectKpiStandardByStandardId(standardId));
    }

    /**
     * 新增标准及平台
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:add')")
    @Log(title = "标准及平台", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiStandard kpiStandard)
    {
        kpiStandard.setTeacherId(getTeacherId());
        return toAjax(kpiStandardService.insertKpiStandard(kpiStandard));
    }

    /**
     * 修改标准及平台
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:edit')")
    @Log(title = "标准及平台", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiStandard kpiStandard)
    {
        return toAjax(kpiStandardService.updateKpiStandard(kpiStandard));
    }

    /**
     * 删除标准及平台
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:remove')")
    @Log(title = "标准及平台", businessType = BusinessType.DELETE)
	@DeleteMapping("/{standardIds}")
    public AjaxResult remove(@PathVariable Long[] standardIds)
    {
        return toAjax(kpiStandardService.deleteKpiStandardByStandardIds(standardIds));
    }
}
