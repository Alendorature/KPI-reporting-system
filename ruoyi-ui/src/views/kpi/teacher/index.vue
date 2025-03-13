<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="老师姓名" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入老师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="老师职称" prop="teacherCareer">
        <el-input
          v-model="queryParams.teacherCareer"
          placeholder="请输入老师职称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="老师所在系" prop="teacherDepartment">
        <!-- <el-input
          v-model="queryParams.teacherDepartment"
          placeholder="请输入老师所在系"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->

        <el-select v-model="queryParams.teacherDepartment" placeholder="请选择老师所在系"    clearable>
            <el-option
              v-for="item in deptList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId">
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="老师电话" prop="teacherTel">
        <el-input
          v-model="queryParams.teacherTel"
          placeholder="请输入老师电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="老师邮箱" prop="teacherMail">
        <el-input
          v-model="queryParams.teacherMail"
          placeholder="请输入老师邮箱"
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
          v-hasPermi="['kpi:teacher:add']"
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
          v-hasPermi="['kpi:teacher:edit']"
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
          v-hasPermi="['kpi:teacher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
            <el-button
              type="info"
              plain
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
              v-hasPermi="['kpi:teacher:import']"
            >导入</el-button>
          </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kpi:teacher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="老师id" align="center" prop="teacherId" />
      <el-table-column label="老师姓名" align="center" prop="teacherName" />
      <el-table-column label="老师头像" align="center" prop="teacherAvatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.teacherAvatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="老师职称" align="center" prop="teacherCareer" />
      <el-table-column label="老师所在系" align="center" prop="deptName" />
      <el-table-column label="老师电话" align="center" prop="teacherTel" />
      <el-table-column label="老师邮箱" align="center" prop="teacherMail" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kpi:teacher:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kpi:teacher:remove']"
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

    <!-- 添加或修改老师基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入老师姓名" />
        </el-form-item>
        <el-form-item label="老师头像" prop="teacherAvatar">
          <image-upload v-model="form.teacherAvatar" :limit="1"/>
        </el-form-item>
        <el-form-item label="老师职称" prop="teacherCareer">
          <el-input v-model="form.teacherCareer" placeholder="请输入老师职称" />
        </el-form-item>
        <el-form-item label="老师所在系" prop="teacherDepartment">
          <!-- <el-input v-model="form.teacherDepartment" placeholder="请输入老师所在系" /> -->
          <el-select v-model="form.teacherDepartment" placeholder="请选择老师所在系">
            <el-option
              v-for="item in deptList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="老师电话" prop="teacherTel">
          <el-input v-model="form.teacherTel" placeholder="请输入老师电话" />
        </el-form-item>
        <el-form-item label="老师邮箱" prop="teacherMail">
          <el-input v-model="form.teacherMail" placeholder="请输入老师邮箱" />
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

     <!-- 老师导入对话框 -->
     <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <!-- <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的用户数据
          </div> -->
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTeacher, getTeacher, delTeacher, addTeacher, updateTeacher,dataTeacher } from "@/api/kpi/teacher";
import { getToken } from "@/utils/auth";

export default {
  name: "Teacher",
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
      // 老师基本信息表格数据
      teacherList: [],
      deptList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherName: null,
        teacherCareer: null,
        teacherDepartment: null,
        teacherTel: null,
        teacherMail: null,
      },
       // 老师导入参数
       upload: {
        // 是否显示弹出层（老师导入）
        open: false,
        // 弹出层标题（老师导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的老师数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/kpi/teacher/importData"
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherName: [
          { required: true, message: "老师姓名不能为空", trigger: "blur" }
        ],
        teacherCareer: [
          { required: true, message: "老师职称不能为空", trigger: "blur" }
        ],
        teacherDepartment: [
          { required: true, message: "老师所在系不能为空", trigger: "blur" }
        ],
        teacherTel: [
          { required: true, message: "老师电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getData();
    this.getList();
  },
  methods: {
    /** 查询老师基本信息列表 */
    getList() {
      this.loading = true;
      listTeacher(this.queryParams).then(response => {
        this.teacherList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getData(){
      dataTeacher().then(response => {
        this.deptList = response.data;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        teacherId: null,
        teacherName: null,
        teacherCareer: null,
        teacherDepartment: null,
        teacherTel: null,
        teacherMail: null,
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
      this.ids = selection.map(item => item.teacherId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加老师基本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teacherId = row.teacherId || this.ids
      getTeacher(teacherId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改老师基本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teacherId != null) {
            updateTeacher(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacher(this.form).then(response => {
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
      const teacherIds = row.teacherId || this.ids;
      this.$modal.confirm('是否确认删除老师基本信息编号为"' + teacherIds + '"的数据项？').then(function() {
        return delTeacher(teacherIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/teacher/export', {
        ...this.queryParams
      }, `teacher_${new Date().getTime()}.xlsx`)
    },
     /** 导入按钮操作 */
     handleImport() {
      this.upload.title = "老师导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('kpi/teacher/importTemplate', {
      }, `老师导入模板_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
