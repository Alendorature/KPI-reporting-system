import request from '@/utils/request'

// 查询项目信息列表
export function listProject(query) {
  return request({
    url: '/kpi/project/list',
    method: 'get',
    params: query
  })
}

export function myListProject(query) {
  return request({
    url: '/kpi/project/myList',
    method: 'get',
    params: query
  })
}

// 查询项目信息详细
export function getProject(projectId) {
  return request({
    url: '/kpi/project/' + projectId,
    method: 'get'
  })
}

// 新增项目信息
export function addProject(data) {
  return request({
    url: '/kpi/project',
    method: 'post',
    data: data
  })
}

// 修改项目信息
export function updateProject(data) {
  return request({
    url: '/kpi/project',
    method: 'put',
    data: data
  })
}

// 删除项目信息
export function delProject(projectId) {
  return request({
    url: '/kpi/project/' + projectId,
    method: 'delete'
  })
}
