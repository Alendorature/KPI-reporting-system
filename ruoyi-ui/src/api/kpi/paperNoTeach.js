import request from '@/utils/request'

// 查询非教学类科研论文列表
export function listPaperNoTeach(query) {
  return request({
    url: '/kpi/paperNoTeach/list',
    method: 'get',
    params: query
  })
}

export function myListPaperNoTeach(query) {
  return request({
    url: '/kpi/paperNoTeach/myList',
    method: 'get',
    params: query
  })
}

// 查询非教学类科研论文详细
export function getPaperNoTeach(paperNoTeachId) {
  return request({
    url: '/kpi/paperNoTeach/' + paperNoTeachId,
    method: 'get'
  })
}

// 新增非教学类科研论文
export function addPaperNoTeach(data) {
  return request({
    url: '/kpi/paperNoTeach',
    method: 'post',
    data: data
  })
}

// 修改非教学类科研论文
export function updatePaperNoTeach(data) {
  return request({
    url: '/kpi/paperNoTeach',
    method: 'put',
    data: data
  })
}

// 删除非教学类科研论文
export function delPaperNoTeach(paperNoTeachId) {
  return request({
    url: '/kpi/paperNoTeach/' + paperNoTeachId,
    method: 'delete'
  })
}
