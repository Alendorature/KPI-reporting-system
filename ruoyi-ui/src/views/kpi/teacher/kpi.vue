<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="kpi年份" prop="kpiYear">
          <el-date-picker clearable
            v-model="queryParams.kpiYear"
            type="year"
            value-format="yyyy"
            placeholder="请选择kpi年份">
          </el-date-picker>
        </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
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

    <el-table v-loading="loading" :data="teacherList" >
      <el-table-column label="老师id" align="center" prop="teacherId" />
      <el-table-column label="老师姓名" align="center" prop="teacherName" />
      <el-table-column label="老师头像" align="center" prop="teacherAvatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.teacherAvatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="老师电话" align="center" prop="teacherTel" />
      <el-table-column label="非教学类科研论文得分" align="center" prop="sumPntProjectScore" />
      <el-table-column label="纵向横向项目得分" align="center" prop="sumProProjectScore" />
      <el-table-column label="知识产权得分" align="center" prop="sumInteProjectScore" />
      <el-table-column label="行业标准得分" align="center" prop="sumStaProjectScore" />
      <el-table-column label="奖项得分" align="center" prop="sumAwaProjectScore" />
      <el-table-column label="优秀论文得分" align="center" prop="sumEpcProjectScore" />
      <el-table-column label="国际组织得分" align="center" prop="sumOrgProjectScore" />
      <el-table-column label="科技成果转化得分" align="center" prop="sumSciProjectScore" />
      <el-table-column label="合计" align="center" prop="sumTotal" />
     
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listStatistics } from "@/api/kpi/teacher";

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
      // 老师kpi信息表格数据
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
        kpiYear: '2024',
      },
      
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询老师kpi信息列表 */
    getList() {
      this.loading = true;
      listStatistics(this.queryParams).then(response => {
        this.teacherList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
   
    /** 导出按钮操作 */
    handleExport() {
      this.download('kpi/teacher/statistics/export', {
        ...this.queryParams
      }, this.queryParams.kpiYear+`年kpi统计_${new Date().getTime()}.xlsx`)
    },
    
  }
};
</script>
