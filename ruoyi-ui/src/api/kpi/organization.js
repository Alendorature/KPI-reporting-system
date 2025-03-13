import request from '@/utils/request'

// 查询国际学术组织任职列表
export function listOrganization(query) {
  return request({
    url: '/kpi/organization/list',
    method: 'get',
    params: query
  })
}
export function myListOrganization(query) {
  return request({
    url: '/kpi/organization/myList',
    method: 'get',
    params: query
  })
}

// 查询国际学术组织任职详细
export function getOrganization(organizationId) {
  return request({
    url: '/kpi/organization/' + organizationId,
    method: 'get'
  })
}

// 新增国际学术组织任职
export function addOrganization(data) {
  return request({
    url: '/kpi/organization',
    method: 'post',
    data: data
  })
}

// 修改国际学术组织任职
export function updateOrganization(data) {
  return request({
    url: '/kpi/organization',
    method: 'put',
    data: data
  })
}

// 删除国际学术组织任职
export function delOrganization(organizationId) {
  return request({
    url: '/kpi/organization/' + organizationId,
    method: 'delete'
  })
}
