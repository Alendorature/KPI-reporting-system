<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
     
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目来源" prop="projectSource">
        <el-input
          v-model="queryParams.projectSource"
          placeholder="请输入项目来源"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目编号" prop="projectNumber">
        <el-input
          v-model="queryParams.projectNumber"
          placeholder="请输入项目编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="财务资金账户编号" prop="financeAccountNumber">
        <el-input
          v-model="queryParams.financeAccountNumber"
          placeholder="请输入财务资金账户编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目起始时间">
        <el-date-picker
          v-model="daterangeProjectStartTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="项目结束时间">
        <el-date-picker
          v-model="daterangeProjectEndTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      
      <el-form-item label="是否首次拨款" prop="isFirstAppropriation">
        <el-select v-model="queryParams.isFirstAppropriation" placeholder="请选择是否首次拨款" clearable>
          <el-option
            v-for="dict in dict.type.is_first_appropriation"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="排名" prop="ranking">
        <el-input
          v-model="queryParams.ranking"
          placeholder="请输入排名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="量值" prop="magnitudeId">
        <!-- <el-input
          v-model="queryParams.magnitudeId"
          placeholder="请输入量值id"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.magnitudeId" placeholder="请选择量值" clearable >
            <el-option
              v-for="item in magnitudeList"
              :key="item.magnitudeId"
              :label="item.magnitudeName"
              :value="item.magnitudeId">
            </el-option>
          </el-select>
      </el-form-item>
      <!-- <el-form-item label="项目得分" prop="projectScore">
        <el-input
          v-model="queryParams.projectScore"
          placeholder="请输入项目得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="kpi年份" prop="kpiYear">
        <el-date-picker clearable
          v-model="queryParams.kpiYear"
          type="year"
          value-format="yyyy"
          placeholder="请选择kpi年份">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核状态" prop="auditState">
        <el-select v-model="queryParams.auditState" placeholder="请选择审核状态" clearable>
          <el-option
            v-for="dict in dict.type.kpi_audit_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
       <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['kpi:project:add']"
        >新增</el-button>
       </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kpi:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目id" align="center" prop="projectId" />
      <!-- <el-table-column label="教师id" align="center" prop="teacherId" />
      <el-table-column label="老师姓名" align="center" prop="teacherName" />
      <el-table-column label="老师头像" align="center" prop="teacherAvatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.teacherAvatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="老师电话" align="center" prop="teacherTel" /> -->
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目来源" align="center" prop="projectSource" />
      <el-table-column label="项目编号" align="center" prop="projectNumber" />
      <el-table-column label="财务资金账户编号" align="center" prop="financeAccountNumber" />
      <el-table-column label="项目起始时间" align="center" prop="projectStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.projectStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目结束时间" align="center" prop="projectEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.projectEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到账经费(万元)" align="center" prop="accountExpenditure" />
      <el-table-column label="外拨经费(万元)" align="center" prop="outExpenditure" />
      <el-table-column label="实际经费(万元)" align="center" prop="actualExpenditure" />
      <el-table-column label="是否首次拨款" align="center" prop="isFirstAppropriation">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_first_appropriation" :value="scope.row.isFirstAppropriation"/>
        </template>
      </el-table-column>
      <el-table-column label="排名" align="center" prop="ranking" />
      <!-- <el-table-column label="量值id" align="center" prop="magnitudeId" /> -->
      <el-table-column label="量值名称" align="center" prop="magnitudeName" />
      <el-table-column label="项目得分" align="center" prop="projectScore" />
      <el-table-column label="kpi年份" align="center" prop="kpiYear" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.kpiYear, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.kpi_audit_state" :value="scope.row.auditState"/>
        </template>
      </el-table-column>
      <el-table-column label="驳回原因" align="center" prop="rejectCause" />
      <el-table-column label="备注" align="center" prop="remark" />
     
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改项目信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
       
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目来源" prop="projectSource">
          <el-input v-model="form.projectSource" placeholder="请输入项目来源" />
        </el-form-item>
        <el-form-item label="项目编号" prop="projectNumber">
          <el-input v-model="form.projectNumber" placeholder="请输入项目编号" />
        </el-form-item>
        <el-form-item label="财务资金账户编号" prop="financeAccountNumber">
          <el-input v-model="form.financeAccountNumber" placeholder="请输入财务资金账户编号" />
        </el-form-item>
        <el-form-item label="项目起始时间" prop="projectStartTime" >
          <el-date-picker clearable
            v-model="form.projectStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目起始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目结束时间" prop="projectEndTime" >
          <el-date-picker clearable
            v-model="form.projectEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="到账经费(万元)" prop="accountExpenditure">
          <el-input v-model="form.accountExpenditure" placeholder="请输入到账经费" />
        </el-form-item>
        <el-form-item label="外拨经费(万元)" prop="outExpenditure">
          <el-input v-model="form.outExpenditure" placeholder="请输入外拨经费" />
        </el-form-item>
        <el-form-item label="实际经费(万元)" prop="actualExpenditure">
          <el-input v-model="form.actualExpenditure" placeholder="请输入实际经费" />
        </el-form-item>
        <el-form-item label="是否首次拨款" prop="isFirstAppropriation" >
          <el-radio-group v-model="form.isFirstAppropriation">
            <el-radio
              v-for="dict in dict.type.is_first_appropriation"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label="排名" prop="ranking">
          <el-input v-model="form.ranking" placeholder="请输入排名" />
        </el-form-item> -->
        <el-form-item label="量值" prop="magnitudeId">
          <el-select v-model="form.magnitudeId" placeholder="请选择量值" >
            <el-option
              v-for="item in magnitudeList"
              :key="item.magnitudeId"
              :label="item.magnitudeName"
              :value="item.magnitudeId">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="项目得分" prop="projectScore">
          <el-input v-model="form.projectScore" placeholder="请输入项目得分" />
        </el-form-item> -->
        <el-form-item label="kpi年份" prop="kpiYear">
          <el-date-picker clearable 
            v-model="form.kpiYear"
            type="year"
            value-format="yyyy"
            placeholder="请选择kpi年份">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { myListProject, getProject, delProject, addProject, updateProject } from "@/api/kpi/project";
