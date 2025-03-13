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
import com.ruoyi.kpi.domain.KpiPaperNoTeach;
import com.ruoyi.kpi.service.IKpiPaperNoTeachService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 非教学类科研论文Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/paperNoTeach")
public class KpiPaperNoTeachController extends BaseController
{
    @Autowired
    private IKpiPaperNoTeachService kpiPaperNoTeachService;

    /**
     * 查询非教学类科研论文列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiPaperNoTeach kpiPaperNoTeach)
    {
        startPage();
        List<KpiPaperNoTeach> list = kpiPaperNoTeachService.selectKpiPaperNoTeachList(kpiPaperNoTeach);
        return getDataTable(list);
    }


    /**
     * 查询非教学类科研论文列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiPaperNoTeach kpiPaperNoTeach)
    {
        kpiPaperNoTeach.setTeacherId(getTeacherId());
        startPage();
        List<KpiPaperNoTeach> list = kpiPaperNoTeachService.selectKpiPaperNoTeachList(kpiPaperNoTeach);
        return getDataTable(list);
    }

    /**
     * 导出非教学类科研论文列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:export')")
    @Log(title = "非教学类科研论文", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiPaperNoTeach kpiPaperNoTeach)
    {
        List<KpiPaperNoTeach> list = kpiPaperNoTeachService.selectKpiPaperNoTeachList(kpiPaperNoTeach);
        ExcelUtil<KpiPaperNoTeach> util = new ExcelUtil<KpiPaperNoTeach>(KpiPaperNoTeach.class);
        util.exportExcel(response, list, "非教学类科研论文数据");
    }

    /**
     * 获取非教学类科研论文详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:query')")
    @GetMapping(value = "/{paperNoTeachId}")
    public AjaxResult getInfo(@PathVariable("paperNoTeachId") Long paperNoTeachId)
    {
        return success(kpiPaperNoTeachService.selectKpiPaperNoTeachByPaperNoTeachId(paperNoTeachId));
    }

    /**
     * 新增非教学类科研论文
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:add')")
    @Log(title = "非教学类科研论文", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiPaperNoTeach kpiPaperNoTeach)
    {
        kpiPaperNoTeach.setTeacherId(getTeacherId());
        return toAjax(kpiPaperNoTeachService.insertKpiPaperNoTeach(kpiPaperNoTeach));
    }

    /**
     * 修改非教学类科研论文
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:edit')")
    @Log(title = "非教学类科研论文", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiPaperNoTeach kpiPaperNoTeach)
    {
        return toAjax(kpiPaperNoTeachService.updateKpiPaperNoTeach(kpiPaperNoTeach));
    }

    /**
     * 删除非教学类科研论文
     */
    @PreAuthorize("@ss.hasPermi('kpi:paperNoTeach:remove')")
    @Log(title = "非教学类科研论文", businessType = BusinessType.DELETE)
	@DeleteMapping("/{paperNoTeachIds}")
    public AjaxResult remove(@PathVariable Long[] paperNoTeachIds)
    {
        return toAjax(kpiPaperNoTeachService.deleteKpiPaperNoTeachByPaperNoTeachIds(paperNoTeachIds));
    }
}
