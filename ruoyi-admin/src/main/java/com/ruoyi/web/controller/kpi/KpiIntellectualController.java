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
import com.ruoyi.kpi.domain.KpiIntellectual;
import com.ruoyi.kpi.service.IKpiIntellectualService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 知识产权Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/intellectual")
public class KpiIntellectualController extends BaseController
{
    @Autowired
    private IKpiIntellectualService kpiIntellectualService;

    /**
     * 查询知识产权列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiIntellectual kpiIntellectual)
    {
        startPage();
        List<KpiIntellectual> list = kpiIntellectualService.selectKpiIntellectualList(kpiIntellectual);
        return getDataTable(list);
    }

    /**
     * 查询我的知识产权列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiIntellectual kpiIntellectual)
    {
        kpiIntellectual.setTeacherId(getTeacherId());
        startPage();
        List<KpiIntellectual> list = kpiIntellectualService.selectKpiIntellectualList(kpiIntellectual);
        return getDataTable(list);
    }

    /**
     * 导出知识产权列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:export')")
    @Log(title = "知识产权", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiIntellectual kpiIntellectual)
    {
        List<KpiIntellectual> list = kpiIntellectualService.selectKpiIntellectualList(kpiIntellectual);
        ExcelUtil<KpiIntellectual> util = new ExcelUtil<KpiIntellectual>(KpiIntellectual.class);
        util.exportExcel(response, list, "知识产权数据");
    }

    /**
     * 获取知识产权详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:query')")
    @GetMapping(value = "/{intellectualId}")
    public AjaxResult getInfo(@PathVariable("intellectualId") Long intellectualId)
    {
        return success(kpiIntellectualService.selectKpiIntellectualByIntellectualId(intellectualId));
    }

    /**
     * 新增知识产权
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:add')")
    @Log(title = "知识产权", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiIntellectual kpiIntellectual)
    {
        kpiIntellectual.setTeacherId(getTeacherId());
        return toAjax(kpiIntellectualService.insertKpiIntellectual(kpiIntellectual));
    }

    /**
     * 修改知识产权
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:edit')")
    @Log(title = "知识产权", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiIntellectual kpiIntellectual)
    {
        return toAjax(kpiIntellectualService.updateKpiIntellectual(kpiIntellectual));
    }

    /**
     * 删除知识产权
     */
    @PreAuthorize("@ss.hasPermi('kpi:intellectual:remove')")
    @Log(title = "知识产权", businessType = BusinessType.DELETE)
	@DeleteMapping("/{intellectualIds}")
    public AjaxResult remove(@PathVariable Long[] intellectualIds)
    {
        return toAjax(kpiIntellectualService.deleteKpiIntellectualByIntellectualIds(intellectualIds));
    }
}
