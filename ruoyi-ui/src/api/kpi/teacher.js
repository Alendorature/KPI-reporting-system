import request from '@/utils/request'

// 查询老师基本信息列表
export function listTeacher(query) {
  return request({
    url: '/kpi/teacher/list',
    method: 'get',
    params: query
  })
}


// 查询老师基本信息列表
export function listStatistics(query) {
  return request({
    url: '/kpi/teacher/statistics/list',
    method: 'get',
    params: query
  })
}

export function dataTeacher() {
  return request({
    url: '/kpi/teacher/data',
    method: 'get',
  })
}


// 查询老师基本信息详细
export function getTeacher(teacherId) {
  return request({
    url: '/kpi/teacher/' + teacherId,
    method: 'get'
  })
}

// 新增老师基本信息
export function addTeacher(data) {
  return request({
    url: '/kpi/teacher',
    method: 'post',
    data: data
  })
}

// 修改老师基本信息
export function updateTeacher(data) {
  return request({
    url: '/kpi/teacher',
    method: 'put',
    data: data
  })
}

// 删除老师基本信息
export function delTeacher(teacherId) {
  return request({
    url: '/kpi/teacher/' + teacherId,
    method: 'delete'
  })
}
