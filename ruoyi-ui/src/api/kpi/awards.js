import request from '@/utils/request'

// 查询奖项信息列表
export function listAwards(query) {
  return request({
    url: '/kpi/awards/list',
    method: 'get',
    params: query
  })
}

// 查询奖项信息详细
export function getAwards(awardsId) {
  return request({
    url: '/kpi/awards/' + awardsId,
    method: 'get'
  })
}

// 新增奖项信息
export function addAwards(data) {
  return request({
    url: '/kpi/awards',
    method: 'post',
    data: data
  })
}

// 修改奖项信息
export function updateAwards(data) {
  return request({
    url: '/kpi/awards',
    method: 'put',
    data: data
  })
}

// 删除奖项信息
export function delAwards(awardsId) {
  return request({
    url: '/kpi/awards/' + awardsId,
    method: 'delete'
  })
}
