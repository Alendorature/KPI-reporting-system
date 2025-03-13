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
import com.ruoyi.kpi.domain.KpiScience;
import com.ruoyi.kpi.service.IKpiScienceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 科技成果Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/science")
public class KpiScienceController extends BaseController
{
    @Autowired
    private IKpiScienceService kpiScienceService;

    /**
     * 查询科技成果列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiScience kpiScience)
    {
        startPage();
        List<KpiScience> list = kpiScienceService.selectKpiScienceList(kpiScience);
        return getDataTable(list);
    }

    /**
     * 查询我的科技成果列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiScience kpiScience)
    {
        kpiScience.setTeacherId(getTeacherId());
        startPage();
        List<KpiScience> list = kpiScienceService.selectKpiScienceList(kpiScience);
        return getDataTable(list);
    }

    /**
     * 导出科技成果列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:export')")
    @Log(title = "科技成果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiScience kpiScience)
    {
        List<KpiScience> list = kpiScienceService.selectKpiScienceList(kpiScience);
        ExcelUtil<KpiScience> util = new ExcelUtil<KpiScience>(KpiScience.class);
        util.exportExcel(response, list, "科技成果数据");
    }

    /**
     * 获取科技成果详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:query')")
    @GetMapping(value = "/{scienceId}")
    public AjaxResult getInfo(@PathVariable("scienceId") Long scienceId)
    {
        return success(kpiScienceService.selectKpiScienceByScienceId(scienceId));
    }

    /**
     * 新增科技成果
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:add')")
    @Log(title = "科技成果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiScience kpiScience)
    {
        kpiScience.setTeacherId(getTeacherId());
        return toAjax(kpiScienceService.insertKpiScience(kpiScience));
    }

    /**
     * 修改科技成果
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:edit')")
    @Log(title = "科技成果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiScience kpiScience)
    {
        return toAjax(kpiScienceService.updateKpiScience(kpiScience));
    }

    /**
     * 删除科技成果
     */
    @PreAuthorize("@ss.hasPermi('kpi:science:remove')")
    @Log(title = "科技成果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scienceIds}")
    public AjaxResult remove(@PathVariable Long[] scienceIds)
    {
        return toAjax(kpiScienceService.deleteKpiScienceByScienceIds(scienceIds));
    }
}
