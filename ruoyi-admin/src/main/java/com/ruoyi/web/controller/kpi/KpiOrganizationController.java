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
import com.ruoyi.kpi.domain.KpiOrganization;
import com.ruoyi.kpi.service.IKpiOrganizationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国际学术组织任职Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/organization")
public class KpiOrganizationController extends BaseController
{
    @Autowired
    private IKpiOrganizationService kpiOrganizationService;

    /**
     * 查询国际学术组织任职列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiOrganization kpiOrganization)
    {
        startPage();
        List<KpiOrganization> list = kpiOrganizationService.selectKpiOrganizationList(kpiOrganization);
        return getDataTable(list);
    }

    /**
     * 查询我的国际学术组织任职列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiOrganization kpiOrganization)
    {
        kpiOrganization.setTeacherId(getTeacherId());
        startPage();
        List<KpiOrganization> list = kpiOrganizationService.selectKpiOrganizationList(kpiOrganization);
        return getDataTable(list);
    }


    /**
     * 导出国际学术组织任职列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:export')")
    @Log(title = "国际学术组织任职", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiOrganization kpiOrganization)
    {
        List<KpiOrganization> list = kpiOrganizationService.selectKpiOrganizationList(kpiOrganization);
        ExcelUtil<KpiOrganization> util = new ExcelUtil<KpiOrganization>(KpiOrganization.class);
        util.exportExcel(response, list, "国际学术组织任职数据");
    }

    /**
     * 获取国际学术组织任职详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:query')")
    @GetMapping(value = "/{organizationId}")
    public AjaxResult getInfo(@PathVariable("organizationId") Long organizationId)
    {
        return success(kpiOrganizationService.selectKpiOrganizationByOrganizationId(organizationId));
    }

    /**
     * 新增国际学术组织任职
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:add')")
    @Log(title = "国际学术组织任职", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiOrganization kpiOrganization)
    {
        kpiOrganization.setTeacherId(getTeacherId());
        return toAjax(kpiOrganizationService.insertKpiOrganization(kpiOrganization));
    }

    /**
     * 修改国际学术组织任职
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:edit')")
    @Log(title = "国际学术组织任职", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiOrganization kpiOrganization)
    {
        return toAjax(kpiOrganizationService.updateKpiOrganization(kpiOrganization));
    }

    /**
     * 删除国际学术组织任职
     */
    @PreAuthorize("@ss.hasPermi('kpi:organization:remove')")
    @Log(title = "国际学术组织任职", businessType = BusinessType.DELETE)
	@DeleteMapping("/{organizationIds}")
    public AjaxResult remove(@PathVariable Long[] organizationIds)
    {
        return toAjax(kpiOrganizationService.deleteKpiOrganizationByOrganizationIds(organizationIds));
    }
}
