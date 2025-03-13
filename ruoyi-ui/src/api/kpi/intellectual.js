import request from '@/utils/request'

// 查询知识产权列表
export function listIntellectual(query) {
  return request({
    url: '/kpi/intellectual/list',
    method: 'get',
    params: query
  })
}

export function myListIntellectual(query) {
  return request({
    url: '/kpi/intellectual/myList',
    method: 'get',
    params: query
  })
}

// 查询知识产权详细
export function getIntellectual(intellectualId) {
  return request({
    url: '/kpi/intellectual/' + intellectualId,
    method: 'get'
  })
}

// 新增知识产权
export function addIntellectual(data) {
  return request({
    url: '/kpi/intellectual',
    method: 'post',
    data: data
  })
}

// 修改知识产权
export function updateIntellectual(data) {
  return request({
    url: '/kpi/intellectual',
    method: 'put',
    data: data
  })
}

// 删除知识产权
export function delIntellectual(intellectualId) {
  return request({
    url: '/kpi/intellectual/' + intellectualId,
    method: 'delete'
  })
}
