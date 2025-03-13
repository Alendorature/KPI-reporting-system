import request from '@/utils/request'

// 查询标准及平台列表
export function listStandard(query) {
  return request({
    url: '/kpi/standard/list',
    method: 'get',
    params: query
  })
}

export function myListStandard(query) {
  return request({
    url: '/kpi/standard/myList',
    method: 'get',
    params: query
  })
}

// 查询标准及平台详细
export function getStandard(standardId) {
  return request({
    url: '/kpi/standard/' + standardId,
    method: 'get'
  })
}

// 新增标准及平台
export function addStandard(data) {
  return request({
    url: '/kpi/standard',
    method: 'post',
    data: data
  })
}

// 修改标准及平台
export function updateStandard(data) {
  return request({
    url: '/kpi/standard',
    method: 'put',
    data: data
  })
}

// 删除标准及平台
export function delStandard(standardId) {
  return request({
    url: '/kpi/standard/' + standardId,
    method: 'delete'
  })
}