import { listMagnitude } from "@/api/kpi/magnitude";

export default {
  name: "Project",
  dicts: ['kpi_audit_state', 'is_first_appropriation'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目信息表格数据
      projectList: [],
          // KPI量值标准表格数据
          magnitudeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeProjectStartTime: [],
      // 备注时间范围
      daterangeProjectEndTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        projectName: null,
        projectSource: null,
        projectNumber: null,
        financeAccountNumber: null,
        projectStartTime: null,
        projectEndTime: null,
        accountExpenditure: null,
        outExpenditure: null,
        actualExpenditure: null,
        isFirstAppropriation: null,
        ranking: null,
        magnitudeId: null,
        projectScore: null,
        kpiYear: null,
        auditState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherId: [
          { required: true, message: "教师id不能为空", trigger: "blur" }
        ],
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        projectSource: [
          { required: true, message: "项目来源不能为空", trigger: "blur" }
        ],
        projectNumber: [
          { required: true, message: "项目编号不能为空", trigger: "blur" }
        ],
        financeAccountNumber: [
          { required: true, message: "财务资金账户编号不能为空", trigger: "blur" }
        ],
        projectStartTime: [
          { required: true, message: "项目起始时间不能为空", trigger: "blur" }
        ],
        projectEndTime: [
          { required: true, message: "项目结束时间不能为空", trigger: "blur" }
        ],
        actualExpenditure: [
          { required: true, message: "实际经费不能为空", trigger: "blur" }
        ],
        isFirstAppropriation: [
          { required: true, message: "是否首次拨款不能为空", trigger: "change" }
        ],
        magnitudeId: [
          { required: true, message: "量值id不能为空", trigger: "blur" }
        ],
        kpiYear: [
          { required: true, message: "kpi年份不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getMagnitudeList();
  },
  methods: {
    /** 查询项目信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeProjectStartTime && '' != this.daterangeProjectStartTime) {
        this.queryParams.params["beginProjectStartTime"] = this.daterangeProjectStartTime[0];
        this.queryParams.params["endProjectStartTime"] = this.daterangeProjectStartTime[1];
      }
      if (null != this.daterangeProjectEndTime && '' != this.daterangeProjectEndTime) {
        this.queryParams.params["beginProjectEndTime"] = this.daterangeProjectEndTime[0];
        this.queryParams.params["endProjectEndTime"] = this.daterangeProjectEndTime[1];
      }
      myListProject(this.queryParams).then(response => {
        this.projectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getMagnitudeList(){
      listMagnitude({ pageNum: 1,
        pageSize: 1000,typeIds:[3,4]}).then(response => {
        this.magnitudeList = response.rows;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        projectId: null,
        teacherId: null,
        projectName: null,
        projectSource: null,
        projectNumber: null,
        financeAccountNumber: null,
        projectStartTime: null,
        projectEndTime: null,
        accountExpenditure: null,
        outExpenditure: null,
        actualExpenditure: null,
        isFirstAppropriation: null,
        ranking: null,
        magnitudeId: null,
        projectScore: null,
        kpiYear: null,
        auditState: null,
        rejectCause: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeProjectStartTime = [];
      this.daterangeProjectEndTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProject(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "审核项目信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateProject(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProject(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delProject(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/project/export', {
        ...this.queryParams
      }, `project_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
