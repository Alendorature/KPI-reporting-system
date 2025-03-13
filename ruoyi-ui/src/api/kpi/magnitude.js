import request from '@/utils/request'

// 查询KPI量值标准列表
export function listMagnitude(query) {
  return request({
    url: '/kpi/magnitude/list',
    method: 'get',
    params: query
  })
}

// 查询KPI量值标准详细
export function getMagnitude(magnitudeId) {
  return request({
    url: '/kpi/magnitude/' + magnitudeId,
    method: 'get'
  })
}

// 新增KPI量值标准
export function addMagnitude(data) {
  return request({
    url: '/kpi/magnitude',
    method: 'post',
    data: data
  })
}

// 修改KPI量值标准
export function updateMagnitude(data) {
  return request({
    url: '/kpi/magnitude',
    method: 'put',
    data: data
  })
}

// 删除KPI量值标准
export function delMagnitude(magnitudeId) {
  return request({
    url: '/kpi/magnitude/' + magnitudeId,
    method: 'delete'
  })
}
