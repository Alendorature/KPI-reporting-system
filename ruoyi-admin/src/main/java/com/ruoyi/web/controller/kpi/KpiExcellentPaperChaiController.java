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
import com.ruoyi.kpi.domain.KpiExcellentPaperChai;
import com.ruoyi.kpi.service.IKpiExcellentPaperChaiService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 指导优秀论文与讲座Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/chai")
public class KpiExcellentPaperChaiController extends BaseController
{
    @Autowired
    private IKpiExcellentPaperChaiService kpiExcellentPaperChaiService;

    /**
     * 查询指导优秀论文与讲座列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        startPage();
        List<KpiExcellentPaperChai> list = kpiExcellentPaperChaiService.selectKpiExcellentPaperChaiList(kpiExcellentPaperChai);
        return getDataTable(list);
    }

    /**
     * 查询我的指导优秀论文与讲座列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        kpiExcellentPaperChai.setTeacherId(getTeacherId());
        startPage();
        List<KpiExcellentPaperChai> list = kpiExcellentPaperChaiService.selectKpiExcellentPaperChaiList(kpiExcellentPaperChai);
        return getDataTable(list);
    }

    /**
     * 导出指导优秀论文与讲座列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:export')")
    @Log(title = "指导优秀论文与讲座", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        List<KpiExcellentPaperChai> list = kpiExcellentPaperChaiService.selectKpiExcellentPaperChaiList(kpiExcellentPaperChai);
        ExcelUtil<KpiExcellentPaperChai> util = new ExcelUtil<KpiExcellentPaperChai>(KpiExcellentPaperChai.class);
        util.exportExcel(response, list, "指导优秀论文与讲座数据");
    }

    /**
     * 获取指导优秀论文与讲座详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:query')")
    @GetMapping(value = "/{excellentPaperChairId}")
    public AjaxResult getInfo(@PathVariable("excellentPaperChairId") Long excellentPaperChairId)
    {
        return success(kpiExcellentPaperChaiService.selectKpiExcellentPaperChaiByExcellentPaperChairId(excellentPaperChairId));
    }

    /**
     * 新增指导优秀论文与讲座
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:add')")
    @Log(title = "指导优秀论文与讲座", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        kpiExcellentPaperChai.setTeacherId(getTeacherId());
        return toAjax(kpiExcellentPaperChaiService.insertKpiExcellentPaperChai(kpiExcellentPaperChai));
    }

    /**
     * 修改指导优秀论文与讲座
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:edit')")
    @Log(title = "指导优秀论文与讲座", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiExcellentPaperChai kpiExcellentPaperChai)
    {
        return toAjax(kpiExcellentPaperChaiService.updateKpiExcellentPaperChai(kpiExcellentPaperChai));
    }

    /**
     * 删除指导优秀论文与讲座
     */
    @PreAuthorize("@ss.hasPermi('kpi:chai:remove')")
    @Log(title = "指导优秀论文与讲座", businessType = BusinessType.DELETE)
	@DeleteMapping("/{excellentPaperChairIds}")
    public AjaxResult remove(@PathVariable Long[] excellentPaperChairIds)
    {
        return toAjax(kpiExcellentPaperChaiService.deleteKpiExcellentPaperChaiByExcellentPaperChairIds(excellentPaperChairIds));
    }
}
