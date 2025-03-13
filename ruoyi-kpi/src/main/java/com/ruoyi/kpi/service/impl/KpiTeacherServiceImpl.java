package com.ruoyi.kpi.service.impl;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.kpi.domain.KpiStatistics;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiTeacherMapper;
import com.ruoyi.kpi.domain.KpiTeacher;
import com.ruoyi.kpi.service.IKpiTeacherService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

/**
 * 老师基本信息Service业务层处理
 * 
 * @author YuanPan
 * @date 2024-04-21
 */
@Service
public class KpiTeacherServiceImpl implements IKpiTeacherService 
{
    private static final Logger log = LoggerFactory.getLogger(KpiTeacherServiceImpl.class);
    @Autowired
    private ISysConfigService configService;
    @Autowired
    private ISysUserService userService;

    @Autowired
    private KpiTeacherMapper kpiTeacherMapper;

    @Autowired
    protected Validator validator;

    /**
     * 查询老师基本信息
     * 
     * @param teacherId 老师基本信息主键
     * @return 老师基本信息
     */
    @Override
    public KpiTeacher selectKpiTeacherByTeacherId(Long teacherId)
    {
        return kpiTeacherMapper.selectKpiTeacherByTeacherId(teacherId);
    }

    @Override
    public Long selectCountTeacher() {
        return kpiTeacherMapper.selectCountTeacher();
    }

    @Override
    public Long selectSumCountByAuditState(String auditState) {
        return kpiTeacherMapper.selectSumCountByAuditState(auditState);
    }

    @Override
    public Long selectMaxSumTotal(String kpiYear) {
        return kpiTeacherMapper.selectMaxSumTotal(kpiYear);
    }

    @Override
    public List<KpiStatistics> selectKpiList(String kpiYear) {
        return kpiTeacherMapper.selectKpiList(kpiYear);
    }

    @Override
    public List<KpiStatistics> selectKpiListTop10(String kpiYear) {
        return kpiTeacherMapper.selectKpiListTop10(kpiYear);
    }

    @Override
    public boolean checkPhoneUnique(KpiTeacher kpiTeacher) {
        Long teacherId = StringUtils.isNull(kpiTeacher.getTeacherId()) ? -1L : kpiTeacher.getTeacherId();
        KpiTeacher info = kpiTeacherMapper.checkPhoneUnique(kpiTeacher.getTeacherTel());
        if (StringUtils.isNotNull(info) && info.getTeacherId().longValue() != teacherId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 查询老师基本信息列表
     * 
     * @param kpiTeacher 老师基本信息
     * @return 老师基本信息
     */
    @Override
    public List<KpiTeacher> selectKpiTeacherList(KpiTeacher kpiTeacher)
    {
        return kpiTeacherMapper.selectKpiTeacherList(kpiTeacher);
    }

    /**
     * 新增老师基本信息
     * 
     * @param kpiTeacher 老师基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKpiTeacher(KpiTeacher kpiTeacher)
    {
        String avatar="/profile/avatar/2024/04/30/jstx_20240430111031A001.jpeg";
        kpiTeacher.setTeacherAvatar(avatar);
        kpiTeacher.setCreateTime(DateUtils.getNowDate());
        int i= kpiTeacherMapper.insertKpiTeacher(kpiTeacher);
        if(i>0){
            String password = configService.selectConfigByKey("sys.user.initPassword");
            //新增一个学生以学号为用户名的登录账户,密码123456
            SysUser sysUser = new SysUser();
            sysUser.setAvatar(avatar);//默认头像
            sysUser.setDeptId(kpiTeacher.getTeacherDepartment());
            sysUser.setUserName(kpiTeacher.getTeacherTel().toString());
            sysUser.setNickName(kpiTeacher.getTeacherName());
            sysUser.setPhonenumber(kpiTeacher.getTeacherTel().toString());
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            sysUser.setTeacherId(kpiTeacher.getTeacherId());
            Long []RoleIds={100l};
            sysUser.setRoleIds(RoleIds);//给他一个老师的角色权限

            userService.insertUser(sysUser);
        }
        return i;
    }

    /**
     * 修改老师基本信息
     * 
     * @param kpiTeacher 老师基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKpiTeacher(KpiTeacher kpiTeacher)
    {
        kpiTeacher.setUpdateTime(DateUtils.getNowDate());
        int i=kpiTeacherMapper.updateKpiTeacher(kpiTeacher);
        if(i>0){
            KpiTeacher kpiTeacher1 = selectKpiTeacherByTeacherId(kpiTeacher.getTeacherId());
            SysUser sysUser = userService.selectUserByTeacherId(kpiTeacher1.getTeacherId());
            sysUser.setAvatar(kpiTeacher1.getTeacherAvatar());
            sysUser.setNickName(kpiTeacher1.getTeacherName());
            sysUser.setUserName(kpiTeacher1.getTeacherTel());
            sysUser.setPhonenumber(kpiTeacher1.getTeacherTel().toString());
            Long []RoleIds={100l};
            sysUser.setRoleIds(RoleIds);
            userService.updateUser(sysUser);
        }
        return i;
    }

    /**
     * 批量删除老师基本信息
     * 
     * @param teacherIds 需要删除的老师基本信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteKpiTeacherByTeacherIds(Long[] teacherIds)
    {
        //先删除系统用户信息
        for (Long teacherId:teacherIds){
            SysUser sysUser = userService.selectUserByTeacherId(teacherId);
            userService.deleteUserById(sysUser.getUserId());
        }

        return kpiTeacherMapper.deleteKpiTeacherByTeacherIds(teacherIds);
    }

    /**
     * 删除老师基本信息信息
     * 
     * @param teacherId 老师基本信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteKpiTeacherByTeacherId(Long teacherId)
    {
        SysUser sysUser = userService.selectUserByTeacherId(teacherId);
        userService.deleteUserById(sysUser.getUserId());
        return kpiTeacherMapper.deleteKpiTeacherByTeacherId(teacherId);
    }

    /**
     * 导入老师数据
     *
     * @param kpiTeachers 老师数据列表
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importTeacher(List<KpiTeacher> kpiTeachers, String operName) {
        if (StringUtils.isNull(kpiTeachers) || kpiTeachers.size() == 0)
        {
            throw new ServiceException("导入老师数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (KpiTeacher kpiTeacher : kpiTeachers)
        {
            try
            {
                Long teacherId = kpiTeacher.getTeacherId();
                // 验证是否存在这个用户
                SysUser u = userService.selectUserByTeacherId(teacherId);
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, kpiTeacher);
                    if (StringUtils.isNotEmpty(kpiTeacher.getTeacherTel()) && !checkPhoneUnique(kpiTeacher))
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、姓名 " + kpiTeacher.getTeacherName() + " 手机号已存在");
                    }else {
                        insertKpiTeacher(kpiTeacher);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、姓名 " + kpiTeacher.getTeacherName() + " 导入成功");
                    }

                }

                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 " + kpiTeacher.getTeacherName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、姓名 " + kpiTeacher.getTeacherName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
