<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      
      <el-form-item label="标准名称" prop="standardName">
        <el-input
          v-model="queryParams.standardName"
          placeholder="请输入标准名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="获得日期">
        <el-date-picker
          v-model="daterangeAcquireTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['kpi:standard:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标准id" align="center" prop="standardId" />
     
      <el-table-column label="标准名称" align="center" prop="standardName" />
      <el-table-column label="获得日期" align="center" prop="acquireTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.acquireTime, '{y}-{m}-{d}') }}</span>
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

    <!-- 添加或修改标准及平台对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
       
        <el-form-item label="标准名称" prop="standardName">
          <el-input v-model="form.standardName" placeholder="请输入标准名称" />
        </el-form-item>
        <el-form-item label="获得日期" prop="acquireTime">
          <el-date-picker clearable 
            v-model="form.acquireTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择获得日期">
          </el-date-picker>
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
import { myListStandard, getStandard, delStandard, addStandard, updateStandard } from "@/api/kpi/standard";
import { listMagnitude } from "@/api/kpi/magnitude";

export default {
  name: "Standard",
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
      // 标准及平台表格数据
      standardList: [],
          // KPI量值标准表格数据
          magnitudeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeAcquireTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        standardName: null,
        acquireTime: null,
        magnitudeId: null,
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
        standardName: [
          { required: true, message: "标准名称不能为空", trigger: "blur" }
        ],
        acquireTime: [
          { required: true, message: "获得日期不能为空", trigger: "blur" }
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
    /** 查询标准及平台列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeAcquireTime && '' != this.daterangeAcquireTime) {
        this.queryParams.params["beginAcquireTime"] = this.daterangeAcquireTime[0];
        this.queryParams.params["endAcquireTime"] = this.daterangeAcquireTime[1];
      }
      myListStandard(this.queryParams).then(response => {
        this.standardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getMagnitudeList(){
      listMagnitude({ pageNum: 1,
        pageSize: 1000,typeId:6}).then(response => {
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
        standardId: null,
        teacherId: null,
        standardName: null,
        acquireTime: null,
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
      this.daterangeAcquireTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.standardId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加标准及平台";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const standardId = row.standardId || this.ids
      getStandard(standardId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改标准及平台";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.standardId != null) {
            updateStandard(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStandard(this.form).then(response => {
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
      const standardIds = row.standardId || this.ids;
      this.$modal.confirm('是否确认删除标准及平台编号为"' + standardIds + '"的数据项？').then(function() {
        return delStandard(standardIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/standard/export', {
        ...this.queryParams
      }, `standard_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
