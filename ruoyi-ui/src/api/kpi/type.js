import request from '@/utils/request'

// 查询kpi分类列表
export function listType(query) {
  return request({
    url: '/kpi/type/list',
    method: 'get',
    params: query
  })
}

// 查询kpi分类详细
export function getType(typeId) {
  return request({
    url: '/kpi/type/' + typeId,
    method: 'get'
  })
}

// 新增kpi分类
export function addType(data) {
  return request({
    url: '/kpi/type',
    method: 'post',
    data: data
  })
}

// 修改kpi分类
export function updateType(data) {
  return request({
    url: '/kpi/type',
    method: 'put',
    data: data
  })
}

// 删除kpi分类
export function delType(typeId) {
  return request({
    url: '/kpi/type/' + typeId,
    method: 'delete'
  })
}
