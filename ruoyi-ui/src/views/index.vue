<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" :panelData="panelData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
     kpi年份: <el-date-picker clearable 
            v-model="kpiYear"
            type="year"
            value-format="yyyy"
            @change="kpiYearChange"
            placeholder="请选择kpi年份">
          </el-date-picker>
      <line-chart :chart-data="line" />
    </el-row>

    <el-row :gutter="32">
      <!-- <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col> -->
      <!-- <el-col>
      <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col> -->
    </el-row>

    
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'

import { index } from "@/api/common";

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,

      kpiYear:'2024',
      panelData:{
        countTeacher:0,
        countAuditState0:0,
        countAuditState1:0,
        maxSumTotal:0,
      },
      line:{
        legendData:[],
        seriesData:[],
        xAxisData:{} 
      }
    }
  },
  created() {
    this.getIndex();
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
    ,
    getIndex(){
      index({kpiYear:this.kpiYear}).then(response=>{
        this.panelData=response.data.panel
        this.line=response.data.line
      })
    },
    kpiYearChange(){
this.getIndex();
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
