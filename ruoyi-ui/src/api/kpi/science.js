import request from '@/utils/request'

// 查询科技成果列表
export function listScience(query) {
  return request({
    url: '/kpi/science/list',
    method: 'get',
    params: query
  })
}

// 查询科技成果详细
export function getScience(scienceId) {
  return request({
    url: '/kpi/science/' + scienceId,
    method: 'get'
  })
}

// 新增科技成果
export function addScience(data) {
  return request({
    url: '/kpi/science',
    method: 'post',
    data: data
  })
}

// 修改科技成果
export function updateScience(data) {
  return request({
    url: '/kpi/science',
    method: 'put',
    data: data
  })
}

// 删除科技成果
export function delScience(scienceId) {
  return request({
    url: '/kpi/science/' + scienceId,
    method: 'delete'
  })
}
