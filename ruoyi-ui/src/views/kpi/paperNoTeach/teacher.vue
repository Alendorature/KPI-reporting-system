<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
    
      <el-form-item label="论文名称" prop="paperName">
        <el-input
          v-model="queryParams.paperName"
          placeholder="请输入论文名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="期刊/会议名称" prop="periodicalName">
        <el-input
          v-model="queryParams.periodicalName"
          placeholder="请输入期刊/会议名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发表日期">
        <el-date-picker
          v-model="daterangePublishTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="量值id" prop="magnitudeId">
        <el-input
          v-model="queryParams.magnitudeId"
          placeholder="请输入量值id"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['kpi:paperNoTeach:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['kpi:paperNoTeach:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['kpi:paperNoTeach:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kpi:paperNoTeach:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paperNoTeachList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="非教学类科研论文id" align="center" prop="paperNoTeachId" />
      <!-- <el-table-column label="教师id" align="center" prop="teacherId" />
      <el-table-column label="老师姓名" align="center" prop="teacherName" />
      <el-table-column label="老师头像" align="center" prop="teacherAvatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.teacherAvatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="老师电话" align="center" prop="teacherTel" /> -->
      <el-table-column label="论文名称" align="center" prop="paperName" />
      <el-table-column label="期刊/会议名称" align="center" prop="periodicalName" />
      <el-table-column label="发表日期" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="期卷号" align="center" prop="issueNumber" />
      <el-table-column label="ISSN号" align="center" prop="issnNumber" />
      <el-table-column label="起止页码" align="center" prop="startEndPageNumber" />
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
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kpi:paperNoTeach:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kpi:paperNoTeach:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改非教学类科研论文对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        
        <el-form-item label="论文名称" prop="paperName">
          <el-input v-model="form.paperName" placeholder="请输入论文名称" />
        </el-form-item>
        <el-form-item label="期刊/会议名称" prop="periodicalName">
          <el-input v-model="form.periodicalName" placeholder="请输入期刊/会议名称" />
        </el-form-item>
        <el-form-item label="发表日期" prop="publishTime">
          <el-date-picker clearable
            v-model="form.publishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发表日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="期卷号" prop="issueNumber">
          <el-input v-model="form.issueNumber" placeholder="请输入期卷号" />
        </el-form-item>
        <el-form-item label="ISSN号" prop="issnNumber">
          <el-input v-model="form.issnNumber" placeholder="请输入ISSN号" />
        </el-form-item>
        <el-form-item label="起止页码" prop="startEndPageNumber">
          <el-input v-model="form.startEndPageNumber" placeholder="请输入起止页码" />
        </el-form-item>
        <el-form-item label="量值" prop="magnitudeId">
          <el-select v-model="form.magnitudeId" placeholder="请选择量值">
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
import { myListPaperNoTeach, getPaperNoTeach, delPaperNoTeach, addPaperNoTeach, updatePaperNoTeach } from "@/api/kpi/paperNoTeach";
import { listMagnitude } from "@/api/kpi/magnitude";
export default {
  name: "PaperNoTeach",
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
      // 非教学类科研论文表格数据
      paperNoTeachList: [],
        // KPI量值标准表格数据
        magnitudeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangePublishTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        paperName: null,
        periodicalName: null,
        publishTime: null,
        issueNumber: null,
        issnNumber: null,
        magnitudeId: null,
        kpiYear: null,
        auditState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
       
        paperName: [
          { required: true, message: "论文名称不能为空", trigger: "blur" }
        ],
        periodicalName: [
          { required: true, message: "期刊/会议名称不能为空", trigger: "blur" }
        ],
        publishTime: [
          { required: true, message: "发表日期不能为空", trigger: "blur" }
        ],
        issueNumber: [
          { required: true, message: "期卷号不能为空", trigger: "blur" }
        ],
        issnNumber: [
          { required: true, message: "ISSN号不能为空", trigger: "blur" }
        ],
        startEndPageNumber: [
          { required: true, message: "起止页码不能为空", trigger: "blur" }
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
    /** 查询非教学类科研论文列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePublishTime && '' != this.daterangePublishTime) {
        this.queryParams.params["beginPublishTime"] = this.daterangePublishTime[0];
        this.queryParams.params["endPublishTime"] = this.daterangePublishTime[1];
      }
      myListPaperNoTeach(this.queryParams).then(response => {
        this.paperNoTeachList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    getMagnitudeList(){
      listMagnitude({ pageNum: 1,
        pageSize: 1000,typeId:2}).then(response => {
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
        paperNoTeachId: null,
        teacherId: null,
        paperName: null,
        periodicalName: null,
        publishTime: null,
        issueNumber: null,
        issnNumber: null,
        startEndPageNumber: null,
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
      this.daterangePublishTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.paperNoTeachId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加非教学类科研论文";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const paperNoTeachId = row.paperNoTeachId || this.ids
      getPaperNoTeach(paperNoTeachId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改非教学类科研论文";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.paperNoTeachId != null) {
            updatePaperNoTeach(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPaperNoTeach(this.form).then(response => {
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
      const paperNoTeachIds = row.paperNoTeachId || this.ids;
      this.$modal.confirm('是否确认删除非教学类科研论文编号为"' + paperNoTeachIds + '"的数据项？').then(function() {
        return delPaperNoTeach(paperNoTeachIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/paperNoTeach/export', {
        ...this.queryParams
      }, `paperNoTeach_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
