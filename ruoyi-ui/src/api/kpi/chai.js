import request from '@/utils/request'

// 查询指导优秀论文与讲座列表
export function listChai(query) {
  return request({
    url: '/kpi/chai/list',
    method: 'get',
    params: query
  })
}
export function myListChai(query) {
  return request({
    url: '/kpi/chai/myList',
    method: 'get',
    params: query
  })
}
// 查询指导优秀论文与讲座详细
export function getChai(excellentPaperChairId) {
  return request({
    url: '/kpi/chai/' + excellentPaperChairId,
    method: 'get'
  })
}

// 新增指导优秀论文与讲座
export function addChai(data) {
  return request({
    url: '/kpi/chai',
    method: 'post',
    data: data
  })
}

// 修改指导优秀论文与讲座
export function updateChai(data) {
  return request({
    url: '/kpi/chai',
    method: 'put',
    data: data
  })
}

// 删除指导优秀论文与讲座
export function delChai(excellentPaperChairId) {
  return request({
    url: '/kpi/chai/' + excellentPaperChairId,
    method: 'delete'
  })
}
