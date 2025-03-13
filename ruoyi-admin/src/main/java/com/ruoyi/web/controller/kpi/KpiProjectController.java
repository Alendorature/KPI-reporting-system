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
import com.ruoyi.kpi.domain.KpiProject;
import com.ruoyi.kpi.service.IKpiProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目信息Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/project")
public class KpiProjectController extends BaseController
{
    @Autowired
    private IKpiProjectService kpiProjectService;

    /**
     * 查询项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiProject kpiProject)
    {
        startPage();
        List<KpiProject> list = kpiProjectService.selectKpiProjectList(kpiProject);
        return getDataTable(list);
    }


    /**
     * 查询我的项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiProject kpiProject)
    {
        kpiProject.setTeacherId(getTeacherId());
        startPage();
        List<KpiProject> list = kpiProjectService.selectKpiProjectList(kpiProject);
        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:export')")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiProject kpiProject)
    {
        List<KpiProject> list = kpiProjectService.selectKpiProjectList(kpiProject);
        ExcelUtil<KpiProject> util = new ExcelUtil<KpiProject>(KpiProject.class);
        util.exportExcel(response, list, "项目信息数据");
    }

    /**
     * 获取项目信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(kpiProjectService.selectKpiProjectByProjectId(projectId));
    }

    /**
     * 新增项目信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:add')")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiProject kpiProject)
    {
        kpiProject.setTeacherId(getTeacherId());
        return toAjax(kpiProjectService.insertKpiProject(kpiProject));
    }

    /**
     * 修改项目信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:edit')")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiProject kpiProject)
    {
        return toAjax(kpiProjectService.updateKpiProject(kpiProject));
    }

    /**
     * 删除项目信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:project:remove')")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(kpiProjectService.deleteKpiProjectByProjectIds(projectIds));
    }
}
