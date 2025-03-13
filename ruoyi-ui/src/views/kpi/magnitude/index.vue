<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="量值名称" prop="magnitudeName">
        <el-input
          v-model="queryParams.magnitudeName"
          placeholder="请输入量值名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类id" prop="typeId">
        <el-input
          v-model="queryParams.typeId"
          placeholder="请输入分类id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="基础KPI分数" prop="basicScore">
        <el-input
          v-model="queryParams.basicScore"
          placeholder="请输入基础KPI分数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="额外KPI分数" prop="extroScore">
        <el-input
          v-model="queryParams.extroScore"
          placeholder="请输入额外KPI分数"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['kpi:magnitude:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['kpi:magnitude:edit']"
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
          v-hasPermi="['kpi:magnitude:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kpi:magnitude:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="magnitudeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="量值id" align="center" prop="magnitudeId" />
      <el-table-column label="量值名称" align="center" prop="magnitudeName" />
      <el-table-column label="分类" align="center" prop="typeName" />
      <el-table-column label="基础KPI分数" align="center" prop="basicScore" />
      <el-table-column label="额外KPI分数" align="center" prop="extroScore" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kpi:magnitude:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kpi:magnitude:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改KPI量值标准对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="量值名称" prop="magnitudeName">
          <el-input v-model="form.magnitudeName" placeholder="请输入量值名称" />
        </el-form-item>
        <el-form-item label="分类" prop="typeId">
          <!-- <el-input v-model="form.typeId" placeholder="请输入分类" /> -->
          <el-select v-model="form.typeId" placeholder="请选择分类">
            <el-option
              v-for="item in typeList"
              :key="item.typeId"
              :label="item.typeName"
              :value="item.typeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="基础KPI分数" prop="basicScore">
          <el-input v-model="form.basicScore" placeholder="请输入基础KPI分数" />
        </el-form-item>
        <el-form-item label="额外KPI分数" prop="extroScore">
          <el-input v-model="form.extroScore" placeholder="请输入额外KPI分数" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listMagnitude, getMagnitude, delMagnitude, addMagnitude, updateMagnitude } from "@/api/kpi/magnitude";
import { listType } from "@/api/kpi/type";

export default {
  name: "Magnitude",
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
      // KPI量值标准表格数据
      magnitudeList: [],
       // kpi分类表格数据
       typeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        magnitudeName: null,
        typeId: null,
        basicScore: null,
        extroScore: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        magnitudeName: [
          { required: true, message: "量值名称不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "分类id不能为空", trigger: "blur" }
        ],
        basicScore: [
          { required: true, message: "基础KPI分数不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getTypeList();
  },
  methods: {
    /** 查询KPI量值标准列表 */
    getList() {
      this.loading = true;
      listMagnitude(this.queryParams).then(response => {
        this.magnitudeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    getTypeList() {
      listType({
        pageNum: 1,
        pageSize: 10000,
        typeStatus: '0',
      }).then(response => {
        this.typeList = response.rows;
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
        magnitudeId: null,
        magnitudeName: null,
        typeId: null,
        basicScore: null,
        extroScore: null,
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
      this.ids = selection.map(item => item.magnitudeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加KPI量值标准";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const magnitudeId = row.magnitudeId || this.ids
      getMagnitude(magnitudeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改KPI量值标准";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.magnitudeId != null) {
            updateMagnitude(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMagnitude(this.form).then(response => {
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
      const magnitudeIds = row.magnitudeId || this.ids;
      this.$modal.confirm('是否确认删除KPI量值标准编号为"' + magnitudeIds + '"的数据项？').then(function() {
        return delMagnitude(magnitudeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/magnitude/export', {
        ...this.queryParams
      }, `magnitude_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
