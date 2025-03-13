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
import com.ruoyi.kpi.domain.KpiAwards;
import com.ruoyi.kpi.service.IKpiAwardsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖项信息Controller
 * 
 * @author YuanPan
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/kpi/awards")
public class KpiAwardsController extends BaseController
{
    @Autowired
    private IKpiAwardsService kpiAwardsService;

    /**
     * 查询奖项信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiAwards kpiAwards)
    {
        startPage();
        List<KpiAwards> list = kpiAwardsService.selectKpiAwardsList(kpiAwards);
        return getDataTable(list);
    }

    /**
     * 查询我的奖项信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:list')")
    @GetMapping("/myList")
    public TableDataInfo myList(KpiAwards kpiAwards)
    {
        kpiAwards.setTeacherId(getTeacherId());
        startPage();
        List<KpiAwards> list = kpiAwardsService.selectKpiAwardsList(kpiAwards);
        return getDataTable(list);
    }

    /**
     * 导出奖项信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:export')")
    @Log(title = "奖项信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiAwards kpiAwards)
    {
        List<KpiAwards> list = kpiAwardsService.selectKpiAwardsList(kpiAwards);
        ExcelUtil<KpiAwards> util = new ExcelUtil<KpiAwards>(KpiAwards.class);
        util.exportExcel(response, list, "奖项信息数据");
    }

    /**
     * 获取奖项信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:query')")
    @GetMapping(value = "/{awardsId}")
    public AjaxResult getInfo(@PathVariable("awardsId") Long awardsId)
    {
        return success(kpiAwardsService.selectKpiAwardsByAwardsId(awardsId));
    }

    /**
     * 新增奖项信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:add')")
    @Log(title = "奖项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiAwards kpiAwards)
    {
        kpiAwards.setTeacherId(getTeacherId());
        return toAjax(kpiAwardsService.insertKpiAwards(kpiAwards));
    }

    /**
     * 修改奖项信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:edit')")
    @Log(title = "奖项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiAwards kpiAwards)
    {
        return toAjax(kpiAwardsService.updateKpiAwards(kpiAwards));
    }

    /**
     * 删除奖项信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:awards:remove')")
    @Log(title = "奖项信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{awardsIds}")
    public AjaxResult remove(@PathVariable Long[] awardsIds)
    {
        return toAjax(kpiAwardsService.deleteKpiAwardsByAwardsIds(awardsIds));
    }
}
