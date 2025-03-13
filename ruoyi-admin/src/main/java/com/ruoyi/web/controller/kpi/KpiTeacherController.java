package com.ruoyi.web.controller.kpi;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.kpi.domain.KpiStatistics;
import com.ruoyi.system.service.ISysDeptService;
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
import com.ruoyi.kpi.domain.KpiTeacher;
import com.ruoyi.kpi.service.IKpiTeacherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 老师基本信息Controller
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
@RestController
@RequestMapping("/kpi/teacher")
public class KpiTeacherController extends BaseController
{
    @Autowired
    private IKpiTeacherService kpiTeacherService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询老师基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiTeacher kpiTeacher)
    {
        startPage();
        List<KpiTeacher> list = kpiTeacherService.selectKpiTeacherList(kpiTeacher);
        return getDataTable(list);
    }

    /**
     * 查询老师基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:list')")
    @GetMapping("/statistics/list")
    public TableDataInfo statisticsList(String kpiYear)
    {
        if(StringUtils.isEmpty(kpiYear)){
            List<KpiStatistics> kpiStatistics =new ArrayList<KpiStatistics>();
            return getDataTable(kpiStatistics);
        }
        startPage();
        List<KpiStatistics> kpiStatistics = kpiTeacherService.selectKpiList(kpiYear);
        return getDataTable(kpiStatistics);
    }

    @GetMapping("/data")
    public AjaxResult data()
    {
        SysDept sysDept = new SysDept();
        sysDept.setParentId(100l);
        List<SysDept> sysDepts = sysDeptService.selectDeptList(sysDept);
        return AjaxResult.success(sysDepts);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<KpiTeacher> util = new ExcelUtil<KpiTeacher>(KpiTeacher.class);
        util.importTemplateExcel(response, "用户数据");
    }
    @Log(title = "老师管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('kpi:teacher:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<KpiTeacher> util = new ExcelUtil<KpiTeacher>(KpiTeacher.class);
        List<KpiTeacher> kpiTeachers = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = kpiTeacherService.importTeacher(kpiTeachers, operName);
        return success(message);
    }

    /**
     * 导出老师基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:export')")
    @Log(title = "老师基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiTeacher kpiTeacher)
    {
        List<KpiTeacher> list = kpiTeacherService.selectKpiTeacherList(kpiTeacher);
        ExcelUtil<KpiTeacher> util = new ExcelUtil<KpiTeacher>(KpiTeacher.class);
        util.exportExcel(response, list, "老师基本信息数据");
    }

    /**
     * 导出kpi
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:export')")
    @Log(title = "导出kpi统计", businessType = BusinessType.EXPORT)
    @PostMapping("/statistics/export")
    public void statisticsExport(HttpServletResponse response, String kpiYear)
    {
        List<KpiStatistics> kpiStatistics = kpiTeacherService.selectKpiList(kpiYear);
        ExcelUtil<KpiStatistics> util = new ExcelUtil<KpiStatistics>(KpiStatistics.class);
        util.exportExcel(response, kpiStatistics, kpiYear+"年kpi统计");
    }

    /**
     * 获取老师基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:query')")
    @GetMapping(value = "/{teacherId}")
    public AjaxResult getInfo(@PathVariable("teacherId") Long teacherId)
    {

        return success(kpiTeacherService.selectKpiTeacherByTeacherId(teacherId));
    }

    /**
     * 新增老师基本信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:add')")
    @Log(title = "老师基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiTeacher kpiTeacher)
    {
        if (StringUtils.isNotEmpty(kpiTeacher.getTeacherTel()) && !kpiTeacherService.checkPhoneUnique(kpiTeacher))
        {
            return error("新增老师'" + kpiTeacher.getTeacherName() + "'失败，手机号码已存在");
        }
        return toAjax(kpiTeacherService.insertKpiTeacher(kpiTeacher));
    }

    /**
     * 修改老师基本信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:edit')")
    @Log(title = "老师基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiTeacher kpiTeacher)
    {
        if (StringUtils.isNotEmpty(kpiTeacher.getTeacherTel()) && !kpiTeacherService.checkPhoneUnique(kpiTeacher))
        {
            return error("新增老师'" + kpiTeacher.getTeacherName() + "'失败，手机号码已存在");
        }
        return toAjax(kpiTeacherService.updateKpiTeacher(kpiTeacher));
    }

    /**
     * 删除老师基本信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:teacher:remove')")
    @Log(title = "老师基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teacherIds}")
    public AjaxResult remove(@PathVariable Long[] teacherIds)
    {
        return toAjax(kpiTeacherService.deleteKpiTeacherByTeacherIds(teacherIds));
    }
}
