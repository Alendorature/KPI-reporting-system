<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    },
    setOptions({xAxisData, legendData, seriesData } = {}) {
      let xAxis={
        data: xAxisData,
          boundaryGap: false,
          axisTick: {
            show: false
          }
      }
      //let legend={legendData}

      let series=[];
      for(let i=0;i<legendData.length;i++){
        let legend=legendData[i];
        let serie={
          name: legend,
          smooth: true,
          type: 'line',
          data: seriesData[legend],
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        }
        series.push(serie)
      }

      this.chart.setOption({
        xAxis: xAxis,
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: false
          }
        },
        legend: {
          data:legendData,
        },
        series:series
      })
    }
  }
}
</script>
