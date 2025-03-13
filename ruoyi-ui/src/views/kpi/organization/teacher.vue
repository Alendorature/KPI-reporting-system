<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="国际学术组织名称" prop="organizationName">
        <el-input
          v-model="queryParams.organizationName"
          placeholder="请输入国际学术组织名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    
      <el-form-item label="任职日期" prop="takeOfficeTime">
        <el-date-picker clearable
          v-model="queryParams.takeOfficeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择任职日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="量值" prop="magnitudeId">
        <el-select v-model="queryParams.magnitudeId" placeholder="请选择量值" clearable >
            <el-option
              v-for="item in magnitudeList"
              :key="item.magnitudeId"
              :label="item.magnitudeName"
              :value="item.magnitudeId">
            </el-option>
          </el-select>
      </el-form-item>
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
          v-hasPermi="['kpi:organization:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="organizationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="国际学术组织任职id" align="center" prop="organizationId" />
      <el-table-column label="国际学术组织名称" align="center" prop="organizationName" />
     
      <el-table-column label="任职日期" align="center" prop="takeOfficeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.takeOfficeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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

    <!-- 添加或修改国际学术组织任职对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="国际学术组织名称" prop="organizationName">
          <el-input v-model="form.organizationName" placeholder="请输入国际学术组织名称" />
        </el-form-item>
       
        <el-form-item label="任职日期" prop="takeOfficeTime">
          <el-date-picker clearable 
            v-model="form.takeOfficeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择任职日期">
          </el-date-picker>
        </el-form-item>
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
import { myListOrganization, getOrganization, delOrganization, addOrganization, updateOrganization } from "@/api/kpi/organization";
import { listMagnitude } from "@/api/kpi/magnitude";

export default {
  name: "Organization",
  dicts: ['kpi_audit_state'],
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
      // 国际学术组织任职表格数据
      organizationList: [],
          // KPI量值标准表格数据
          magnitudeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        organizationName: null,
        teacherId: null,
        takeOfficeTime: null,
        magnitudeId: null,
        kpiYear: null,
        auditState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        organizationName: [
          { required: true, message: "国际学术组织名称不能为空", trigger: "blur" }
        ],
        teacherId: [
          { required: true, message: "教师id不能为空", trigger: "blur" }
        ],
        takeOfficeTime: [
          { required: true, message: "任职日期不能为空", trigger: "blur" }
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
    /** 查询国际学术组织任职列表 */
    getList() {
      this.loading = true;
      myListOrganization(this.queryParams).then(response => {
        this.organizationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getMagnitudeList(){
      listMagnitude({ pageNum: 1,
        pageSize: 1000,typeId:9}).then(response => {
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
        organizationId: null,
        organizationName: null,
        teacherId: null,
        takeOfficeTime: null,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.organizationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加国际学术组织任职";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const organizationId = row.organizationId || this.ids
      getOrganization(organizationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "审核国际学术组织任职";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.organizationId != null) {
            updateOrganization(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrganization(this.form).then(response => {
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
      const organizationIds = row.organizationId || this.ids;
      this.$modal.confirm('是否确认删除国际学术组织任职编号为"' + organizationIds + '"的数据项？').then(function() {
        return delOrganization(organizationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/organization/export', {
        ...this.queryParams
      }, `organization_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
